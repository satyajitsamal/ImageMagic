package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class WriteToExcel extends Im4java{
	java.util.List<String> titlelist=new ArrayList<String>();
	java.util.List<String> urllist=new ArrayList<String>();
	String title;
	String url;
	
	public void writeDataToExcel(int rowcount,int columncount,String filepath,String Sheetname,String value) throws Exception
	{
		//FileOutputStream webdata1=new FileOutputStream(filepath);
	    try
	    {
	        FileInputStream input=new FileInputStream(filepath);
	        XSSFWorkbook wb=new XSSFWorkbook(input);
	        XSSFSheet sh=wb.getSheet(Sheetname);
	        System.out.println("sheet");
	        XSSFRow row=sh.getRow(rowcount);
	        //System.out.println(sh.getRow(rowcount));
	        
	        System.out.println("sheet1");
	        row.createCell(columncount).setCellValue(value);
	        System.out.println("sheet2");
	        FileOutputStream webdata=new FileOutputStream(filepath);
	        wb.write(webdata);
	        System.out.println("sheet3");

	    }
	    catch(Exception e){
	    	System.out.println("sheet exception");
	    }
	}
	    

	
	@Test
	public void writeToExcel() throws Exception{
		File src=new File("C:\\Users\\ssamal\\Desktop\\WriteExcel.xlsx");
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		driver=new FirefoxDriver();
		for(int row=0;row<=sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(0).getStringCellValue());
			title=driver.getTitle().toLowerCase();
			titlelist.add(title);
            url=driver.getCurrentUrl();
            urllist.add(url);
			Thread.sleep(2000);
		File google=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(google, new File("C:\\Users\\ssamal\\Documents\\GOOGLE"+driver.getTitle().trim().toLowerCase()+".png"));
		resize("C:\\Users\\ssamal\\Documents\\GOOGLE"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresize"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
	}
		driver.close();
		driver=new FirefoxDriver();
		for(int row=0;row<=sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(1).getStringCellValue());
			Thread.sleep(2000);
		File google=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(google, new File("C:\\Users\\ssamal\\Documents\\GOOGLE2nd"+driver.getTitle().trim().toLowerCase()+".png"));
		resize("C:\\Users\\ssamal\\Documents\\GOOGLE2nd"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresize2nd"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
 }
		for(int k=0;k<titlelist.size();k++){
			System.out.println(titlelist.size());
			boolean value=compareScreenshot("C:\\Users\\ssamal\\Documents\\GOOGLEresize2nd"+titlelist.get(k)+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresize"+titlelist.get(k)+".png", "C:\\Users\\ssamal\\Documents\\difference"+titlelist.get(k)+".png");
			System.out.println("Value is "+value);
			if(value==true){
				System.out.println("Value is true");
			writeDataToExcel(k, 2, "C:\\Users\\ssamal\\Desktop\\WriteExcel.xlsx", "Sheet1", "Pass");	
			}else{
				System.out.println("value is false");
				writeDataToExcel(k, 2, "C:\\Users\\ssamal\\Desktop\\WriteExcel.xlsx", "Sheet1", "Fail");
				writeDataToExcel(k, 3, "C:\\Users\\ssamal\\Desktop\\WriteExcel.xlsx", "Sheet1", urllist.get(k));
			}
			Thread.sleep(2000);
			//System.out.println("comparing between"+"devresize"+titlelist.get(k)+".png");
		}
	}

}

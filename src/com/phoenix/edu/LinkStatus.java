package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class LinkStatus extends Im4java {
	WebDriver driver;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	File src;
	FileInputStream fis;
	String title;
	java.util.List<String> titlelist=new ArrayList<String>();
	
	public  void readExcel(int rownum,int colnum) throws Exception{
		src=new File("C:\\Users\\ssamal\\Desktop\\Excel1010.xlsx");
		fis=new FileInputStream(src);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		//System.out.println("cell is Empty"+sheet.getRow(rownum).getCell(colnum).getStringCellValue());
		String value=sheet.getRow(rownum).getCell(colnum).getStringCellValue();
		//System.out.println("cell is Empty"+sheet.getRow(rownum).getCell(colnum).getStringCellValue());
		if(value.equalsIgnoreCase("yes")){
			driver=new FirefoxDriver();
			//driver.get(sheet.getRow(rownum).getCell(colnum-1).getStringCellValue());
			for(int row=0;row<=sheet.getLastRowNum();row++){
				driver.get(sheet.getRow(row).getCell(colnum-2).getStringCellValue());
				title=driver.getTitle().toLowerCase();
				titlelist.add(title);
				Thread.sleep(2000);
			File google=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(google, new File("C:\\Users\\ssamal\\Documents\\GOOGLE"+driver.getTitle().trim().toLowerCase()+".png"));
			resize("C:\\Users\\ssamal\\Documents\\GOOGLE"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresize"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
		}
			for(int row=0;row<=sheet.getLastRowNum();row++){
				driver.get(sheet.getRow(row).getCell(colnum-1).getStringCellValue());
				Thread.sleep(2000);
			File google=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(google, new File("C:\\Users\\ssamal\\Documents\\GOOGLE2nd"+driver.getTitle().trim().toLowerCase()+".png"));
			resize("C:\\Users\\ssamal\\Documents\\GOOGLE2nd"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresize2nd"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
	 }
		}else{
			System.out.println("cell is Empty");
		}
	}
	@Test
public void linkStatus() throws Exception{
		src=new File("C:\\Users\\ssamal\\Desktop\\Excel1010.xlsx");
		fis=new FileInputStream(src);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		for(int i=0;i<=this.sheet.getLastRowNum();i++){
	   readExcel(i, 2);
		}
}
}

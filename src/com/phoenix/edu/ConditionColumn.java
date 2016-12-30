package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ConditionColumn extends Im4java {
	WebDriver driver;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	File src;
	FileInputStream fis;
	String title;
	java.util.List<String> titlelist=new ArrayList<String>();
	public void getSheet() throws Exception{
		src=new File("C:\\Users\\ssamal\\Desktop\\WriteExcel.xlsx");
		fis=new FileInputStream(src);
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheetAt(0);
	}
	public void takeScreenshot(String path) throws Exception{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new File(path));
	}
	
	public void readColumnValue(int rownum,int colnum) throws Exception{
		getSheet();
		System.out.println(sheet.getLastRowNum());
		//System.out.println("cell is Empty"+sheet.getRow(rownum).getCell(colnum).getStringCellValue());
		String value=sheet.getRow(rownum).getCell(colnum).getStringCellValue();
		if(value.equalsIgnoreCase("yes")){
			driver=new FirefoxDriver();
			driver.get(sheet.getRow(rownum).getCell(colnum-2).getStringCellValue());
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit-button")).click();
			Thread.sleep(6000);
			/*File google=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(google, new File("C:\\Users\\ssamal\\Documents\\GOOGLE"+driver.getTitle().trim().toLowerCase()+".png"));*/
			takeScreenshot("C:\\Users\\ssamal\\Documents\\GOOGLEdev"+driver.getTitle().trim().toLowerCase()+".png");
			//resize("C:\\Users\\ssamal\\Documents\\GOOGLEdev"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresizedev"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
		   //driver.close();
		   
		   //driver=new FirefoxDriver();
		   driver.get(sheet.getRow(rownum).getCell(colnum-1).getStringCellValue());
		   /*driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.findElement(By.id("username")).sendKeys("admin");
			driver.findElement(By.id("password")).sendKeys("admin");
			driver.findElement(By.id("submit-button")).click();
			Thread.sleep(6000);*/
		   /*File facebook=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(facebook, new File("C:\\Users\\ssamal\\Documents\\GOOGLE"+driver.getTitle().trim().toLowerCase()+".png"));*/
		   takeScreenshot("C:\\Users\\ssamal\\Documents\\GOOGLEqa"+driver.getTitle().trim().toLowerCase()+".png");
			//resize("C:\\Users\\ssamal\\Documents\\GOOGLEqa"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEresizeqa"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
		   driver.close();
		   compareScreenshot("C:\\Users\\ssamal\\Documents\\GOOGLEdev"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEqa"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\DIFFERENCE"+"AEM12"+".png");
		   mergeImages("C:\\Users\\ssamal\\Documents\\GOOGLEdev"+"press release page"+".png", "C:\\Users\\ssamal\\Documents\\GOOGLEqa"+"press release page"+".png", "C:\\Users\\ssamal\\Documents\\merge1"+".png");
		   mergeImages("C:\\Users\\ssamal\\Documents\\merge1"+".png", "C:\\Users\\ssamal\\Documents\\DIFFERENCE"+"AEM12"+".png", "C:\\Users\\ssamal\\Documents\\merge2"+".png");
		}
		
	}
	@Test
	public void conditionColumn() throws Exception{
		getSheet();
		for(int row=0;row<=sheet.getLastRowNum();row++){
		readColumnValue(row, 2);
	}
	}

}

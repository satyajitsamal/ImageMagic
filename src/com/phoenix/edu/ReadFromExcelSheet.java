package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class ReadFromExcelSheet extends Im4java {
	//WebDriver driver;
	java.util.List<String> titlelist=new ArrayList<String>();
	String title;
	java.util.List<String> urllist=new ArrayList<String>();
	String url;
	String prodUrl="http://www.phoenix.edu/";
	String preprodUrl="http://www.preprod.aptimus.phoenix.edu/";
	String qaUrl="http://www.qa.aptimus.phoenix.edu/";
	String devUrlforNewAem="http://www-new62.devint.phoenix.edu/";
	String devUrl="http://www.devint.aptimus.phoenix.edu/";
	String uatUrl="http://www.uat.aptimus.phoenix.edu/";
	String qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qa";
	String qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qaresize";
	String devPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\dev";
	String devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\devresize";
	String differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Differencescreenshots\\devdifference";
	String firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Merge1\\1stMerge";
	String fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Finalmerge\\Fullimage";
	String excelSheetPath="C:\\Users\\ssamal\\Desktop\\ForDemo.xlsx";
	
	public void doLogin(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
	}
	
	public void getFolder() throws Exception{
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		String value=sheet.getRow(0).getCell(0).getStringCellValue();
		if(value.startsWith(qaUrl)){
			new File("C:\\Users\\ssamal\\Documents\\QAFolder").mkdirs();
			new File("C:\\Users\\ssamal\\Documents\\Preprod").mkdirs();
			new File("C:\\Users\\ssamal\\Documents\\").mkdirs();
			new File("C:\\Users\\ssamal\\Documents\\").mkdirs();
		}
	}
	@Test
	public void readFromExcelSheet() throws Exception{
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		startRecording();
		driver=new FirefoxDriver();
		//driver.get("https://author.qa.aptimus.phoenix.edu/siteadmin#");
		//doLogin();
		XSSFSheet sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		//driver.get(sheet.getRow(0).getCell(0).getStringCellValue());
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(0).getStringCellValue());
			Thread.sleep(4000);
			title=driver.getTitle().toLowerCase().replaceAll("\\/","");
			titlelist.add(title);
			url=driver.getCurrentUrl();
	        urllist.add(url);
	        takeScreenshot(qaPath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png");
			//java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(src1,new java.io.File(qaPath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png"));
			System.out.println("Title is:"+driver.getTitle().trim().toLowerCase().replaceAll("\\/",""));
			resize(qaPath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png", qaResizePath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png", 1407, 1901);
			System.out.println("finish");
		}
		driver.close();
		driver=new FirefoxDriver();
		/*driver.get("https://author.qa.aptimus.phoenix.edu/siteadmin#");
		doLogin();*/
		
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(1).getStringCellValue());
			Thread.sleep(4000);
            takeScreenshot(devPath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png");
			//java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//FileUtils.copyFile(src1,new java.io.File(devPath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png"));
			System.out.println("Dev Title is:"+driver.getTitle().trim().toLowerCase().replaceAll("\\/",""));
			resize(devPath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png", devResizePath+driver.getTitle().trim().toLowerCase().replaceAll("\\/","")+".png", 1407, 1901);
			System.out.println("finish");
		}
		stopRecording();
		
		for(int k=0;k<titlelist.size();k++){
		boolean value=compareScreenshot(devResizePath+titlelist.get(k)+".png", qaResizePath+titlelist.get(k)+".png", differencePath+titlelist.get(k)+".png");
		mergeImages(devResizePath+titlelist.get(k)+".png", qaResizePath+titlelist.get(k)+".png", firstMergePath+titlelist.get(k)+".png");
		//mergeImages(firstMergePath+titlelist.get(k)+".png", differencePath+titlelist.get(k)+".png", fullMergePath+titlelist.get(k)+".png");
			if(value==true){
				System.out.println("Value is true");
			writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Pass");	
			}else{
				System.out.println("value is false");
				mergeImages(firstMergePath+titlelist.get(k)+".png", differencePath+titlelist.get(k)+".png", fullMergePath+titlelist.get(k)+".png");
				writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Fail");
				System.out.println(urllist.get(k));
				writeDataToExcel(k, 3, excelSheetPath, "Sheet1", urllist.get(k));
			}
			Thread.sleep(2000);
			//System.out.println("comparing between"+"devresize"+titlelist.get(k)+".png");
		}
		
		
	}
	}



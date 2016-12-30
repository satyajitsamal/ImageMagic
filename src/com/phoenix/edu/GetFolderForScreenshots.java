package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GetFolderForScreenshots extends Im4java {
	java.util.List<String> titlelist=new ArrayList<String>();
	String title;
	java.util.List<String> urllist=new ArrayList<String>();
	String url;
	java.util.List<String> mainUrllist=new ArrayList<String>();
	String mainUrl;
	String folderpath;
	String prodUrl="http://www.phoenix.edu/";
	String preprodUrl="http://www.preprod.aptimus.phoenix.edu/";
	String qaUrl="http://www.qa.aptimus.phoenix.edu/";
	String devUrlforNewAem="http://www-new62.devint.phoenix.edu/";
	String devUrl="http://www.devint.aptimus.phoenix.edu/";
	String uatUrl="http://www.uat.aptimus.phoenix.edu/";
	String qaUrlforNewAem="http://www-new62.qa.phoenix.edu/";
	String qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qa";
	String qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qaresize";
	String devPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\dev";
	String devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\devresize";
	String differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Differencescreenshots\\devdifference";
	String firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Merge1\\1stMerge";
	String fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Finalmerge\\";
	String passfullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\PassFinalmerge\\";
	String imageAfter2days="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\ImageAfter2days\\qa";
	String excelSheetPath="C:\\Users\\ssamal\\Desktop\\ForDemo.xlsx";
	
	public void getUrlForImage(){
		url=driver.getCurrentUrl();
		if(url.contains("https")){
			url=url.replaceAll("https", "http");
			System.out.println(url);
		}
		if(url.startsWith(prodUrl)){
			mainUrl=url.replaceAll(prodUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(preprodUrl)){
			mainUrl=url.replaceAll(preprodUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(qaUrl)){
			mainUrl=url.replaceAll(qaUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(devUrlforNewAem)){
			mainUrl=url.replaceAll(devUrlforNewAem, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(devUrl)){
			mainUrl=url.replaceAll(devUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(uatUrl)){
			mainUrl=url.replaceAll(uatUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(qaUrlforNewAem)){
			mainUrl=url.replaceAll(qaUrlforNewAem, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}
	}
	
	/*public void clearColumn(int columncount,String filepath,String Sheetname) throws Exception
	{
	    try
	    {
	        FileInputStream input=new FileInputStream(filepath);
	        XSSFWorkbook wb=new XSSFWorkbook(input);
	        XSSFSheet sh=wb.getSheet(Sheetname);
	        System.out.println("sheet");
	        for (int k=0;k<sh.getLastRowNum();k++){
	        XSSFRow row=sh.getRow(k);
	        row.getCell(columncount).setCellValue("");
	        }
	        //row.createCell(columncount).setCellValue(value);
	        FileOutputStream webdata=new FileOutputStream(filepath);
	        wb.write(webdata);

	    }
	    catch(Exception e){
	    	System.out.println("sheet exception");
	    }
	}*/
	
	public void getFolder(String path) throws Exception{
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		
		XSSFSheet sheet=wb.getSheetAt(0);
		Row row=sheet.getRow(0);
		row.getLastCellNum();
		System.out.println(sheet.getLastRowNum());
		String value=sheet.getRow(0).getCell(0).getStringCellValue();
		if(value.contains("qa")){
			new File(path+"QA6.1").mkdir();
			System.out.println("qa6.1 is created");
			new File(path+"QA6.2").mkdirs();
			System.out.println("qa6.2 is created");
			new File(path+"Difference6.1&6.2").mkdirs();
			System.out.println("difference is created");
			new File(path+"1stmerge6.1&6.2").mkdirs();
			System.out.println("1stmerge is created");
			new File(path+"finalmerge6.1&6.2").mkdirs();
			System.out.println("finalmerge is created");
			 qaPath=path+"QA6.1\\qa";
			 qaResizePath=path+"QA6.1\\qaresize";
			 devPath=path+"QA6.2\\dev";
			 devResizePath=path+"QA6.2\\devresize";
			 differencePath=path+"Difference6.1&6.2\\devdifference";
			 firstMergePath=path+"1stmerge6.1&6.2\\1stMerge";
			 fullMergePath=path+"finalmerge6.1&6.2\\";
		}
	}
	
	public void createCopy(String srcpath,String copypath){
		File source = new File(srcpath);
		new File(copypath).mkdirs();
		File dest = new File(copypath);
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("not copied");
		}
	}
	
	@Parameters("browser")
	@BeforeMethod
	public void openBrowser(String browser) throws Exception{
		clearColumn(2, excelSheetPath, "Sheet1");
		if(browser.equalsIgnoreCase("firefox")) {
			
			// If browser is firefox, then do this
			 
			  driver = new FirefoxDriver();
			  folderpath="C:\\Users\\ssamal\\Documents\\firefoxfolder\\";
			  getFolder("C:\\Users\\ssamal\\Documents\\firefoxfolder\\");
		}else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", "C:\\Users\\ssamal\\Desktop\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
			 
			  driver = new InternetExplorerDriver();
		 
		  }
		}
	@Test
	
	public void compare() throws Exception{

		//driver=new FirefoxDriver();
		
		//This method will compare realtime screenshots
		
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		//startRecording();
		//driver=new FirefoxDriver();
		XSSFSheet sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(0).getStringCellValue());
			Thread.sleep(4000);
			title=driver.getTitle().toLowerCase().replaceAll("\\/","");
			titlelist.add(title);
			getUrlForImage();
			System.out.println(mainUrl);
			mainUrllist.add(mainUrl);
			url=driver.getCurrentUrl();
	        urllist.add(url);
	        takeScreenshot(qaPath+mainUrl+".png");
	        Thread.sleep(2000);
			System.out.println("Title is:"+mainUrl);
			//resize(qaPath+mainUrl+".png", qaResizePath+mainUrl+".png", 1407, 1901);
			System.out.println("finish");
		}
		//driver.close();
		//driver=new FirefoxDriver();
		
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(1).getStringCellValue());
			Thread.sleep(4000);
			getUrlForImage();
			System.out.println(mainUrl);
			mainUrllist.add(mainUrl);
            takeScreenshot(devPath+mainUrl+".png");
            Thread.sleep(2000);
			//resize(devPath+mainUrl+".png", devResizePath+mainUrl+".png", 1407, 1901);
			System.out.println("finish");
		}
		
		
		for(int k=0;k<titlelist.size();k++){
			
			//comparing both real time screenshots and producing the differences
			
		boolean value=compareScreenshot(devPath+mainUrllist.get(k)+".png", qaPath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png");
		Thread.sleep(2000);
		mergeImages(devPath+mainUrllist.get(k)+".png", qaPath+mainUrllist.get(k)+".png", firstMergePath+mainUrllist.get(k)+".png");
		Thread.sleep(2000);
		//mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", fullMergePath+mainUrllist.get(k)+".png");
			if(value==true){
				System.out.println("Value is true");
				mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", passfullMergePath+mainUrllist.get(k)+".png");
			writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Pass");	
			}else{
				System.out.println("value is false");
				mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", fullMergePath+mainUrllist.get(k)+".png");
				writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Fail");
				System.out.println(urllist.get(k));
				writeDataToExcel(k, 3, excelSheetPath, "Sheet1", urllist.get(k));
				System.out.println(mainUrllist.get(k));
				writeDataToExcel(k, 4, excelSheetPath, "Sheet1", mainUrllist.get(k)+".png");
			}
			Thread.sleep(2000);
		}
		
	
	}
	
	@AfterMethod
	public void tearDown(){
		createCopy(folderpath+"QA6.1",folderpath+"QA6.1copy");
		createCopy(folderpath+"QA6.2",folderpath+"QA6.2copy");
		/*File source = new File("C:\\Users\\ssamal\\Documents\\firefoxfolder\\QA6.1\\");
		new File("C:\\Users\\ssamal\\Documents\\firefoxfolder\\QA6.1copy").mkdirs();
		File dest = new File("C:\\Users\\ssamal\\Documents\\firefoxfolder\\QA6.1copy\\");
		try {
		    FileUtils.copyDirectory(source, dest);
		} catch (IOException e) {
		    e.printStackTrace();
		    System.out.println("not copied");
		}*/
	}

}

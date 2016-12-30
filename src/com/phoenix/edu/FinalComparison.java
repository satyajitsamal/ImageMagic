package com.phoenix.edu;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Set;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

public class FinalComparison extends Im4java {
	String chromeAddonCRXPath = "C:\\NewWorkSpace\\ImageMagic\\Nimbus-Screenshot-and-Screencast_v8.4.1.crx";
	String url;
	String mainUrl;
	String qaUrl="http://www.qa.aptimus.phoenix.edu/";
	String qaUrlforNewAem="http://www-new62.qa.phoenix.edu/";
	java.util.List<String> mainUrllist=new ArrayList<String>();
	java.util.List<String> urllist=new ArrayList<String>();
	String firstColumnPath="C:\\Users\\ssamal\\Documents\\ChromeScreenshots\\1stColumn\\";
	String secondColumnPath="C:\\Users\\ssamal\\Documents\\ChromeScreenshots\\2ndColumn\\";
	String differencePath="C:\\Users\\ssamal\\Documents\\ChromeScreenshots\\Differencescreenshots\\";
    String firstMergePath="C:\\Users\\ssamal\\Documents\\ChromeScreenshots\\Merge1\\";
    String fullMergePath="C:\\Users\\ssamal\\Documents\\ChromeScreenshots\\Finalmerge\\";
    String passfullMergePath="C:\\Users\\ssamal\\Documents\\ChromeScreenshots\\Passmerge\\";
	String excelSheetPath="C:\\Users\\ssamal\\Desktop\\Mostvistedpages701to800.xlsx";
	
	public void getUrlForImage(){
		url=driver.getCurrentUrl();
		if(url.contains("https")){
			url=url.replaceAll("https", "http");
		}
		if(url.startsWith(qaUrl)){
			mainUrl=url.replaceAll(qaUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(qaUrlforNewAem)){
			mainUrl=url.replaceAll(qaUrlforNewAem, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}
	}
	
	public void initializeChrome() throws Exception{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssamal\\Desktop\\chromedriver_win32\\chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		 options.addExtensions(new File(chromeAddonCRXPath));
		  //DesiredCapabilities capabilities = new DesiredCapabilities();
		 // capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		  driver = new ChromeDriver(options);
		  driver.manage().window().maximize();
		  Thread.sleep(2000);
	}
	
	@Test
	public void screenshotInChrome() throws Exception{
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssamal\\Desktop\\chromedriver_win32\\chromedriver.exe");
		  ChromeOptions options = new ChromeOptions();
		  options.addExtensions(new File(chromeAddonCRXPath));
		  DesiredCapabilities capabilities = new DesiredCapabilities();
		  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		  driver = new ChromeDriver(capabilities);
		  driver.manage().window().maximize();
		  Thread.sleep(2000);*/
		  //driver.get("http://www.qa.aptimus.phoenix.edu/courses/mte541.html");
		    //initializeChrome();
		    File src=new File(excelSheetPath);
			FileInputStream fis=new FileInputStream(src);
			XSSFWorkbook wb=new XSSFWorkbook(fis);
			XSSFSheet sheet=wb.getSheetAt(0);
			System.out.println(sheet.getLastRowNum());
			/*for(int row=0;row<sheet.getLastRowNum();row++){
		    driver.get(sheet.getRow(row).getCell(0).getStringCellValue());
		    getUrlForImage();
		    mainUrllist.add(mainUrl);
		    System.out.println(mainUrl);
		    url=driver.getCurrentUrl();
	        urllist.add(url);
		  //String parentWinName = driver.getWindowHandle();
			Thread.sleep(2000);
			takeScreenshotThroughNimbusAddon("");
			Runtime.getRuntime().exec("C:\\Users\\ssamal\\Desktop\\saveFile.exe " + mainUrl+".png");
			driver.close();
			windowHandlerForNimbus();
			}
			driver.quit();*/ //1st screen
			
			/*driver.get("http://www-new62.qa.phoenix.edu/courses/mte541.html");
			Thread.sleep(2000);
			takeScreenshotThroughNimbusAddon("");*/
			//renameFile("C:\\Users\\ssamal\\Desktop\\imageinchrome.png", "C:\\Users\\ssamal\\Desktop\\customizeName.png");
			
			initializeChrome();
			
			for(int row=0;row<sheet.getLastRowNum();row++){
				 
				 /*Set<String> totalWindows =  driver.getWindowHandles();
				if (totalWindows.size()>=1) {
					Robot robot = new Robot();
					robot.keyPress(KeyEvent.VK_CONTROL);
					robot.keyPress(KeyEvent.VK_F4);
					 robot.delay(100);
					robot.keyRelease(KeyEvent.VK_CONTROL);
					robot.keyRelease(KeyEvent.VK_F4);
					//act.sendKeys(Keys.CONTROL,Keys.F4);
					//act.keyDown(Keys.CONTROL).keyDown(Keys.F4).keyUp(Keys.CONTROL).keyUp(Keys.F4).build().perform();
					//driver.switchTo().window((String) totalWindows.get(1));
					//System.out.println(driver.getCurrentUrl());
				}*/
				driver.get(sheet.getRow(row).getCell(1).getStringCellValue());
			    getUrlForImage();
			    mainUrllist.add(mainUrl);
			    System.out.println(mainUrl);
			  //String parentWinName = driver.getWindowHandle();
				Thread.sleep(2000);
				takeScreenshotThroughNimbusAddon("");
				Runtime.getRuntime().exec("C:\\Users\\ssamal\\Desktop\\secondColumn.exe " + mainUrl+".png");
				driver.close();
				windowHandlerForNimbus();
				}
			for(int k=0;k<mainUrllist.size();k++){
			boolean value=compareScreenshot(firstColumnPath+mainUrllist.get(k)+".png", secondColumnPath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png");
			Thread.sleep(2000);
			mergeImages(firstColumnPath+mainUrllist.get(k)+".png", secondColumnPath+mainUrllist.get(k)+".png", firstMergePath+mainUrllist.get(k)+".png");
			//mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", fullMergePath+mainUrllist.get(k)+".png");
			if(value==true){
				System.out.println("Value is true");
				mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", passfullMergePath+mainUrllist.get(k)+".png");
				//createCopy(folderPath+"\\PassFinalmerge", folderPath+"\\PassFinalmerge"+getDateAndTime()+"copy");
			writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Pass");	
			}else{
				System.out.println("value is false");
				mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", fullMergePath+mainUrllist.get(k)+".png");
				writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Fail");
				//System.out.println(urllist.get(k));
				//writeDataToExcel(k, 3, excelSheetPath, "Sheet1", urllist.get(k));
				System.out.println(mainUrllist.get(k));
				writeDataToExcel(k, 4, excelSheetPath, "Sheet1", mainUrllist.get(k)+".png");
			}
			}
	}

}

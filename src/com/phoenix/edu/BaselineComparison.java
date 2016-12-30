package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import ru.yandex.qatools.ashot.AShot;

public class BaselineComparison extends Im4java {
	java.util.List<String> titlelist=new ArrayList<String>();
	String title;
	java.util.List<String> urllist=new ArrayList<String>();
	String url;
	java.util.List<String> mainUrllist=new ArrayList<String>();
	String mainUrl;
	String onlyBaselineImagePath;
	String realTimePath;
	String folderPath;
	String secondColumnPath;
	String prodUrl="http://www.phoenix.edu/";
	String preprodUrl="http://www.preprod.aptimus.phoenix.edu/";
	String qaUrl="http://www.qa.aptimus.phoenix.edu/";
	String devUrlforNewAem="http://www-new62.devint.phoenix.edu/";
	String qaUrlforNewAem="http://www-new62.qa.phoenix.edu/";
	String devUrl="http://www.devint.aptimus.phoenix.edu/";
	String uatUrl="http://www.uat.aptimus.phoenix.edu/";
	String httpsuatUrl="https://www.uat.aptimus.phoenix.edu/";
	String uatUrlforNewAem="http://www-new62.uat.phoenix.edu/";
	String httpsuatUrlforNewAem="https://www-new62.uat.phoenix.edu/";
	String qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qa";
	String qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qaresize";
	String devPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\dev";
	String devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\devresize";
	String differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Differencescreenshots\\devdifference";
	String firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Merge1\\1stMerge";
	String fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Finalmerge\\";
	String passfullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\PassFinalmerge\\";
	String imageAfter2days="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\ImageAfter2days\\qa";
	String excelSheetPath="C:\\Users\\ssamal\\Desktop\\Mostivisted_Https1to400.xlsx";
	String chromeAddonCRXPath = "C:\\NewWorkSpace\\ImageMagic\\Nimbus-Screenshot-and-Screencast_v8.4.1.crx";
	
	public void getUrlForImage(){
		url=driver.getCurrentUrl();
		if(url.contains("https")){
			url=url.replaceAll("https", "http");
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
		}else if(url.startsWith(uatUrlforNewAem)){
			mainUrl=url.replaceAll(uatUrlforNewAem, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}/*else if(url.startsWith(httpsuatUrlforNewAem)){
			mainUrl=url.replaceAll(httpsuatUrlforNewAem, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}else if(url.startsWith(httpsuatUrl)){
			mainUrl=url.replaceAll(httpsuatUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", "");
		}*/
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
	
	public String getDateAndTime(){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (hh- mm- ss)");
		String time = dateFormat.format(now);
		return time;
	}
	
	public void cleanDirectory(String directoryPath) throws Exception{
		FileUtils.cleanDirectory(new File(directoryPath));
	}
	
	public void createFolder(String path) throws Exception{
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		String value=sheet.getRow(0).getCell(0).getStringCellValue();
		if(value.contains("qa")){
			new File(path+"\\QAImages").mkdir();
		}else if(value.contains("preprod")){
			new File(path+"\\PreprodImages").mkdir();
		}
		
	}
	

	@Parameters("browser")
	@BeforeMethod
	public void openBrowser(String browser) throws Exception{
		clearColumn(2, excelSheetPath, "Sheet1");
		clearColumn(3, excelSheetPath, "Sheet1");
		clearColumn(4, excelSheetPath, "Sheet1");
		if(browser.equalsIgnoreCase("firefox")) {
			
			// If browser is firefox, then do this
			 
			  driver = new FirefoxDriver();	  
			  
			     onlyBaselineImagePath="C:\\Users\\ssamal\\Documents\\OnlyBaselineImages\\OnlyBaselineFirefox";
			     realTimePath="C:\\Users\\ssamal\\Documents\\BaselineImageComparison\\RealTimeImagesFirefox";
			     folderPath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\";
			     secondColumnPath="C:\\Users\\ssamal\\Documents\\BaselineImageComparison\\RealTimeImagesFirefox\\2ndColumnImages";
			     qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\QAscreenshots\\qa";
				 qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\QAscreenshots\\qaresize";
				 devPath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\Devscreenshots\\dev";
				 devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\Devscreenshots\\devresize";
				 differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\Differencescreenshots\\devdifference";
				 firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\Merge1\\1stMerge";
				 fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\Finalmerge\\";
				 passfullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\PassFinalmerge\\";
		 
		  }else if (browser.equalsIgnoreCase("ie")) { 
		 
			     onlyBaselineImagePath="C:\\Users\\ssamal\\Documents\\OnlyBaselineImages\\OnlyBaselineIE";
			     realTimePath="C:\\Users\\ssamal\\Documents\\BaselineImageComparison\\RealTimeImagesIE";
			     folderPath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\";
			     secondColumnPath="C:\\Users\\ssamal\\Documents\\BaselineImageComparison\\RealTimeImagesIE\\2ndColumnImages";
			     qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\QAscreenshots\\qa";
				 qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\QAscreenshots\\qaresize";
				 devPath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\Devscreenshots\\dev";
				 devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\Devscreenshots\\devresize";
				 differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\Differencescreenshots\\devdifference";
				 firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\Merge1\\1stMerge";
				 fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\Finalmerge\\";
				 passfullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\IE\\PassFinalmerge\\";
				 
				// Here I am setting up the path for my IEDriver
		 
			  System.setProperty("webdriver.ie.driver", "C:\\Users\\ssamal\\Desktop\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		 
			  driver = new InternetExplorerDriver();
		 
		  }else if (browser.equalsIgnoreCase("chrome")) { 
		 
			  
			     qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\QAscreenshots\\qa";
				 qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\QAscreenshots\\qaresize";
				 devPath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\Devscreenshots\\dev";
				 devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\Devscreenshots\\devresize";
				 differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\Differencescreenshots\\devdifference";
				 firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\Merge1\\1stMerge";
				 fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\chrome\\Finalmerge\\";
				 
				// Here I am setting up the path for my chromeDriver
		 
				  System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssamal\\Desktop\\chromedriver_win32\\chromedriver.exe");
				  ChromeOptions options = new ChromeOptions();
				  options.addExtensions(new File(chromeAddonCRXPath));
				  DesiredCapabilities capabilities = new DesiredCapabilities();
				  capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				  driver = new ChromeDriver(capabilities);
				  driver.manage().window().maximize();
			  
		  } 
		
		
	}
	
	@Parameters("browser")
	@Test
	public void onlyBaselineImages(String browser) throws Exception{
		String parentWinName = driver.getWindowHandle();
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		/*String value=sheet.getRow(0).getCell(0).getStringCellValue();
		if(value.contains("qa")){
			new File(onlyBaselineImagePath+"\\QAImages").mkdir();
			onlyBaselineImagePath=onlyBaselineImagePath+"\\QAImages";
		}else if(value.contains("preprod")){
			new File(onlyBaselineImagePath+"\\PreprodImages").mkdir();
			onlyBaselineImagePath=onlyBaselineImagePath+"\\PreprodImages";
		}else if(value.contains("devint")){
			new File(onlyBaselineImagePath+"\\DevintImages").mkdir();
			onlyBaselineImagePath=onlyBaselineImagePath+"\\DevintImages";
		}*/
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(0).getStringCellValue());
			Thread.sleep(2000);
			title=driver.getTitle().toLowerCase().replaceAll("\\/","");
			titlelist.add(title);
			getUrlForImage();
			System.out.println(mainUrl);
			mainUrllist.add(mainUrl);
	        urllist.add(url);
	        takeScreenshot(onlyBaselineImagePath+"\\"+mainUrl+".png");
			System.out.println("Title is:"+mainUrl);
			resize(onlyBaselineImagePath+"\\"+mainUrl+".png", onlyBaselineImagePath+"\\"+"resize"+mainUrl+".png", 1407, 1901);
			System.out.println("finish");
			//createCopy("C:\\Users\\ssamal\\Documents\\OnlyBaselineImages\\OnlyBaselineFirefox", "C:\\Users\\ssamal\\Documents\\OnlyBaselineImages\\OnlyBaselineFirefox"+"copy");
		}
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd (hh- mm- ss)");
		String time = dateFormat.format(now);
		createCopy(onlyBaselineImagePath, onlyBaselineImagePath+time+"copy");
	}

	@Test
	public void baselineImageComparison() throws Exception{
		
		//This method will compare realtime images with baseline images
		
		File src=new File(excelSheetPath);
		FileInputStream fis=new FileInputStream(src);
		XSSFWorkbook wb=new XSSFWorkbook(fis);
		XSSFSheet sheet=wb.getSheetAt(0);
		System.out.println(sheet.getLastRowNum());
		/*String cellvalue=sheet.getRow(0).getCell(1).getStringCellValue();
		if(cellvalue.contains("qa")){
			new File(realTimePath+"\\2ndColumnImages"+"\\QAImages").mkdir();
			//realTimePath=realTimePath+"\\2ndColumnImages"+"\\QAImages";
			secondColumnPath=secondColumnPath+"\\QAImages";
			onlyBaselineImagePath=onlyBaselineImagePath+"\\QAImages";
		}else if(cellvalue.contains("preprod")){
			new File(onlyBaselineImagePath+"\\PreprodImages").mkdir();
			secondColumnPath=secondColumnPath+"\\PreprodImages";
			onlyBaselineImagePath=onlyBaselineImagePath+"\\PreprodImages";
		}else if(cellvalue.contains("devint")){
			new File(onlyBaselineImagePath+"\\DevintImages").mkdir();
			secondColumnPath=secondColumnPath+"\\DevintImages";
			onlyBaselineImagePath=onlyBaselineImagePath+"\\DevintImages";
		}*/
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(1).getStringCellValue());
			Thread.sleep(5000);
			title=driver.getTitle().toLowerCase().replaceAll("\\/","");
			titlelist.add(title);
			getUrlForImage();
			System.out.println(mainUrl);
			mainUrllist.add(mainUrl);
	        urllist.add(url);
	        takeScreenshot(realTimePath+"\\2ndColumnImages\\"+mainUrl+".png");
	        //takeScreenshot(secondColumnPath+"\\"+mainUrl+".png");
	        Thread.sleep(2000);
			System.out.println("Title is:"+mainUrl);
			resize(realTimePath+"\\2ndColumnImages\\"+mainUrl+".png", realTimePath+"\\2ndColumnImages\\"+"resize"+mainUrl+".png", 1407, 1901);
			//resize(secondColumnPath+"\\"+mainUrl+".png", secondColumnPath+"\\"+"resize"+mainUrl+".png", 1407, 1901); //env-env
			System.out.println("finish");
		}
		//createCopy(realTimePath+"\\2ndColumnImages", realTimePath+"\\2ndColumnImages"+getDateAndTime()+"copy");
		createCopy(secondColumnPath, secondColumnPath+getDateAndTime()+"copy");
		for(int k=0;k<urllist.size();k++){
			
			//comparing the realtime screenshots with baseline screenshots
			
		//boolean value=compareScreenshot(realTimePath+"\\2ndColumnImages\\"+"resize"+mainUrllist.get(k)+".png", onlyBaselineImagePath+"\\"+"resize"+mainUrllist.get(k)+".png", realTimePath+"\\Differencescreenshots\\"+mainUrllist.get(k)+".png");
		
		boolean value=compareScreenshot(secondColumnPath+"\\"+"resize"+mainUrllist.get(k)+".png", onlyBaselineImagePath+"\\"+"resize"+mainUrllist.get(k)+".png", realTimePath+"\\Differencescreenshots\\"+mainUrllist.get(k)+".png");
		//merging the two screenshots and producing another image
		
			mergeImages(realTimePath+"\\2ndColumnImages\\"+"resize"+mainUrllist.get(k)+".png", onlyBaselineImagePath+"\\"+"resize"+mainUrllist.get(k)+".png", realTimePath+"\\Merge1\\"+mainUrllist.get(k)+".png");
			//mergeImages(firstMergePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png", fullMergePath+mainUrllist.get(k)+".png");//not required
		//mergeImages(secondColumnPath+"\\"+"resize"+mainUrllist.get(k)+".png", onlyBaselineImagePath+"\\"+"resize"+mainUrllist.get(k)+".png", realTimePath+"\\Merge1\\"+mainUrllist.get(k)+".png"); //env-env
				if(value==true){
					System.out.println("Value is true");
					mergeImages(realTimePath+"\\Merge1\\"+mainUrllist.get(k)+".png", realTimePath+"\\Differencescreenshots\\"+mainUrllist.get(k)+".png", realTimePath+"\\PassFinalmerge\\"+mainUrllist.get(k)+".png");
					//mergeImages(realTimePath+"\\Merge1\\"+mainUrllist.get(k)+".png", realTimePath+"\\Differencescreenshots\\"+mainUrllist.get(k)+".png", realTimePath+"\\PassFinalmerge\\"+mainUrllist.get(k)+".png"); //env-env
				writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Pass");	
				}else{
					System.out.println("value is false");
					mergeImages(realTimePath+"\\Merge1\\"+mainUrllist.get(k)+".png", realTimePath+"\\Differencescreenshots\\"+mainUrllist.get(k)+".png", realTimePath+"\\Finalmerge\\"+mainUrllist.get(k)+".png");
					//mergeImages(realTimePath+"\\Merge1\\"+mainUrllist.get(k)+".png", realTimePath+"\\Differencescreenshots\\"+mainUrllist.get(k)+".png", realTimePath+"\\Finalmerge\\"+mainUrllist.get(k)+".png"); //env-env
					writeDataToExcel(k, 2, excelSheetPath, "Sheet1", "Fail");
					System.out.println(urllist.get(k));
					writeDataToExcel(k, 3, excelSheetPath, "Sheet1", urllist.get(k));
					writeDataToExcel(k, 4, excelSheetPath, "Sheet1", mainUrllist.get(k)+".png");
				}
			Thread.sleep(2000);
		}
		createCopy(realTimePath+"\\Finalmerge", realTimePath+"\\Finalmerge"+getDateAndTime()+"copy");
		//cleanDirectory(realTimePath+"\\Finalmerge\\");
		
		
	}
	@Test(enabled=true)
	
	public void realTimeComparison() throws Exception{
		
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
			resize(qaPath+mainUrl+".png", qaResizePath+mainUrl+".png", 1407, 1901);
			System.out.println("finish");
		}
		createCopy(folderPath+"\\QAscreenshots", folderPath+"\\QAscreenshots"+getDateAndTime()+"copy");
		
		for(int row=0;row<sheet.getLastRowNum();row++){
			driver.get(sheet.getRow(row).getCell(1).getStringCellValue());
			Thread.sleep(4000);
			getUrlForImage();
			System.out.println(mainUrl);
			mainUrllist.add(mainUrl);
            takeScreenshot(devPath+mainUrl+".png");
            Thread.sleep(2000);
			resize(devPath+mainUrl+".png", devResizePath+mainUrl+".png", 1407, 1901);
			System.out.println("finish");
		}
		createCopy(folderPath+"\\Devscreenshots", folderPath+"\\Devscreenshots"+getDateAndTime()+"copy");
		//stopRecording();
		
		for(int k=0;k<titlelist.size();k++){
			
			//comparing both real time screenshots and producing the differences
			
		boolean value=compareScreenshot(devResizePath+mainUrllist.get(k)+".png", qaResizePath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png");
			//boolean value=compareScreenshot(devPath+mainUrllist.get(k)+".png", qaPath+mainUrllist.get(k)+".png", differencePath+mainUrllist.get(k)+".png");
		Thread.sleep(2000);
		mergeImages(devResizePath+mainUrllist.get(k)+".png", qaResizePath+mainUrllist.get(k)+".png", firstMergePath+mainUrllist.get(k)+".png");
		Thread.sleep(2000);
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
				System.out.println(urllist.get(k));
				writeDataToExcel(k, 3, excelSheetPath, "Sheet1", urllist.get(k));
				System.out.println(mainUrllist.get(k));
				writeDataToExcel(k, 4, excelSheetPath, "Sheet1", mainUrllist.get(k)+".png");
			}
			Thread.sleep(2000);
		}
		createCopy(folderPath+"\\PassFinalmerge", folderPath+"\\PassFinalmerge"+getDateAndTime()+"copy");
		createCopy(folderPath+"\\Finalmerge", folderPath+"\\Finalmerge"+getDateAndTime()+"copy");
		cleanDirectory(folderPath+"\\Finalmerge\\");
		
	}
	@AfterMethod
	public void tearDown() throws Exception{
		/*if(folderPath.contains("firefox")){
			createCopy(folderPath+"QAscreenshots", folderPath+"QAscreenshotsCopy");
			createCopy(folderPath+"Devscreenshots", folderPath+"DevscreenshotsCopy");
			createCopy(folderPath+"Finalmerge", folderPath+"FinalmergeCopy");
		}else{
			folderPath="C:\\Users\\ssamal\\Documents\\screenshots\\IE";
			createCopy(folderPath+"QAscreenshots", folderPath+"QAscreenshotsCopy");
			createCopy(folderPath+"Devscreenshots", folderPath+"DevscreenshotsCopy");
			createCopy(folderPath+"Finalmerge", folderPath+"FinalmergeCopy");
		}*/
		cleanDirectory(folderPath+"\\Differencescreenshots\\");
		cleanDirectory(folderPath+"\\QAscreenshots\\");
		cleanDirectory(folderPath+"\\Devscreenshots\\");
		cleanDirectory(folderPath+"\\Merge1\\");
		cleanDirectory(folderPath+"\\PassFinalmerge\\");
		driver.quit();
	}
	
	

	 public void takeScreenshotThroughNimbusAddon(String path) throws Exception{
		 String selectAll = Keys.chord(Keys.CONTROL,Keys.SHIFT,"4");
		  driver.findElement(By.tagName("html")).sendKeys(selectAll);
		  Thread.sleep(3000);
		  
		}
	 
}

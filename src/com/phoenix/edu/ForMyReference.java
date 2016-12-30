package com.phoenix.edu;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.Augmenter;
import org.testng.annotations.Test;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.screentaker.ViewportPastingStrategy;

public class ForMyReference extends Im4java{
	java.util.List<String> titlelist=new ArrayList<String>();
	String title;
	java.util.List<String> urllist=new ArrayList<String>();
	String url;
	java.util.List<String> mainUrllist=new ArrayList<String>();
	String mainUrl;
	String prodUrl="http://www.phoenix.edu/";
	String preprodUrl="https://www.preprod.aptimus.phoenix.edu/";
	String qaUrl="https://www.qa.aptimus.phoenix.edu/";
	String qaPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qa";
	String qaResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAscreenshots\\qaresize";
	String devPath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\dev";
	String devResizePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Devscreenshots\\devresize";
	String differencePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Differencescreenshots\\devdifference";
	String firstMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Merge1\\1stMerge";
	String fullMergePath="C:\\Users\\ssamal\\Documents\\screenshots\\images\\Finalmerge\\";
	String imageAfter2days="C:\\Users\\ssamal\\Documents\\screenshots\\firefox\\ImageAfter2days\\qa";
	String excelSheetPath="C:\\Users\\ssamal\\Desktop\\ForSaucelabs.xlsx";
	
	public void getUrlForImage(){
		url=driver.getCurrentUrl();
		if(url.startsWith(prodUrl)){
			new File("C:\\Users\\ssamal\\Documents\\My1stDirectory").mkdir();
			mainUrl=url.replaceAll(prodUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_");
		}else if(url.startsWith(preprodUrl)){
			mainUrl=url.replaceAll(preprodUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_");
		}else if(url.startsWith(qaUrl)){
			mainUrl=url.replaceAll(qaUrl, "").replaceAll("\\/","").replace("?", "_").replace(":", "_");
		}
	}
	@Test(enabled=true)
	
	public void forMyReference() throws Exception{
		/*driver=new FirefoxDriver();
		Thread.sleep(3000);
		driver.get("www-new62.devint.phoenix.edu/programs/continuing-education/individual-courses.html");
		String url=driver.getCurrentUrl();
		if(url.startsWith("http://www-new62.devint.phoenix.edu/")){
			System.out.println(url.replaceAll("http://www-new62.devint.phoenix.edu/", "").replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", ""));
		}*/
		//System.out.println(driver.getCurrentUrl().replaceAll("\\/","").replace("?", "_").replace(":", "_").replace("|", ""));
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssamal\\Desktop\\chromedriver_win32\\chromedriver.exe");
		System.setProperty("webdriver.ie.driver", "C:\\Users\\ssamal\\Desktop\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe");
		
		driver=new ChromeDriver();
		//driver=new FirefoxDriver();
		//driver=new InternetExplorerDriver();
		Thread.sleep(2000);
		
		driver.get("http://www-new62.qa.phoenix.edu/admissions/transfer_information/transfer-guides/courses/co.html");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		
		/*File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(src1, new File("C:\\Users\\ssamal\\Documents\\screenshots\\IEscreenshot21.png"));*/
		//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot2.png");
		Screenshot screenshot = new AShot().shootingStrategy(
			    new ViewportPastingStrategy(1000)).takeScreenshot(driver);
		
		//final BufferedImage image = screenshot.getImage();
		  ImageIO.write(screenshot.getImage(), "PNG", new File("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot9121.png"));
		  resize("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot9121.png", "C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot9121resize.png", 1407, 1901);
		driver.quit();
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver=new FirefoxDriver();
		Thread.sleep(2000);
		driver.get("http://www-new62.qa.phoenix.edu/admissions/transfer_information/transfer-guides/courses/co.html");
		Thread.sleep(4000);
		//Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS).save("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot2.png");
		Screenshot screenshot1 = new AShot().shootingStrategy(
			    new ViewportPastingStrategy(500)).takeScreenshot(driver);
		
		final BufferedImage image1 = screenshot1.getImage();
		  ImageIO.write(image1, "PNG", new File("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot1234.png"));
		  resize("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot1234.png", "C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot1234resize.png", 1407, 1901);
		driver.quit();
		compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot1234resize.png", "C:\\Users\\ssamal\\Documents\\screenshots\\chromescreenshot9121resize.png", "C:\\Users\\ssamal\\Documents\\screenshots\\differencechromescreenshot12.png");
		
	}
	
	@Test(enabled=false)
	public void createFolder() throws Exception{
		driver=new FirefoxDriver();
		driver.get("http://www.phoenix.edu/");
		//getUrlForImage();
		FileUtils.cleanDirectory(new File("C:\\Users\\ssamal\\Documents\\My1stDirectory"));
		Thread.sleep(3000);
		driver.quit();
		
	}
	
	
	public void comparisonInChrome(){
		
		
	}

}

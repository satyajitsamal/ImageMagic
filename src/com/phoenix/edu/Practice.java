package com.phoenix.edu;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Practice extends ImageComparison{
	//@Test
public  void main() throws Exception{
		
		java.util.Date currDate=new java.util.Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH-mm-ss.SSS");
		String dateAndTime = dateFormat.format(currDate);
		System.out.println(dateAndTime);
	driver=new FirefoxDriver();
	driver.get("http://docs.casperjs.org/en/latest/installation.html");
	Thread.sleep(4000);
	java.io.File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle().trim()+dateAndTime+".png"));
	//resize("C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle().trim()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle().trim()+"resize.png", 1407, 880);
	driver.get("http://docs.casperjs.org/en/latest/installation.html");
	Thread.sleep(4000);
	java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src1, new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle().trim()+dateAndTime+".png"));
	//resize("C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle().trim()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle().trim()+"resize.png", 1407, 880);
	//compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle().trim()+"resize.png", "C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle().trim()+"resize.png", "C:\\Users\\ssamal\\Documents\\screenshots\\difference"+driver.getTitle().trim()+"resize12345.png");
    compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle().trim()+dateAndTime+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle().trim()+dateAndTime+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\inequality"+driver.getTitle().trim()+dateAndTime+".png");
    }
	
	@Test
	public void main1() throws Exception{
		driver=new FirefoxDriver();
		driver.get("https://author.qa.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		java.io.File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle()+".png"));
		//resize("C:\\Users\\ssamal\\Documents\\screenshots\\first"+driver.getTitle()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\firstresize"+driver.getTitle()+".png", 1407, 1901);
		
		driver=new FirefoxDriver();
		driver.get("https://author.devint.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1, new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle()+".png"));
		//resize("C:\\Users\\ssamal\\Documents\\screenshots\\second"+driver.getTitle()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\secondresize"+driver.getTitle()+".png", 1407, 1901);
		
		compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\second"+"Accessibility"+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\first"+"Accessibility"+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\differenceresize"+".png");
	}
}


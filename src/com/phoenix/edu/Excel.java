package com.phoenix.edu;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Excel {
	WebDriver driver;
	public void takeScreenshot(String path) throws Exception{
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	   FileUtils.copyFile(src, new File(path));
	}
@Test
public void excel() throws Exception{
	//driver=new FirefoxDriver();
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\ssamal\\Desktop\\chromedriver_win32\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://author.qa.aptimus.phoenix.edu/siteadmin#");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.id("username")).sendKeys("admin");
	driver.findElement(By.id("password")).sendKeys("admin");
	driver.findElement(By.id("submit-button")).click();
	Thread.sleep(2000);
	takeScreenshot("C:\\Users\\ssamal\\Documents\\imagegoogle"+driver.getTitle()+".png");
	
}
@Test
public void excel1() throws Exception{
	driver=new FirefoxDriver();
	driver.get("https://author.qa.aptimus.phoenix.edu/siteadmin#");
	driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	driver.findElement(By.id("username")).sendKeys("admin");
	driver.findElement(By.id("password")).sendKeys("admin");
	driver.findElement(By.id("submit-button")).click();
	Thread.sleep(2000);
	takeScreenshot("C:\\Users\\ssamal\\Documents\\imagegoogle"+driver.getTitle()+".png");
	
}
}

package com.phoenix.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class VideoRecording extends Im4java {
	WebDriver driver;
	@Test
	public void  videoRecording() throws Exception{
		startRecording();
		driver=new FirefoxDriver();
		driver.get("https://author.qa.aptimus.phoenix.edu/content/altcloud/en/colleges_divisions/military/military-financial-options/post-9-11-g-i-bill.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		String title=driver.getTitle().replaceAll("\\/","");
		System.out.println(title);
		Thread.sleep(3000);
		stopRecording();
		
		
	}

}

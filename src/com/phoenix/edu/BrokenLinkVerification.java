package com.phoenix.edu;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.testng.annotations.Test;

public class BrokenLinkVerification extends Im4java {
	WebDriver driver;
	public void verifyURLStatus(String URL) {

		org.apache.http.client.HttpClient client=HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not
			if (response.getStatusLine().getStatusCode() != 200){
				System.out.println("status code:"+response.getStatusLine().getStatusCode());
				System.out.println("bokrnurl is:"+driver.getCurrentUrl());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void brokenLinkVerification() throws Exception{
		driver=new FirefoxDriver();
		driver.get("https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/bishop_state_community_college.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		Thread.sleep(3000);
		System.out.println(driver.getTitle().trim().toLowerCase().replace("_", " ")+" transfer guides");
		java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src1,new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\qa"+driver.getTitle().trim().toLowerCase().replace("_", " ")+".png"));
		resize("C:\\Users\\ssamal\\Documents\\screenshots\\qa"+driver.getTitle().trim().toLowerCase().replace("_", " ")+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\qaresize"+driver.getTitle().trim().toLowerCase().replace("_", " ")+" transfer guides"+".png", 1407, 1901);
		System.out.println("finish");
		
		driver.get("https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/bishop_state_community_college.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		Thread.sleep(3000);
		System.out.println(driver.getTitle().trim().toLowerCase());
		java.io.File src2=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src2,new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\dev"+driver.getTitle().trim().toLowerCase()+".png"));
		resize("C:\\Users\\ssamal\\Documents\\screenshots\\dev"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\devresize"+driver.getTitle().trim().toLowerCase()+".png", 1407, 1901);
		System.out.println("finish");
		
		compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\devresize"+driver.getTitle().trim().toLowerCase()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\qaresize"+"bishop state community college transfer guides"+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\diffresize"+driver.getTitle().trim().toLowerCase()+".png");
	}
	
	@Test
	public void verifyLink(){
		String accessbilityurlinQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled";
		String bussinessFacultyurlQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/faculty/our_faculty/faculty-profiles-business.html?wcmmode=disabled";
		String doctorurlQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/faculty/become-a-faculty-member/doctoral.html?wcmmode=disabled";
	}

}

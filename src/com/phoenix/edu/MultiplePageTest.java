package com.phoenix.edu;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.http.HttpClient;
import org.openqa.selenium.remote.http.HttpRequest;
import org.testng.annotations.Test;

import com.gargoylesoftware.htmlunit.javascript.host.URL;

public class MultiplePageTest extends Im4java{
	WebDriver driver;
	String accessbilityurlinQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled";
	String bussinessFacultyurlQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/faculty/our_faculty/faculty-profiles-business.html?wcmmode=disabled";
	String doctorurlQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/faculty/become-a-faculty-member/doctoral.html?wcmmode=disabled";
	
	String accessbilityurlinDev="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled";
	String bussinessFacultyurlDev="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/faculty/our_faculty/faculty-profiles-business.html?wcmmode=disabled";
	String doctorurlDev="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/faculty/become-a-faculty-member/doctoral.html?wcmmode=disabled";
	String title;
	File src;
	FileInputStream fis;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	public void doLogin(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
	}
	
	public boolean verifyURLStatus(String URL) {

		org.apache.http.client.HttpClient client=HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not,
			if (response.getStatusLine().getStatusCode() != 200){
				System.out.println("status code:"+response.getStatusLine().getStatusCode());
				System.out.println("bokrnurl is"+driver.getCurrentUrl());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	java.util.List<String> titlelist=new ArrayList<String>();
	@Test
	public void checkMultiplePage() throws Exception{
		//driver=new FirefoxDriver();
		DesiredCapabilities caps=DesiredCapabilities.firefox();
		caps.setBrowserName("firefox");
		caps.setPlatform(Platform.WINDOWS);
		driver=new RemoteWebDriver(new java.net.URL("http://10.96.63.0:5555/wd/hub"), caps);
		driver.get("http://google.com");
		//takeScreenshot("C:\\Users\\skajekar\\Documents\\Google.png");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(src, new File("\\Vapw20000000220\\aem6.2uatimages\\Google.png"));
	}
	//@Test
	public void checkMultiplePage1() throws Exception{
		driver=new FirefoxDriver();
		driver.get("http://facebook.com");
	}

}

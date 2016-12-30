package com.phoenix.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class VerifyAllLinks extends Im4java {
	static WebDriver driver;
	
	public static void clickAllHyperLinksByTagName(String path) throws Exception{
	    int numberOfElementsFound = getNumberOfElementsFound(By.xpath(path));
	    System.out.println(numberOfElementsFound);
	    for (int pos = 1; pos < numberOfElementsFound; pos++) {
	    	if(getElementWithIndex(By.xpath(path), pos).isDisplayed()){
	        getElementWithIndex(By.xpath(path), pos).click();
	    	}else{
	    		System.out.println("Link is not visible");
	    	}
	        driver.navigate().back();
	        Thread.sleep(3000);
	    }
	}

	public static int getNumberOfElementsFound(By by) {
	    return driver.findElements(by).size();
	}

	public static WebElement getElementWithIndex(By by, int pos) {
	    return driver.findElements(by).get(pos);
	}
	@Test
	public void verifyAllLinks() throws Exception{
		driver=new FirefoxDriver();
		driver.get("http://www.phoenix.edu/");
		/*driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();*/
		Thread.sleep(6000);
		/*WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("html/body/footer/div/nav/div[1]/a/img")));*/
		clickAllHyperLinksByTagName("//nav[@class='container-footer']//a");
		//river.findElement(By.xpath("//nav[@class='container-footer']/div[1]/a")).click();
		
	}
	
	}



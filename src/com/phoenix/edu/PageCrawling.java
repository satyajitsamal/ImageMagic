package com.phoenix.edu;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PageCrawling {
WebDriver driver;
@Test(enabled=true)
	public void pageCrawling() throws Exception{
     driver=new FirefoxDriver();
     driver.get("http://www-new62.uat.phoenix.edu/admissions/transfer_information/previous_college_education.html");
	int noOfLinks=driver.findElements(By.xpath("//a")).size();
	 System.out.println(noOfLinks);	
	 for(int pos=0;pos<noOfLinks;pos++){
     if(driver.findElements(By.xpath("//a")).get(pos).isDisplayed()){
    	 driver.findElements(By.xpath("//a")).get(pos).click();
    	 System.out.println("Title is:" +driver.getTitle());
     }else{
 		System.out.println("Link is not visible");
	 }
     driver.navigate().back();
     Thread.sleep(3000);
	}
}

@Test(enabled=false)
public void checkWindow() throws Exception{
	driver=new FirefoxDriver();
	driver.get("https://www.irctc.co.in/eticketing/loginHome.jsf");
	Thread.sleep(2000);
	Actions act=new Actions(driver);
	act.moveToElement(driver.findElement(By.xpath(".//*[@id='irctc_tourism']/font"))).perform();
	driver.findElement(By.xpath(".//*[@id='bluemenu']/ul/li[8]/ul/li[4]/a")).click();
	Set<String> windows=driver.getWindowHandles();
	if(windows.size()!=1){
		Iterator<String> iterator = windows.iterator();
		String parent=iterator.next();
		String child=iterator.next();
		driver.switchTo().window(child);
		driver.close();
		driver.switchTo().window(parent);
	}
}
}


package com.phoenix.edu;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ApplitoolsImageComparison {
	
	//public void  imageComparison(){
	public static void main(String[] args){
		
		WebDriver driver=new FirefoxDriver();
		/*driver.get("https://google.com");
		driver.close();*/
		/*Eyes eye=new Eyes();
	  try{
		eye.setApiKey("1983VdeAXqd107D6rdroy7LtRZL8utnLSavls9SLAZzJEw110");
		driver = eye.open(driver, "Applitools", "Test Web Page", new RectangleSize(1024, 768));
		driver.get("https://author.qa.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Accessibility')]")));
		//eye.checkWindow("QAPage");
		//driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		//new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Accessibility')]")));
		//eye.checkWindow("DevPage");
		eye.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}*/
		

	}
}
	



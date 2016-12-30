package com.phoenix.edu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class PageCount20 extends Im4java {
	WebDriver driver;
	String accessbilityurlinQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled";
	String bussinessFacultyurlQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/faculty/our_faculty/faculty-profiles-business.html?wcmmode=disabled";
	String doctorurlQA="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/faculty/become-a-faculty-member/doctoral.html?wcmmode=disabled";
	String qaurl1="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/admission_requirements.html?wcmmode=disabled";
	String qaurl2="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/foreign-education/evaluation-translation-services.html?wcmmode=disabled";
	String qaurl3="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/foreign-education/faqs.html?wcmmode=disabled";
	String qaurl4="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/foreign-education/english-proficiency-exam.html?wcmmode=disabled";
	String qaurl5="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/military_experience.html?wcmmode=disabled";
	String qaurl6="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/national_testing_programs.html?wcmmode=disabled";
	String qaurl7="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/previous_college_education.html?wcmmode=disabled";
	String qaurl8="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/articulation.html?wcmmode=disabled";
	String qaurl9="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer_credit/corporate_articulation.html?wcmmode=disabled";
	String qaurl10="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/bishop_state_community_college.html?wcmmode=disabled";
	String qaurl11="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/central_alabama_community_college.html?wcmmode=disabled";
	String qaurl12="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/columbia_southern_univ.html?wcmmode=disabled";
	String qaurl13="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/education_america_se_college_of_tech_mobile.html?wcmmode=disabled";
	String qaurl14="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/h_councill_trenholm_state_technical_college.html?wcmmode=disabled";
	String qaurl15="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/ar/arkansas_northeastern_college.html?wcmmode=disabled";
	String qaurl16="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/ar/college_of_the_ouachitas.html?wcmmode=disabled	";
	String qaurl17="https://author.qa.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/ar/mid_south_community_college.html?wcmmode=disabled";
	
	
	String accessbilityurlinDev="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled";
	String bussinessFacultyurlDev="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/faculty/our_faculty/faculty-profiles-business.html?wcmmode=disabled";
	String doctorurlDev="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/faculty/become-a-faculty-member/doctoral.html?wcmmode=disabled";
	String devurl1="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/admission_requirements.html?wcmmode=disabled";
	String devurl2="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/foreign-education/evaluation-translation-services.html?wcmmode=disabled";
	String devurl3="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/foreign-education/faqs.html?wcmmode=disabled";
	String devurl4="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/foreign-education/english-proficiency-exam.html?wcmmode=disabled";
	String devurl5="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/military_experience.html?wcmmode=disabled";
	String devurl6="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/national_testing_programs.html?wcmmode=disabled";
	String devurl7="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/previous_college_education.html?wcmmode=disabled";
	String devurl8="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/articulation.html?wcmmode=disabled";
	String devurl9="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer_credit/corporate_articulation.html?wcmmode=disabled";
	String devurl10="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/bishop_state_community_college.html?wcmmode=disabled";
	String devurl11="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/central_alabama_community_college.html?wcmmode=disabled";
	String devurl12="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/columbia_southern_univ.html?wcmmode=disabled";
	String devurl13="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/education_america_se_college_of_tech_mobile.html?wcmmode=disabled";
	String devurl14="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/al/h_councill_trenholm_state_technical_college.html?wcmmode=disabled";
	String devurl15="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/ar/arkansas_northeastern_college.html?wcmmode=disabled";
	String devurl16="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/ar/college_of_the_ouachitas.html?wcmmode=disabled	";
	String devurl17="https://author.devint.aptimus.phoenix.edu/content/altcloud/en/admissions/transfer_information/transfer-guides/courses/ar/mid_south_community_college.html?wcmmode=disabled";
	
	String title;
	public void doLogin(){
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
	}
	
	java.util.List<String> titlelist=new ArrayList<String>();
	@Test
	public void checkMultiplePage() throws Exception{
		String[] str={accessbilityurlinQA,bussinessFacultyurlQA,doctorurlQA,qaurl1,qaurl2,qaurl3,qaurl4,qaurl5,qaurl6,qaurl7
				,qaurl8,qaurl9,qaurl10,qaurl11,qaurl12,qaurl13,qaurl14,qaurl15,qaurl16,qaurl17};
		String[] str1={accessbilityurlinDev,bussinessFacultyurlDev,doctorurlDev,devurl1,devurl2,devurl3,devurl4,devurl5,devurl6,devurl7,
				devurl8,devurl9,devurl10,devurl11,devurl12,devurl13,devurl14,devurl15,devurl16,devurl17};
		java.util.List<String> qaUrl=new ArrayList<String>();
		qaUrl.addAll(Arrays.asList(str));
		java.util.List<String> devUrl=new ArrayList<String>();
		devUrl.addAll(Arrays.asList(str1));
		
		driver=new FirefoxDriver();
		driver.get("https://author.qa.aptimus.phoenix.edu/siteadmin#");
		doLogin();
		
		for(int i=0;i<qaUrl.size();i++){
			driver.get(qaUrl.get(i)); 	
			title=driver.getTitle();
			titlelist.add(title);
			Thread.sleep(3000);
			java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src1,new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAScreenshots\\qa"+driver.getTitle().trim()+".png"));
			resize("C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAScreenshots\\qa"+driver.getTitle().trim()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAScreenshots\\qaresize"+driver.getTitle().trim()+".png", 1407, 1901);
			System.out.println("finish");
		}
		driver.get("https://author.devint.aptimus.phoenix.edu/siteadmin#");
		doLogin();
		
		for(int j=0;j<devUrl.size();j++){
			driver.get(devUrl.get(j));
			Thread.sleep(3000);
			java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src1,new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\images\\DevScreenshots\\dev"+driver.getTitle().trim()+".png"));
			resize("C:\\Users\\ssamal\\Documents\\screenshots\\images\\DevScreenshots\\dev"+driver.getTitle().trim()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\images\\DevScreenshots\\devresize"+driver.getTitle().trim()+".png", 1407, 1901);
			System.out.println("finish");
		}
		
		/*String[] titles={"Accessibility","Become a Doctoral Faculty Member","Business Faculty Profiles"};
		java.util.List<String> titlelist=new ArrayList<String>();
		titlelist.addAll(Arrays.asList(titles));*/
		
		for(int k=0;k<titlelist.size();k++){
			compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\images\\DevScreenshots\\devresize"+titlelist.get(k)+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\images\\QAScreenshots\\qaresize"+titlelist.get(k)+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\images\\Difference Screenshots\\devdifference"+titlelist.get(k)+".png");
			System.out.println("comparing between"+"devresize"+titlelist.get(k)+".png");
		}
	}

}



package com.phoenix.edu;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ImageComparison {
	WebDriver driver;
	int i,j;
	String title;
	java.util.List<String> substringlst=new ArrayList<String>();
	public void resize(String inputImagePath,
            String outputImagePath, int scaledWidth, int scaledHeight)
            throws IOException {
        // reads input image
        File inputFile = new File(inputImagePath);
        BufferedImage inputImage = ImageIO.read(inputFile);
 
        // creates output image
        BufferedImage outputImage = new BufferedImage(scaledWidth,
                scaledHeight, inputImage.getType());
 
        // scales the input image to the output image
        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
        g2d.dispose();
 
        // extracts extension of output file
        /*String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);*/
 
        // writes to output file
        ImageIO.write(outputImage, "png", new File(outputImagePath));
    }
	
	public boolean compareScreenshot(String exp, String cur, String diff) throws Exception{
		CompareCmd compare=new CompareCmd();
		compare.setErrorConsumer(StandardStream.STDERR);
		  IMOperation cmpOp = new IMOperation();
		  cmpOp.metric("mae");
		 
		  cmpOp.addImage(exp);
		  cmpOp.addImage(cur);
		  cmpOp.addImage(diff);
		  
		  
		    try {
			    // Do the compare
			    compare.run(cmpOp);
			    System.out.println("True");
			    return true;
			  }
			  catch (Exception ex) {
				  System.out.println("false");
			    return false;
			  }
		
	}
	//@Test
	public void imageComparison() throws Exception{
		Properties prop=new Properties();
		Set<Object> keys=prop.keySet();
		//InputStream input = App3.class.getClassLoader().getResourceAsStream("");
		prop.load(getClass().getClassLoader().getResourceAsStream("config.properties"));
		driver=new FirefoxDriver();
		//for(i=0;i<2;i++){
		System.out.println(prop.getProperty("url1"));
			driver.get(prop.getProperty("url1"));
		//}
		/*String[] str={"https://www.google.com","https://www.facebook.com","https://www.flipkart.com"};
		String[] str1={"https://www.google.com","https://www.facebook.com","https://www.flipkart.com"};
		java.util.List<String> url=new ArrayList<String>();
		url.addAll(Arrays.asList(str));
		java.util.List<String> url1=new ArrayList<String>();
		url1.addAll(Arrays.asList(str1));
		driver=new FirefoxDriver();
		for(i=0;i<url.size();i++){
			//driver=new FirefoxDriver();
			driver.get(url.get(i));
			title=driver.getTitle().trim();
			System.out.println(title);
			substringlst.add(title);
			java.io.File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src,new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\1"+driver.getTitle().trim()+".png"));
			resize("C:\\Users\\ssamal\\Documents\\screenshots\\1"+driver.getTitle().trim()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\1resize"+driver.getTitle().trim()+".png", 1407, 880);
			Thread.sleep(2000);
		}
		driver=new FirefoxDriver();
			for(j=0;j<url1.size();j++){
				System.out.println("jjjjjj");
				//driver=new FirefoxDriver();
				driver.get(url1.get(j));
				java.io.File src1=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				FileUtils.copyFile(src1,new java.io.File("C:\\Users\\ssamal\\Documents\\screenshots\\2"+driver.getTitle().trim()+".png"));
				resize("C:\\Users\\ssamal\\Documents\\screenshots\\2"+driver.getTitle().trim()+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\2resize"+driver.getTitle().trim()+".png", 1407, 880);
				System.out.println("finish");
				
				
			}
			String[] substring={"google.co","facebook.","flipkart."};
			java.util.List<String> substringlst=new ArrayList<String>();
			substringlst.addAll(Arrays.asList(substring));
			//resize("C:\\Users\\ssamal\\Documents\\screenshots\\1"+url.get(i).substring(12, 21)+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\1resize"+url.get(i).substring(12, 21)+".png", 1407, 880);
			//resize("C:\\Users\\ssamal\\Documents\\screenshots\\2"+url1.get(j).substring(12, 21)+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\2resize"+url1.get(j).substring(12, 21)+".png", 1407, 880);
			//compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\2resize"+"google.co"+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\1resize"+"google.co"+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\differenceresize"+"google.co"+".png");
		 for(int k=0;k<substringlst.size();k++){
			 compareScreenshot("C:\\Users\\ssamal\\Documents\\screenshots\\2resize"+substringlst.get(k)+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\1resize"+substringlst.get(k)+".png", "C:\\Users\\ssamal\\Documents\\screenshots\\differenceresize"+substringlst.get(k)+".png"); 
		 }*/
	}
}

package com.phoenix.edu;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;





public class ScreenshotComparison {
	static WebDriver driver;
	static BufferedImage imageA;
	static BufferedImage imageB;
	
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
	
	public static boolean IsPngEquals(File pngFile, byte[] pngBytes) throws IOException {
	     imageA = ImageIO.read(pngFile);

	    ByteArrayInputStream inStreamB = new ByteArrayInputStream(pngBytes);
	     imageB = ImageIO.read(inStreamB);
	    inStreamB.close();

	    DataBufferByte dataBufferA = (DataBufferByte)imageA.getRaster().getDataBuffer();
	    DataBufferByte dataBufferB = (DataBufferByte)imageB.getRaster().getDataBuffer();

	    if (dataBufferA.getNumBanks() != dataBufferB.getNumBanks()) {
	        return false;
	    }

	    for (int bank = 0; bank < dataBufferA.getNumBanks(); bank++) {
	        if (!Arrays.equals(dataBufferA.getData(bank), dataBufferB.getData(bank))) {
	            return false;
	        }
	    }

	    return true;
	}
	
	public static BufferedImage getDifferenceImage(File pngFile,byte[] pngBytes) throws Exception {
	    // convert images to pixel arrays...
		imageA = ImageIO.read(pngFile);
	    final int w = imageA.getWidth(),
	            h = imageB.getHeight(), 
	            highlight = Color.MAGENTA.getRGB();
	    final int[] p1 = imageA.getRGB(0, 0, w, h, null, 0, w);
	    final int[] p2 = imageB.getRGB(0, 0, w, h, null, 0, w);
	    
	    // compare img1 to img2, pixel by pixel. If different, highlight img1's pixel...
	    for (int i = 0; i < p1.length; i++) {
	        if (p1[i] != p2[i]) {
	            p1[i] = highlight;
	        }
	    }
	    // save img1's pixels to a new BufferedImage, and return it...
	    // (May require TYPE_INT_ARGB)
	    final BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	    out.setRGB(0, 0, w, h, p1, 0, w);
	    System.out.println("difference in images");
	    return out;
	}
	
	 public static void subtractImages(BufferedImage image1, BufferedImage image2) throws IOException {
		    BufferedImage image3 = new BufferedImage(image1.getWidth(), image1.getHeight(), image1.getType());
		    int color;
		    for(int x = 0; x < image1.getWidth(); x++)
		        for(int y = 0; y < image1.getHeight(); y++) {
		            color = Math.abs(image2.getRGB(x, y) - image1.getRGB(x, y));                
		            image3.setRGB(x, y, color);
		        }
		     ImageIO.write(image3, "jpg",  new File("C:\\Users\\ssamal\\Documents\\difference.jpg"));
		 }
	 
	 public static BufferedImage getDifferenceImage(BufferedImage img1, BufferedImage img2) {
		    int width1 = img1.getWidth(); // Change - getWidth() and getHeight() for BufferedImage
		    int width2 = img2.getWidth(); // take no arguments
		    int height1 = img1.getHeight();
		    int height2 = img2.getHeight();
		    if ((width1 != width2) || (height1 != height2)) {
		        //System.err.println("Error: Images dimensions mismatch");
		        //System.exit(1);
		    }

		    // NEW - Create output Buffered image of type RGB
		    BufferedImage outImg = new BufferedImage(width1, height1, BufferedImage.TYPE_INT_RGB);

		    // Modified - Changed to int as pixels are ints
		    int diff;
		    int result; // Stores output pixel
		    for (int i = 0; i < height1; i++) {
		        for (int j = 0; j < width1; j++) {
		            int rgb1 = img1.getRGB(j, i);
		            int rgb2 = img2.getRGB(j, i);
		            int r1 = (rgb1 >> 16) & 0xff;
		            int g1 = (rgb1 >> 8) & 0xff;
		            int b1 = (rgb1) & 0xff;
		            int r2 = (rgb2 >> 16) & 0xff;
		            int g2 = (rgb2 >> 8) & 0xff;
		            int b2 = (rgb2) & 0xff;
		            diff = Math.abs(r1 - r2); // Change
		            diff += Math.abs(g1 - g2);
		            diff += Math.abs(b1 - b2);
		            diff /= 3; // Change - Ensure result is between 0 - 255
		            // Make the difference image gray scale
		            // The RGB components are all the same
		            result = (diff << 16) | (diff << 8) | diff;
		            outImg.setRGB(j, i, result); // Set result
		        }
		    }
		    return outImg;
	 }
	
	public static void main(String[] args) throws Exception{
		driver=new FirefoxDriver();
		driver.get("https://author.qa.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		
		new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Accessibility')]")));
		Thread.sleep(4000);
		
		File fileqa=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		
		FileUtils.copyFile(fileqa, new File("C:\\Users\\ssamal\\Documents\\QaScreenshot2.png"));
		
		
		driver=new FirefoxDriver();
		driver.get("https://author.devint.aptimus.phoenix.edu/content/altcloud/en/accessibility.html?wcmmode=disabled");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.id("submit-button")).click();
		
		//new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[contains(.,'Education that meets')]")));
		Thread.sleep(4000);
		
		byte[] filedev1=((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
		File filedev=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(filedev, new File("C:\\Users\\ssamal\\Documents\\DevScreenshot2.png"));
	
		//ScreenshotComparison scmp=new ScreenshotComparison();
		//scmp.compareScreenshot("C:\\Users\\ssamal\\Documents\\DevScreenshot2.png", "C:\\Users\\ssamal\\Documents\\QaScreenshot2.png", "C:\\Users\\ssamal\\Documents\\diffScreenshot.png");
		
		/*ImageComparison comparison=new ImageComparison(10, 10, 0.05);
		if(comparison.fuzzyEqual("C:\\Users\\ssamal\\Documents\\DevScreenshot3.png", "C:\\Users\\ssamal\\Documents\\QaScreenshot3.png", "C:\\Users\\ssamal\\Documents\\diffScreenshot1.png"))
		
		System.out.println("Images are fuzzy-equal.");*/
		
		/*File fileInput = new File("C:\\Users\\ssamal\\Documents\\DevScreenshot3.png");
        File fileOutPut = new File("C:\\Users\\ssamal\\Documents\\QaScreenshot3.png");
		
		BufferedImage bufferfileInput = ImageIO.read(fileInput);
		System.out.println("123");
        DataBuffer datafileInput = bufferfileInput.getData().getDataBuffer();
        System.out.println("456");
        int sizefileInput = datafileInput.getSize();                     
        BufferedImage bufferfileOutPut = ImageIO.read(fileOutPut);
        DataBuffer datafileOutPut = bufferfileOutPut.getData().getDataBuffer();
        int sizefileOutPut = datafileOutPut.getSize();
        System.out.println("789");
        Boolean matchFlag = true;
        if(sizefileInput == sizefileOutPut) {
        	System.out.println("1234");
           for(int i=0; i<sizefileInput; i++) {
        	   System.out.println("4567");
                 if(datafileInput.getElem(i) != datafileOutPut.getElem(i)) {
                	 System.out.println("7891");
                       matchFlag = false;
                       Assert.assertTrue(matchFlag, "Images are perfectly same");
                       System.out.println("Images are perfectly same");
                       break;
                 }
            }
        }
        else {                           
           matchFlag = false;
        Assert.assertTrue(matchFlag, "Images are not same"); 
        System.out.println("Images are not same");
     }*/
		
		if (IsPngEquals(new File("C:\\Users\\ssamal\\Documents\\QaScreenshot2.png"), filedev1)) {
		    System.out.println("equals");
		} else {
			//ImageIO.write(getDifferenceImage(new File("C:\\Users\\ssamal\\Documents\\QaScreenshot2.png"),filedev),"png",new File("C:\\Users\\ssamal\\Documents\\output.png"));
			//subtractImages(ImageIO.read(new File("C:\\Users\\ssamal\\Documents\\QaScreenshot2.png")), ImageIO.read(new File("C:\\Users\\ssamal\\Documents\\DevScreenshot2.png")));
			BufferedImage imagec=getDifferenceImage(ImageIO.read(new File("C:\\Users\\ssamal\\Documents\\QaScreenshot2.png")), ImageIO.read(new File("C:\\Users\\ssamal\\Documents\\DevScreenshot2.png")));
			ImageIO.write(imagec, "png", new File("C:\\Users\\ssamal\\Documents\\difference1.png"));
		    System.out.println("not equals");
		}
		
		
		
		
		
	}

}

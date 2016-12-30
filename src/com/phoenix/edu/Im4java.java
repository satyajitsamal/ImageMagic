package com.phoenix.edu;

import static org.monte.media.FormatKeys.EncodingKey;
import static org.monte.media.FormatKeys.FrameRateKey;
import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
import static org.monte.media.FormatKeys.MIME_AVI;
import static org.monte.media.FormatKeys.MediaTypeKey;
import static org.monte.media.FormatKeys.MimeTypeKey;
import static org.monte.media.VideoFormatKeys.CompressorNameKey;
import static org.monte.media.VideoFormatKeys.DepthKey;
import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
import static org.monte.media.VideoFormatKeys.QualityKey;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.jdt.internal.compiler.impl.ITypeRequestor;
import org.im4java.core.CompareCmd;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;
import org.monte.media.Format;
import org.monte.media.FormatKeys.MediaType;
import org.monte.media.math.Rational;
import org.monte.screenrecorder.ScreenRecorder;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Im4java {
	WebDriver driver;
	private ScreenRecorder screenRecorder;
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
			    System.out.println("equal");
			    return true;
			  }
			  catch (Exception ex) {
				  System.out.println("not equal");
			    return false;
			  }
		
	}
	
	/*public void resizeImage(BufferedImage expected,BufferedImage actual){
		BufferedImage resizedImageexpected = new BufferedImage(expected.getWidth(), expected.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g1 = resizedImageexpected.createGraphics();
		g1.drawImage(expected, 0, 0, 1407, 880, null);
		g1.dispose();
		BufferedImage resizedImageactual= new BufferedImage(actual.getWidth(), actual.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizedImageactual.createGraphics();
		g2.drawImage(actual, 0, 0, 1407, 880, null);
		g2.dispose();
		
	}*/
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
	
	public void writeDataToExcel(int rowcount,int columncount,String filepath,String Sheetname,String value) throws Exception
	{
	    try
	    {
	        FileInputStream input=new FileInputStream(filepath);
	        XSSFWorkbook wb=new XSSFWorkbook(input);
	        XSSFSheet sh=wb.getSheet(Sheetname);
	        System.out.println("sheet");
	        XSSFRow row=sh.getRow(rowcount);
	        row.createCell(columncount).setCellValue(value);
	        FileOutputStream webdata=new FileOutputStream(filepath);
	        wb.write(webdata);

	    }
	    catch(Exception e){
	    	System.out.println("sheet exception");
	    }
	}
	
	public void clearColumn(int columncount,String filepath,String Sheetname) throws Exception
	{
	    try
	    {
	        FileInputStream input=new FileInputStream(filepath);
	        XSSFWorkbook wb=new XSSFWorkbook(input);
	        XSSFSheet sh=wb.getSheet(Sheetname);
	        System.out.println("sheet");
	        for (int k=0;k<sh.getLastRowNum();k++){
	        XSSFRow row=sh.getRow(k);
	        row.getCell(columncount).setCellValue("");
	        }
	        //row.createCell(columncount).setCellValue(value);
	        FileOutputStream webdata=new FileOutputStream(filepath);
	        wb.write(webdata);

	    }
	    catch(Exception e){
	    	System.out.println("sheet exception");
	    }
	}
	
	public void mergeImages(String path1,String path2,String outpath){
		 //String filename = System.getProperty("user.home")+File.separator;
	        try {
	            BufferedImage img1 = ImageIO.read(new File(path1));
	            BufferedImage img2=ImageIO.read(new File(path2));
	            BufferedImage joinedImg = joinBufferedImage(img1,img2);
	            boolean success = ImageIO.write(joinedImg, "png", new File(outpath));
	            System.out.println("saved success? "+success);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void startRecording() throws Exception
    {    
           //File file = new File("C:\\Videos");
		 File file = new File("C:\\Users\\ssamal\\Documents\\screenshots\\Videos");
                         
           Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
           int width = screenSize.width;
           int height = screenSize.height;
                          
           Rectangle captureSize = new Rectangle(0,0, width, height);
                          
         GraphicsConfiguration gc = GraphicsEnvironment
            .getLocalGraphicsEnvironment()
            .getDefaultScreenDevice()
            .getDefaultConfiguration();

        this.screenRecorder = new SpecializedScreenRecorder(gc, captureSize,
            new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                 CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                 DepthKey, 24, FrameRateKey, Rational.valueOf(15),
                 QualityKey, 1.0f,
                 KeyFrameIntervalKey, 15 * 60),
            new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                 FrameRateKey, Rational.valueOf(30)),
            null, file, "MyVideo");
       
       this.screenRecorder.start();
    
    }

    public void stopRecording() throws Exception
    {
      this.screenRecorder.stop();
    }
	 public static BufferedImage joinBufferedImage(BufferedImage img1,BufferedImage img2) {

	        //do some calculate first
	        int offset  = 5;
	        int wid = img1.getWidth()+img2.getWidth()+offset;
	        //int height = img1.getHeight()+img2.getHeight()+offset;
	        int height = Math.max(img1.getHeight(),img2.getHeight())+offset;
	        //create a new buffer and draw two image into the new image
	        BufferedImage newImage = new BufferedImage(wid,height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2 = newImage.createGraphics();
	        java.awt.Color oldColor = g2.getColor();
	        //fill background
	        //g2.setPaint(Color.WHITE);
	        g2.fillRect(0, 0, wid, height);
	        //draw image
	        g2.setColor(oldColor);
	        g2.drawImage(img1, null, 0, 0);
	        g2.drawImage(img2, null, img1.getWidth()+offset, 0);
	        g2.dispose();
	        return newImage;
	    }
	 
	 public void takeScreenshot(String path) throws Exception{
			File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		   FileUtils.copyFile(src, new File(path));
		}
	 
	 public void takeScreenshotThroughNimbusAddon(String path) throws Exception{
//		 String selectAll = Keys.chord(Keys.CONTROL,Keys.SHIFT,"4");
//		  driver.findElement(By.tagName("html")).sendKeys(selectAll);
		 
		    Actions keyAction = new Actions(driver);     
			keyAction.keyDown(Keys.CONTROL).keyDown(Keys.SHIFT).sendKeys("4").keyUp(Keys.CONTROL).keyUp(Keys.SHIFT).build().perform();
		  Thread.sleep(3000);
		  windowHandlerForNimbus();
		  
//		  WebElement pop = driver.findElement(By.cssSelector(("button.btn_cancel")));
//		  if (pop.isDisplayed()) {
//			  pop.click();
//		}
//		   pop.click();
		  driver.navigate().refresh();
		   Thread.sleep(2000);
		  driver.findElement(By.id("done")).click();
		  Thread.sleep(2000);
		  //driver.findElement(By.xpath("//span[contains(text(),'Save as Image')]")).click();
		  WebElement element = driver.findElement(By.cssSelector("button#nsc_button_save_image.nsc-button.nsc-button-save-image"));
		  JavascriptExecutor executor = (JavascriptExecutor)driver;
		  executor.executeScript("arguments[0].click();", element);
		  Thread.sleep(2000);
		  //Runtime.getRuntime().exec("C:\\Users\\ssamal\\Desktop\\saveFile.exe " + "");
		  /*driver.close();
		  windowHandlerForNimbus();*/
		  
		  
		}

	 public void windowHandlerForPh(){
			Set<String>lst = driver.getWindowHandles();
			Iterator<String>itr = lst.iterator();
			while (itr.hasNext()) {
				String parent = itr.next();
				String newwin1 = itr.next();
				driver.switchTo().window(parent);
			}
				
				
			}
			
	 
	 public void windowHandlerForNimbus(){
			Set<String>lst = driver.getWindowHandles();
			System.out.println("no of tabs:" +lst.size());
			Iterator<String>itr = lst.iterator();
            if(lst.size()<3){
            	while (itr.hasNext()) {
    				String parent = itr.next();
    				String newwin1 = itr.next();
    				driver.switchTo().window(parent);
			}
            }
            	else{
            		
            	   while (itr.hasNext()) {
				   String parent = itr.next();
				   String newwin1 = itr.next();
				   String newwin3 = itr.next();
				   driver.switchTo().window(newwin3);
			}
				
				
			}
	 }
	 
	 public void renameFile(String oldname,String newname) throws Exception{
		 File file = new File(oldname);
		 File file2 = new File(newname);
		 if (file2.exists())
			   throw new java.io.IOException("file exists");
		 boolean success = file.renameTo(file2);
		 if (!success) {
			   System.out.println("File rename is not successfull");
			}
	 }
}
			
	 


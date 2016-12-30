package com.phoenix.edu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Color;
import org.testng.annotations.Test;

public class CombineImages {

	@Test(enabled=false)
	public void combineImage() throws Exception{
		File path = new File("C:\\Users\\ssamal\\Documents");
		//File path2 = new File("C:\\Users\\ssamal\\Documents\\GOOGLEdevpress release page");

				// load source images
				BufferedImage image = ImageIO.read(new File(path, "GOOGLEqapress release page.png"));
				BufferedImage overlay = ImageIO.read(new File(path,"GOOGLEdevpress release page.png"));

				// create the new image, canvas size is the max. of both image sizes
				int w = Math.max(image.getWidth(), overlay.getWidth());
				int h = Math.max(image.getHeight(), overlay.getHeight());
				BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

				// paint both images, preserving the alpha channels
				Graphics g = combined.getGraphics();
				g.drawImage(image, 0, 0, null);
				g.drawImage(overlay, 0, 0, null);

				// Save as new image
				ImageIO.write(combined, "PNG", new File(path, "combined.png"));
	}
	@Test
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
}

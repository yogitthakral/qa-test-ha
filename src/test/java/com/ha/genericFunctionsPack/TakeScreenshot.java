package com.ha.genericFunctionsPack;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import com.google.common.io.Files;


public class TakeScreenshot {

//A function that captures the screenshots , saves it and return the screenshot file Path
public static <T> String returnScreenShotName(T driver, String testcaseName){
	String NewFileNamePath = null;
	String htmlFilePath = null;
	try {
		System.out.println("came to take screenshot method");
		File directory = new File("./finalReport");
		DateFormat dateFormat = new SimpleDateFormat("MMM_dd_yyyy_hh_mm_ssaa");
		Date date = new Date();

		String dateName = dateFormat.format(date);


		NewFileNamePath = directory.getCanonicalPath() + "/screenshots/"+ testcaseName + "_" + dateName + "_"+ ".png";
		htmlFilePath = "./screenshots/"+ testcaseName + "_" + dateName + "_"+ ".png";

		try{
			
			System.out.println("Driver Type is :"+ driver);
			
			WebDriver augmentedDriver = new Augmenter().augment((WebDriver) driver);
			File f = ((TakesScreenshot)augmentedDriver).getScreenshotAs(OutputType.FILE);
			Files.copy(f, new File(NewFileNamePath));
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Taking Robot Screenshot since webdriver screenshot is failed.");
			java.awt.Dimension resolution = Toolkit.getDefaultToolkit().getScreenSize();
			Rectangle rectangle = new Rectangle(resolution);
			Robot robot = new Robot();
			BufferedImage bi = robot.createScreenCapture(new Rectangle(rectangle));
			ImageIO.write(bi, "png", new File(NewFileNamePath));

		}

	}
	catch (Exception e) {
		e.printStackTrace();
	} 
	
	return htmlFilePath;
}




}
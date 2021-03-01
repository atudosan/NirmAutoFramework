package io.nirmata.reports;

import com.aventstack.extentreports.MediaEntityBuilder;

import io.nirmata.enums.ConfigProperties;
import io.nirmata.utils.PropertyUtils;
import io.nirmata.utils.ScreenshotUtils;

public class ExtentLogger {
	
	private ExtentLogger() {
		
	}
	
	public static void pass(String message) {
		ExtentManager.getExtentTest().pass(message);
	}
	
	public static void fail(String message) {
		ExtentManager.getExtentTest().fail(message);
	}
	
	public static void skip(String message) {
		ExtentManager.getExtentTest().skip(message);
	}
	
	public static void info(String message) {
		ExtentManager.getExtentTest().info(message);
	}
	
	public static void pass(String message, boolean isScreenshotNeeded)  {
		if(PropertyUtils.getValue(ConfigProperties.PASSEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotNeeded) {ExtentManager.getExtentTest().pass(message, 
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
		else pass(message);
	}
	
	public static void fail(String message, boolean isScreenshotNeeded)  {
		if(PropertyUtils.getValue(ConfigProperties.FAILEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotNeeded) {ExtentManager.getExtentTest().fail(message, 
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
		else fail(message);
	}
	
	public static void skip(String message, boolean isScreenshotNeeded)  {
		if(PropertyUtils.getValue(ConfigProperties.SKIPPEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotNeeded) {ExtentManager.getExtentTest().skip(message, 
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
		else skip(message);
	}
	
	public static void info(String message, boolean isScreenshotNeeded)  {
		if(PropertyUtils.getValue(ConfigProperties.SKIPPEDSTEPSSCREENSHOTS).equalsIgnoreCase("yes")
				&& isScreenshotNeeded) {ExtentManager.getExtentTest().info(message, 
						MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
		}
		else info(message);
	}

}


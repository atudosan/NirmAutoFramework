package io.nirmata.constants;

import io.nirmata.enums.ConfigProperties;
import io.nirmata.utils.PropertyUtils;

public final class FrameworkConstants {

	private FrameworkConstants() {

	}

	private static final String CONFIGFILEPATH = System.getProperty("user.dir") + "/src/test/resources/config/config.properties";
	private static final int EXPLICITWAIT = 10; 
	private static final String XLSXFILEPATH = System.getProperty("user.dir") + "/src/test/resources/xlsxFolder/DataProvide.xlsx";
	private static final String EXTENTREPORTFOLDERPATH = System.getProperty("user.dir") + "/ExtentReports";
	private static String extentReportFilePath = "";
	
	public static String getExtentReportFilePath() {
		if(extentReportFilePath.isEmpty()) {
			extentReportFilePath = createReportPath();
		}
		return extentReportFilePath;
	}

	private static String createReportPath()  {
		if(PropertyUtils.getValue(ConfigProperties.OVERRIDEREPORTS).equalsIgnoreCase("no")) {
			return EXTENTREPORTFOLDERPATH + System.currentTimeMillis()+"/index.html";
		}
		else {
			return EXTENTREPORTFOLDERPATH + "/index.html";
		}	
	}

	public static String getConfigFilePath() {
		return CONFIGFILEPATH;
	}

	public static int getExplicitWait() {
		return EXPLICITWAIT;
	}

	public static String getXlsxFilePath() {
		return XLSXFILEPATH;
	}

	

}

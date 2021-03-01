	package io.nirmata.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.nirmata.constants.FrameworkConstants;
import io.nirmata.enums.ConfigProperties;
import io.nirmata.utils.PropertyUtils;

public final class ExtentReport {

	private static ExtentReports extent;

	private ExtentReport() {
	}

	public static void initReports()  {
		if(Objects.isNull(extent)) {
			ExtentSparkReporter sparkReport = new ExtentSparkReporter(FrameworkConstants.getExtentReportFilePath());
			extent = new ExtentReports();
			extent.attachReporter(sparkReport);

			sparkReport.config().setDocumentTitle("DocumentTitle");
			sparkReport.config().setTheme(Theme.DARK);
			sparkReport.config().setReportName("ReportName");

			String url = PropertyUtils.getValue(ConfigProperties.URL);
			String browser = PropertyUtils.getValue(ConfigProperties.BROWSER);
			extent.setSystemInfo("Executed on envirnoment: ", url);
			extent.setSystemInfo("Executed on browser: ", browser);
			extent.setSystemInfo("Executed on OS: ", System.getProperty("os.name"));
			extent.setSystemInfo("Executed by User: ", System.getProperty("user.name"));
		}
	}

	public static void flushReports() {
		if(Objects.nonNull(extent)) {
			extent.flush();
			ExtentManager.unload();
			try {
				Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportFilePath()).toURI());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void createTest(String testcasename) {
		ExtentManager.setExtentTest(extent.createTest(testcasename));
	}

}

package io.nirmata.tests;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.google.common.util.concurrent.Uninterruptibles;

import io.nirmata.driver.Driver;
import io.nirmata.driver.DriverManager;
import io.nirmata.enums.ConfigProperties;
import io.nirmata.utils.PropertyUtils;

public class BaseTest {

	protected BaseTest() {
	}

	@BeforeMethod
	protected void setUp() {
		Driver.initDriver(PropertyUtils.getValue(ConfigProperties.BROWSER));
		DriverManager.getDriver().manage().window().maximize();
		Uninterruptibles.sleepUninterruptibly(9, TimeUnit.SECONDS);
		DriverManager.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}


	@AfterMethod protected void tearDown() { 
		Driver.quitDriver(); 
	}


}

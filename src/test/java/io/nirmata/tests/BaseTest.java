package io.nirmata.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

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
	}


	@AfterMethod protected void tearDown() { 
		Driver.quitDriver(); 
	}


}

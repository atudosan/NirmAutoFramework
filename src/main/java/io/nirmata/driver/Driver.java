package io.nirmata.driver;

import java.net.MalformedURLException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.Uninterruptibles;

import io.nirmata.enums.ConfigProperties;
import io.nirmata.exceptions.BrowserInvocationFailedException;
import io.nirmata.factories.DriverFactory;
import io.nirmata.utils.PropertyUtils;

public final class Driver {

	private Driver() {}

	public static void initDriver(String browser)  {

		if(Objects.isNull(DriverManager.getDriver())) {
			try {
				DriverManager.setDriver(DriverFactory.getDriver(browser)); 
			} catch (MalformedURLException e) {
				throw new BrowserInvocationFailedException("Please check the capabilities of browser");
			}
			DriverManager.getDriver().get(PropertyUtils.getValue(ConfigProperties.URL));
		}
	}


	public static void quitDriver() {
		if(Objects.nonNull(DriverManager.getDriver())) {
			DriverManager.getDriver().quit();
			DriverManager.unload(); 
		} 
	}


	public static void sleep(int sec) {
		Uninterruptibles.sleepUninterruptibly(sec, TimeUnit.SECONDS);
	}

}







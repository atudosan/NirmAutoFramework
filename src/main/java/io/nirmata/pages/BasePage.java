package io.nirmata.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.nirmata.driver.DriverManager;
import io.nirmata.enums.WaitStrategy;
import io.nirmata.factories.ExplicitWaitFactory;
import io.nirmata.reports.ExtentLogger;

public class BasePage {
	
	protected void clickOn(By by, WaitStrategy waitStrategy, String elementName) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		try {
			element.click();
			ExtentLogger.info(elementName+ " was clicked", true);
		} catch (Exception e) {
			ExtentLogger.fail(elementName+ " was not clicked, due to "+e, true);
		}
	}
	
	protected void sendText(By by, String text, WaitStrategy waitStrategy, String elementName){
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		try {
			element.clear();
			element.sendKeys(text);
			ExtentLogger.info( "Text ["+text+"] was sent to ["+elementName+"] textfield", true);
		} catch (Exception e) {
			ExtentLogger.fail( "Text ["+text+"] was not sent to ["+elementName+"] textfield, due to "+e, true);
		} 
	}
	
	protected void pressEnter(By by, WaitStrategy waitStrategy, String elementName){
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		try {
			element.sendKeys(Keys.ENTER);
			ExtentLogger.info( "Enter key was pressed on ["+elementName+"]", true);
		} catch (Exception e) {
			ExtentLogger.fail( "Enter key was not pressed on ["+elementName+"], due to "+e, true);
		} 
	}
	
	protected void pressArrowDownKey(int iterations, By by, WaitStrategy waitStrategy) {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		for(int i = 0; i <= iterations; i++) {
			element.sendKeys(Keys.ARROW_DOWN);
		}
	}
	
	
	
	protected void sendDigit(By by, int i, WaitStrategy waitStrategy, String elementName){
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		String digit = i+"";
		try {
			element.clear();
			element.sendKeys(digit);
			ExtentLogger.info( "Text ["+digit+"] was sent to ["+elementName+"] textfield", true);
		} catch (Exception e) {
			ExtentLogger.fail( "Text ["+digit+"] was not sent to ["+elementName+"] textfield, due to "+e, true);
		} 
	}


	protected boolean validatePresenceOfWebElementFromListByItsText(By by, WaitStrategy waitStrategy, 
			String desiredElementName) {
		boolean presenseOfElement = false;
		try {
			List<WebElement> list = DriverManager.getDriver().findElements(by);
			for (WebElement webElement : list) {
				String text = webElement.getText();
				if(text.contains(desiredElementName)) {
					presenseOfElement =  true;
					ExtentLogger.info("Element "+desiredElementName+" was found in List", true);
				} 
			}
		} catch (Exception e) {
			ExtentLogger.fail("Element "+desiredElementName+" was not present in List, due to "+e, true);
		}
		return presenseOfElement;
	}
	
	

	protected String getText(By by, WaitStrategy waitStrategy, String elementName) {
		String text = null;
		try {
			WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
			text = element.getText();
			ExtentLogger.info("Text from "+elementName+" was captured", true);
		} catch (Exception e) {
			ExtentLogger.fail("Text from "+elementName+" was not captured, due to "+e, true);
		}
		return text;
	}
	
	protected void selectOptionFromDropdown(By by, String value, WaitStrategy waitStrategy,
			String elementName) {
		try {
		WebElement element = ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
		Select select = new Select(element);
		select.selectByValue(value);
		ExtentLogger.info("["+value+"] was selected from ["+elementName+"] dropdown list", true);
		} catch (Exception e) {
			ExtentLogger.fail("["+value+"] was not selected from ["+elementName+"] dropdown list, due to"+e, true);
		}
	}



	protected String getTitle(String elementName) {
		String pageTitle = null;
		try {
			pageTitle = DriverManager.getDriver().getTitle();
			ExtentLogger.info("Title of " +elementName+ " was captured", true);
		} catch (Exception e) {
			ExtentLogger.fail("Title of " +elementName+ " was not captured, due to "+e, true);
		}
		return pageTitle;
	}

	protected void hoverOver(By by, WaitStrategy waitStrategy, String elementName) {
		Actions action = new Actions(DriverManager.getDriver());
		try {
			action.moveToElement(ExplicitWaitFactory.performExplicitWait(waitStrategy, by));
			ExtentLogger.info("Performed hover over "+elementName+" element", true);
		} catch (Exception e) {
			ExtentLogger.fail(elementName+ " was not found, due to "+e, true);
		}

	}
	
	protected boolean validateString(String expectedString, String actualString, String elementName) {
		boolean result = false;
		if (actualString.equalsIgnoreCase(expectedString)) {
			result = true;
			ExtentLogger.info(elementName+" Validation Passed! Actual String ["+actualString+"] is the same as Expected String ["
					+expectedString+"]", true);
		} else {
			ExtentLogger.fail(elementName+" Validation Failed! Actual expected string is ["+actualString+"]"
					+ " but expected was["+expectedString+"]", true);
			result = false;
		}
		return result;
	}
	




}

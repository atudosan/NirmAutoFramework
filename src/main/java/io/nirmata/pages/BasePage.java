package io.nirmata.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import io.nirmata.driver.DriverManager;
import io.nirmata.enums.WaitStrategy;
import io.nirmata.factories.ExplicitWaitFactory;
import io.nirmata.reports.ExtentLogger;

public class BasePage {

	/*
	 * private WebElement find(By by, WaitStrategy waitStrategy) { return
	 * ExplicitWaitFactory.performExplicitWait(waitStrategy, by);
	 * 
	 * }
	 */

	protected boolean checkPresenceOfWebElementFromList(By by, WaitStrategy waitStrategy, 
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
			element.sendKeys(text);
			ExtentLogger.info( "Text ["+text+"] was sent to ["+elementName+"] textfield", true);
		} catch (Exception e) {
			ExtentLogger.fail( "Text ["+text+"] was not sent to ["+elementName+"] textfield, due to "+e, true);
		} 
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



}

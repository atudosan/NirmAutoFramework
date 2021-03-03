package io.nirmata.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.google.common.util.concurrent.Uninterruptibles;

import io.nirmata.enums.WaitStrategy;

public class CreateCatalogPage extends MainNavigationBar{
	
	private final By nameTxt = By.xpath("//input[@name='name']");
	private final By addCatalogBtn = By.xpath("//button[text()='Add Catalog']");
	private final By errorNameMsg = By.xpath("//input[@name='name']/following-sibling::p");
	
	public CreateCatalogPage provideCatalogName(String catalogName) {
		sendText(nameTxt, catalogName, WaitStrategy.PRESENCE, "Catalog Name");
		return this;
	}
	
	public CatalogDescriptionPage clickOnAddCatalogBtn() {
		clickOn(addCatalogBtn, WaitStrategy.NONE, "Add Catalog Button");
		return new CatalogDescriptionPage();
	}
	
	public boolean validateCatalogNameErrorMessage(String catalogName) {
		String actualErrorMsg = getText(errorNameMsg, WaitStrategy.VISIBLE, "Actual Catalog Name Error Message");
		String expectedErrorMsg = "The name "+catalogName+" is already used";
		boolean result = validateString(expectedErrorMsg, actualErrorMsg, "CatalogNameDuplication ErrorMsg");
		Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
		return result;
	}
	
	

}

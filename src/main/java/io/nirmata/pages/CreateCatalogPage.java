package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class CreateCatalogPage extends MainNavigationBar{
	
	private final By nameTxt = By.xpath("//input[@name='name']");
	private final By addCatalogBtn = By.xpath("//button[text()='Add Catalog']");
	
	public CreateCatalogPage provideCatalogName(String catalogName) {
		sendText(nameTxt, catalogName, WaitStrategy.PRESENCE, "Catalog Name");
		return this;
	}
	
	public CatalogDescriptionPage clickOnAddCatalogBtn() {
		clickOn(addCatalogBtn, WaitStrategy.NONE, "Add Catalog Button");
		return new CatalogDescriptionPage();
	}
	
	

}

package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class CatalogsPage extends MainNavigationBar{
	
	private final By addCatalogBtn = By.xpath("//button[@type='Add Catalog']");
	private final By catalogNameInfo = By.xpath("//span[@for='name']");
	
	
	public CreateCatalogPage addNewCatalog() {
		clickOn(addCatalogBtn, WaitStrategy.VISIBLE, "Add Catalog button");
		return new CreateCatalogPage();
	}
	
	public boolean checkIfCreatedCatalogIsPresentInList(String catalogName) {
		return checkPresenceOfWebElementFromList(catalogNameInfo, WaitStrategy.PRESENCE, catalogName);
	}
	
	public String getActualOwnerOfCreatedCatalog(String catalogName) {
		String stringXpath = "//span[@for='name' and text()='"+catalogName+"']/ancestor::div[@class='card-body']/"
				+ "descendant::div[@class='text-muted']/strong";
		By ownerXpath = By.xpath(stringXpath);
		return getText(ownerXpath, WaitStrategy.NONE, "Owner of Catalog");
		
	}
	

}

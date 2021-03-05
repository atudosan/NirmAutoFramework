package io.nirmata.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.google.common.util.concurrent.Uninterruptibles;

import io.nirmata.enums.WaitStrategy;

public class CatalogsPage extends MainNavigationBar{

	private final By addCatalogBtn = By.xpath("//button[@type='Add Catalog']");
	private final By catalogNameInfo = By.xpath("//span[@for='name']");


	public CreateCatalogPage addNewCatalog() {
		clickOn(addCatalogBtn, WaitStrategy.VISIBLE, "Add Catalog button");
		return new CreateCatalogPage();
	}

	public boolean validateIfCatalogIsPresentInList(String catalogName) {
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		return validatePresenceOfWebElementFromListByItsText(catalogNameInfo, WaitStrategy.CLICKABLE, catalogName);
	}

	public boolean validateActualCatalogOwner(String catalogName, String email) {
		String stringXpath = "//span[@for='name' and text()='"+catalogName+"']/ancestor::div[@class='card-body']/"
				+ "descendant::div[@class='text-muted']/strong";
		By ownerXpath = By.xpath(stringXpath);
		String actualOwner = getText(ownerXpath, WaitStrategy.NONE, "Owner of Catalog");
		String expectedOwner = email;
		return validateString(expectedOwner, actualOwner, "Owner of catalog");
	}

	public CatalogDescriptionPage navigateToCatalog(String catalogName) {
		String xpathString = "//span[text()='"+catalogName+"']/ancestor::div[contains(@class, 'select')]";
		By catalogXpath = By.xpath(xpathString);
		clickOn(catalogXpath, WaitStrategy.VISIBLE, "Catalog Box");
		return new CatalogDescriptionPage();
	}


}

package io.nirmata.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.google.common.util.concurrent.Uninterruptibles;

import io.nirmata.enums.WaitStrategy;

public class CatalogDescriptionPage extends MainNavigationBar {
	
	private final By catalogNameInfo = By.id("model-content-panel-title");
	private final By returnCatalogListLink = By.xpath("//ul[@class='breadcrumb']/li/a");
	private final By threeDotsMenu = By.xpath("//a[contains(@class,'btn')]");
	private final By deleteCatalogMenu = By.xpath("//a[@id='deleteCatalog']");
	private final By deleteCatalogBtn = By.xpath("//button[contains(@class, 'danger')]");
	private final By addApplicationBtn = By.xpath("//button[@type='Add Application']");
			
	
	public CatalogsPage returnToCatalogListPage() {
		clickOn(returnCatalogListLink, WaitStrategy.NONE, "return to Catalog List page link");
		return new CatalogsPage();
	}
	
	public CatalogsPage deleteCatalog() {
		clickOn(threeDotsMenu, WaitStrategy.VISIBLE, "Three dots menu");
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
		clickOn(deleteCatalogMenu, WaitStrategy.PRESENCE, "Delete catalog menu button");
		Uninterruptibles.sleepUninterruptibly(5, TimeUnit.SECONDS);
		clickOn(deleteCatalogBtn, WaitStrategy.CLICKABLE, "Delete Alert Button");
		return new CatalogsPage();
	}
	
	public boolean validateNewlyCreatedCatalogName(String catalogName) {
		String actualErrorMsg = getText(catalogNameInfo, WaitStrategy.VISIBLE, "Catalog Name");
		String expectedErrorMsg = catalogName;
		boolean result = validateString(expectedErrorMsg, actualErrorMsg, "Catalog Name");
		return result;
	}
	
	public CreateApplicationPage clickAddApplication() {
		clickOn(addApplicationBtn, WaitStrategy.CLICKABLE, "Add Application Button");
		return new CreateApplicationPage();
	}
	

}

	

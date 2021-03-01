package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class CatalogDescriptionPage extends MainNavigationBar {
	
	private final By catalogNameInfo = By.id("model-content-panel-title");
	private final By returnCatalogListLink = By.xpath("//ul[@class='breadcrumb']/li/a");
	
	public String getActualCatalogName() {
		return getText(catalogNameInfo, WaitStrategy.VISIBLE, "Catalog Name");
	}
	
	public CatalogsPage returnToCatalogListPage() {
		clickOn(returnCatalogListLink, WaitStrategy.NONE, "return to Catalog List page link");
		return new CatalogsPage();
	}
	
	

}

	

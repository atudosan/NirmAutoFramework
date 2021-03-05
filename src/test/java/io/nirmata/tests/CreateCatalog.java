package io.nirmata.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.nirmata.pages.CatalogDescriptionPage;
import io.nirmata.pages.CatalogsPage;
import io.nirmata.pages.CreateCatalogPage;
import io.nirmata.pages.EmailLoginPage;
import io.nirmata.pages.HomePage;
import io.nirmata.pages.PasswordLoginPage;

public class CreateCatalog extends BaseTest {
	
	EmailLoginPage emailLoginPage = new EmailLoginPage();
	PasswordLoginPage passwordLoginPage = new PasswordLoginPage();
	CatalogsPage catalogsPage = new CatalogsPage();
	CreateCatalogPage createCatalogPage = new CreateCatalogPage();
	CatalogDescriptionPage catalogDescriptionPage = new CatalogDescriptionPage();
	HomePage homePage = new HomePage();
	SoftAssert softAssert = new SoftAssert();
	
	@Parameters({ "email", "password", "catalogName"})
	@Test(priority=1)	
	public void createCatalog(String email, String password, String catalogName) {
		emailLoginPage.provideEmail(email);
		emailLoginPage.clickOnSighInBtn();
		passwordLoginPage.providePassword(password);
		passwordLoginPage.clickOnSighInBtn();
		homePage.selectMenu("Workloads", "Catalogs");
		catalogsPage.addNewCatalog();
		createCatalogPage.provideCatalogName(catalogName);
		createCatalogPage.clickOnAddCatalogBtn();
		
		softAssert.assertTrue(catalogDescriptionPage.validateNewlyCreatedCatalogName(catalogName));
		catalogDescriptionPage.returnToCatalogListPage();
		softAssert.assertTrue(catalogsPage.validateIfCatalogIsPresentInList(catalogName));
		softAssert.assertTrue(catalogsPage.validateActualCatalogOwner(catalogName, email));
		softAssert.assertAll();
	}
		
		@Parameters({ "email", "password", "catalogName"})
		@Test(priority=2)
		public void createCatalogWithExistingName(String email, String password, String catalogName) {
			emailLoginPage.provideEmail(email).
			clickOnSighInBtn().
			providePassword(password).
			clickOnSighInBtn().
			selectMenu("Workloads", "Catalogs");
			catalogsPage.addNewCatalog().
			provideCatalogName(catalogName).
			clickOnAddCatalogBtn();
			Assert.assertTrue(createCatalogPage.validateCatalogNameErrorMessage(catalogName));
			
			
		}
		
		@Parameters({ "email", "password", "catalogName"})
		@Test(priority=3)
		public void deleteCatalog(String email, String password, String catalogName) {
			emailLoginPage.provideEmail(email).
			clickOnSighInBtn().
			providePassword(password).
			clickOnSighInBtn().
			selectMenu("Workloads", "Catalogs");
			catalogsPage.navigateToCatalog(catalogName).
			deleteCatalog();
			Assert.assertFalse(catalogsPage.validateIfCatalogIsPresentInList(catalogName));
		}
		

}

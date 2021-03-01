package io.nirmata.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.nirmata.pages.CatalogDescriptionPage;
import io.nirmata.pages.CatalogsPage;
import io.nirmata.pages.CreateCatalogPage;
import io.nirmata.pages.EmailLoginPage;
import io.nirmata.pages.PasswordLoginPage;

public class CreateCatalog extends BaseTest {
	
	@Parameters({ "email", "password", "catalogName"})
	@Test
	public void createCatalog(String email, String password, String catalogName) {
		EmailLoginPage emailLoginPage = new EmailLoginPage();
		PasswordLoginPage passwordLoginPage = new PasswordLoginPage();
		CatalogsPage catalogsPage = new CatalogsPage();
		//CreateCatalogPage createCatalogPage = new CreateCatalogPage();
		CatalogDescriptionPage catalogDescriptionPage = new CatalogDescriptionPage();
		SoftAssert softAssert = new SoftAssert();
		 
		emailLoginPage.provideEmail(email).
		clickOnSighInBtn().
		providePassword(password);
		passwordLoginPage.clickOnSighInBtn().
		selectMenu("Workloads", "Catalogs");
		catalogsPage.addNewCatalog().
		provideCatalogName(catalogName).
		clickOnAddCatalogBtn();
		
		softAssert.assertEquals(catalogDescriptionPage.getActualCatalogName(), catalogName, 
				"Expected catalog name is ["+catalogName+"] but was found ["+catalogDescriptionPage.getActualCatalogName()+"]");
		
		catalogDescriptionPage.returnToCatalogListPage();
		assertTrue(catalogsPage.checkIfCreatedCatalogIsPresentInList(catalogName));
		assertEquals(catalogsPage.getActualOwnerOfCreatedCatalog(catalogName), email);
		
		catalogsPage.logOut();
		

		}

}

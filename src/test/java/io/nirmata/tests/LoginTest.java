package io.nirmata.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.nirmata.pages.EmailLoginPage;
import io.nirmata.pages.HomePage;
import io.nirmata.pages.PasswordLoginPage;


public class LoginTest extends BaseTest{

	@Parameters({ "email", "password", "expectedAccountName", "expectedHomePageTitle" })
	@Test
	public void logInTest(String email, String password, String expectedAccountName, 
			String expectedHomePageTitle) {
		EmailLoginPage elp = new EmailLoginPage();
		PasswordLoginPage plp = new PasswordLoginPage();
		HomePage hp = new HomePage();
		SoftAssert softAssert = new SoftAssert();
		
		softAssert.assertEquals(elp.getActualHomePageTitle(), expectedHomePageTitle, "Home PAge Title Mismatch");
		elp.provideEmail(email).
		clickOnSighInBtn().
		providePassword(password);
		softAssert.assertEquals(plp.getActualAccountName(), expectedAccountName, "Account Name mismatch");
		plp.clickOnSighInBtn();
		softAssert.assertEquals(plp.getActualAccountName(), expectedAccountName, "Account Name mismatch");
		hp.logOut();
		
		softAssert.assertAll();

	}


}

package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class EmailLoginPage extends BasePage {
	
	private final By txtEmail = By.name("email");
	private final By btnSignIn = By.id("btnLogin");
	
	
	
	public EmailLoginPage provideEmail(String email) {
		sendText(txtEmail, email, WaitStrategy.VISIBLE, "Email");
		return this;
	}
	
	public PasswordLoginPage clickOnSighInBtn() {
		clickOn(btnSignIn, WaitStrategy.NONE, "Sign In");
		return new PasswordLoginPage();
	}
	
	public String getActualHomePageTitle() {
		String homePageTitle =  getTitle("Home Page");
		return homePageTitle;
	}
	
	

}
 
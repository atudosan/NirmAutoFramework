
package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class PasswordLoginPage extends BasePage{
	
	private final By txtPassword = By.name("password");
	private final By btnSignIn = By.id("btnLogin");
	private final By infoAccountName = By.xpath("//div[@class='login-title']");

	public String getActualAccountName() {
		String[] fullText = getText(infoAccountName, WaitStrategy.PRESENCE, "Account Name").split(" ");
		String accountName = fullText[fullText.length-1];
		return accountName;
	} 
	
	public PasswordLoginPage providePassword(String password) {
		sendText(txtPassword, password, WaitStrategy.VISIBLE, "Password");
		return this;
	}
	
	public HomePage clickOnSighInBtn() {
		clickOn(btnSignIn, WaitStrategy.NONE, "Sign In");
		return new HomePage();
	}
	
	
}

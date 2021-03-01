package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class HomePage extends MainNavigationBar {

	private final By infoAccountName = By.id("user-name");


	public String getActualAccountName() {
		String[] fullText = getText(infoAccountName, WaitStrategy.PRESENCE, "Account Name").split(" ");
		String accountName = fullText[fullText.length-1];
		return accountName;
	} 


}

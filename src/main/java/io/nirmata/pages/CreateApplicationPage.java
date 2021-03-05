package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.AppUpstreamType;
import io.nirmata.enums.WaitStrategy;

public class CreateApplicationPage extends MainNavigationBar {

	By appNameTxt = By.xpath("//input[@name='name']");
	By upstreamTypeInput = By.id("react-select-3-input");
	By saveBtn = By.xpath("//button[text()='Save']");


	public CreateApplicationPage typeApplicationName(String name) {
		sendText(appNameTxt, name, WaitStrategy.VISIBLE, "Application Name");
		return this;
	}



	public CreateApplicationPage selectUpstreamType(AppUpstreamType type){
		sendText(upstreamTypeInput, type.toString().toLowerCase(), WaitStrategy.PRESENCE, "Select Upstream type");
		pressEnter(upstreamTypeInput, WaitStrategy.NONE, "Upstream selection");
		return this;
	}

	public ApplicationPage clickOnSaveBtn() {
		clickOn(saveBtn, WaitStrategy.NONE, "Save Button");
		return new ApplicationPage();
	}
}

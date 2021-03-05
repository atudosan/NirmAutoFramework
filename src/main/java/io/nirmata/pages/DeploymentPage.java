package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class DeploymentPage extends MainNavigationBar{
	
	By deploymentNameBoldInfo = By.xpath("//span[@class='modelName']");
	By deploymentNameInfo = By.xpath("//div[@class='rt-td']/a");
	By threeDotsMenu = By.xpath("//button[@role='button']");
	By deleteDeploymentBtn = By.xpath("//button[@role='button']/following-sibling::ul//a");
	By deletePopUp = By.xpath("//button[contains(@class, 'danger')]");
	
	public boolean validateDeploymentBoldName(String expectedName) {
		String actualName = getText(deploymentNameBoldInfo, WaitStrategy.PRESENCE, "Deployment Name Bold");
		return validateString(expectedName, actualName, "Deployment Name Bold");
	}
	
	public boolean validateDeploymentName(String expectedName) {
		String actualName = getText(deploymentNameInfo, WaitStrategy.PRESENCE, "Deployment Name");
		return validateString(expectedName, actualName, "Deployment Name");
	}
	
	public boolean validateImageName(String deploymentName, String imageName, String tagName) {
		String expectedImageName = imageName+":"+tagName;
		String stringXpath = "//a[text()='"+deploymentName+"']/parent::div[@class='rt-td']/following-sibling::div[1]";
		By xptahPath = By.xpath(stringXpath);
		String actualImageName = getText(xptahPath, WaitStrategy.PRESENCE, "Image Name");
		return validateString(expectedImageName, actualImageName, "Image Name");
	}
	
	public ApplicationPage deleteDeployment() {
		clickOn(threeDotsMenu, WaitStrategy.CLICKABLE, "Three Dots Menu");
		clickOn(deleteDeploymentBtn, WaitStrategy.VISIBLE, "Delete Deployment Button");
		clickOn(deletePopUp, WaitStrategy.PRESENCE, "Delete Warning Info Button");
		return new ApplicationPage();
	}
	
	

}

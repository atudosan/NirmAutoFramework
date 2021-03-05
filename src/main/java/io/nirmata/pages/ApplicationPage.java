package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class ApplicationPage extends BasePage {
	
	By appNameInfo = By.xpath("//span[@class='modelName']");
	By addDeploymentBtn = By.id("addDeployment");
	By deploymentsName = By.xpath("//div[@role='gridcell']/a");
	
	

	
	public boolean validateApplicationNameText(String expectedString) {
		String actualAppName = getText(appNameInfo, WaitStrategy.PRESENCE, "App Name Info");
		return validateString(expectedString, actualAppName, "Application Name");
	}
	
	public AddDeploymentPage addDeployment() {
		clickOn(addDeploymentBtn, WaitStrategy.CLICKABLE, "Add Deployment Button");
		return new AddDeploymentPage();
	}
	
	public boolean validateDeploymentName(String name) {
		 return validatePresenceOfWebElementFromListByItsText(deploymentsName, WaitStrategy.PRESENCE, name);
	}

	public boolean validateNrOfReplicas(String deploymentName, String expectedNrOfReplicas) {
		String stringPath = "//a[text()='"+deploymentName+"']/parent::div[@role='gridcell']/following-sibling::div";
		By path = By.xpath(stringPath);
		String actualNrOfReplicas = getText(path, WaitStrategy.PRESENCE, "Nr of replicas");
		return validateString(expectedNrOfReplicas, actualNrOfReplicas, "Number of Replicas");
	}
	
	public DeploymentPage navigateToDeployment(String deploymentName) {
		String stringPath = "//a[text()='"+deploymentName+"']";
		By path = By.xpath(stringPath);
		clickOn(path, WaitStrategy.PRESENCE, "Deployment link");
		return new DeploymentPage();
	}
	
	public boolean validateDeploymantPresence(String deploymentName) {
		String stringPath = "//a[text()='"+deploymentName+"']";
		By path = By.xpath(stringPath);
		return validatePresenceOfWebElementFromListByItsText(path, WaitStrategy.PRESENCE, deploymentName);
	}
	
		
	
	

}

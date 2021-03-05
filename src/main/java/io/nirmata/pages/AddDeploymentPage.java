package io.nirmata.pages;

import org.openqa.selenium.By;

import io.nirmata.enums.WaitStrategy;

public class AddDeploymentPage extends BasePage{

	By addDeploymentNameTxt = By.id("name");
	By setNrOfReplicas = By.id("replicas");
	By nextBtn = By.xpath("//button[text()='Next']");
	By imageRepoTxt = By.className("select2-search__field");
	By repoDropDown = By.id("select2-registry-container");
	By imageNameTxt = By.id("imageRepository");
	By tagDropDown = By.id("select2-tag-container");
	By tagInputTxt = By.xpath("//input[contains(@class,'select2-search__field')]");
	By tagRefreshBtn = 	By.xpath("//div[contains(@class,'btn-refresh')]");
	By pullPolicyDd = By.id("imagePullPolicy");
	By commandTxt = By.xpath("//input[@name='command']");
	By entrypointVarsTxt = By.xpath("//input[@name='args']");
	//By addPortBtn = By.xpath("//div[@name='ports']/div[@type='button']");
	By addPortBtn = By.xpath("//div[@name='ports']");
	By portNameTxt = By.xpath("//div[@name='ports']//div[@type='button']");
	By containerPortNumber = By.id("containerPort");
	By selectProtocolBtn = By.xpath("//span[contains(@id,'protocol')]");
	By selectProtocolTxt = By.xpath("//input[@type='search']");
	By finishBtn = By.xpath("//button[text()='Finish']");

	


	public AddDeploymentPage setDeploymentName(String name) {
		sendText(addDeploymentNameTxt, name, WaitStrategy.VISIBLE, "Deployment Name ");
		return this;	
	}

	public AddDeploymentPage clickOnNextBtn() {
		clickOn(nextBtn, WaitStrategy.NONE, "Next Button");
		return this;
	}

	public AddDeploymentPage typeYourRepoSource(String text) {
		clickOn(repoDropDown, WaitStrategy.CLICKABLE, "Repo DropDown Menu");
		sendText(imageRepoTxt, text, WaitStrategy.VISIBLE, "Repository Name");
		return this;
	}

	public AddDeploymentPage setNrOfReplicas(String nrReplicas) {
		sendText(setNrOfReplicas, nrReplicas, WaitStrategy.NONE, "Number of Desired Replicas");
		return this;
	}

	public AddDeploymentPage provideImageName(String imageName) {
		sendText(imageNameTxt, imageName, WaitStrategy.NONE, "Image Name");
		return this;
	}

	public AddDeploymentPage provideImageTag(String imageTag) {
		clickOn(tagDropDown, WaitStrategy.CLICKABLE, "Tag DropDown Menu");
		//Uninterruptibles.sleepUninterruptibly(8, TimeUnit.SECONDS);
		sendText(tagInputTxt, imageTag, WaitStrategy.VISIBLE, "Tag Name");
		return this;
	}

	public ApplicationPage pressFinishBtn() {
		clickOn(finishBtn, WaitStrategy.NONE, "Finish Button");
		return new ApplicationPage();
	}

	public AddDeploymentPage selectImagePullPolicy(String policy) {
			selectOptionFromDropdown(pullPolicyDd, "IfNotPresent", WaitStrategy.VISIBLE, "Pull Image Policy");
		return this;
	}
	
	public AddDeploymentPage provideEntrypointCommand(String command) {
		sendText(commandTxt, command, WaitStrategy.NONE, "Entrypoint command");
		return this;
	}
	
	public AddDeploymentPage provideEntrypointVars(String vars) {
		sendText(entrypointVarsTxt, vars, WaitStrategy.NONE, "Entrypoint variables");
		return this;
	}
	
	public AddDeploymentPage addPort(String portName, String portType, int portNumber) {
		pressArrowDownKey(5, entrypointVarsTxt, WaitStrategy.NONE);
		pressEnter(addPortBtn, WaitStrategy.NONE, "Add Port Button");
		sendText(portNameTxt, portName, WaitStrategy.NONE, "Port Name");
		clickOn(selectProtocolBtn, WaitStrategy.NONE, "Protocol Type Button");
		sendText(selectProtocolTxt, portType, WaitStrategy.VISIBLE, "Protocol Type Textfield");
		sendDigit(containerPortNumber, portNumber, WaitStrategy.NONE, "Container Port Number");
		return this;
	}
	



}

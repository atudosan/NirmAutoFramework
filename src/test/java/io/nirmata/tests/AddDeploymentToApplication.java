package io.nirmata.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.nirmata.enums.AppUpstreamType;
import io.nirmata.pages.ApplicationPage;
import io.nirmata.pages.CatalogDescriptionPage;
import io.nirmata.pages.CatalogsPage;
import io.nirmata.pages.CreateCatalogPage;
import io.nirmata.pages.DeploymentPage;
import io.nirmata.pages.EmailLoginPage;
import io.nirmata.pages.PasswordLoginPage;

public class AddDeploymentToApplication extends BaseTest{
	
	EmailLoginPage emailLoginPage = new EmailLoginPage();
	PasswordLoginPage passwordLoginPage = new PasswordLoginPage();
	CatalogsPage catalogsPage = new CatalogsPage();
	CreateCatalogPage createCatalogPage = new CreateCatalogPage();
	CatalogDescriptionPage catalogDescriptionPage = new CatalogDescriptionPage();
	SoftAssert softAssert = new SoftAssert();
	ApplicationPage applicationPage = new ApplicationPage();
	DeploymentPage deploymentPage = new DeploymentPage();
	
	
	@Parameters({"email", "password", "catalogName", "appName", "deploymentName", 
		"nrOfReplicas", "repoName", "imageName", "imageTag", "imagePullPolicy",
		"command", "vars"})
	@Test(priority=1)
	public void CreateApp(String email, String password, String catalogName,
			String appName, String deploymentName, String nrOfReplicas, String repoName, 
			String imageName, String imageTag, String imagePullPolicy, String command,
			String vars) {
		emailLoginPage. provideEmail(email).
		clickOnSighInBtn().
		providePassword(password).
		clickOnSighInBtn().
		selectMenu("Workloads", "Catalogs");
		catalogsPage.navigateToCatalog(catalogName).
		clickAddApplication().
		typeApplicationName(appName).		
		selectUpstreamType(AppUpstreamType.CATALOG).
		clickOnSaveBtn().
		addDeployment().
		setDeploymentName(deploymentName).
		setNrOfReplicas(nrOfReplicas).
		clickOnNextBtn().
		typeYourRepoSource(repoName).
		provideImageName(imageName).
		provideImageTag(imageTag).
		selectImagePullPolicy(imagePullPolicy).
		provideEntrypointCommand(command).
		provideEntrypointVars(vars).
		pressFinishBtn();
		
		softAssert.assertTrue(applicationPage.validateApplicationNameText(appName));
		softAssert.assertTrue(applicationPage.validateNrOfReplicas(deploymentName, nrOfReplicas));
		softAssert.assertTrue(applicationPage.validateDeploymentName(deploymentName));
		
		applicationPage.navigateToDeployment(deploymentName);
		
		softAssert.assertTrue(deploymentPage.validateDeploymentBoldName(deploymentName));
		softAssert.assertTrue(deploymentPage.validateDeploymentName(deploymentName));
		softAssert.assertTrue(deploymentPage.validateImageName(deploymentName, imageName, imageTag));
		
		deploymentPage.deleteDeployment();
		
		softAssert.assertFalse(applicationPage.validateDeploymantPresence(deploymentName));
		
		softAssert.assertAll();
	}

}

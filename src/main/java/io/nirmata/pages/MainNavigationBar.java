package io.nirmata.pages;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

import com.google.common.util.concurrent.Uninterruptibles;

import io.nirmata.enums.WaitStrategy;

public class MainNavigationBar extends BasePage {

	private final By menuBtn = By.xpath("//div[@class='main-navigation-button']");
	private final By profileDropdown = By.className("dropdown-toggle");
	private final By logOutBtn = By.xpath("//a/ancestor::ul[@class='dropdown-menu']");		

	public void clickOnMenuBtn(){
		clickOn(menuBtn, WaitStrategy.CLICKABLE, "Menu Bar");
	}

	public void logOut() {
		clickOn(profileDropdown, WaitStrategy.VISIBLE, "Profile dropdown menu");
		clickOn(logOutBtn, WaitStrategy.VISIBLE, "LogOut button");
	}

	public void selectMenu(String menu, String submenu) {
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
		clickOn(menuBtn, WaitStrategy.CLICKABLE, "Menu Bar");
		String menuXtpathString = "//span[text()='"+menu+"']//ancestor::div[contains(@class, 'menu-item')]";
		By menuXtpath = By.xpath(menuXtpathString);
		String submenuXtpathString = "//span[text()='"+submenu+"']//ancestor::a";
		By submenuXtpath = By.xpath(submenuXtpathString);
		String menuName = menu+" menu";
		String submenuName = submenu+" submenu";
		clickOn(menuXtpath, WaitStrategy.CLICKABLE, menuName);
		Uninterruptibles.sleepUninterruptibly(2, TimeUnit.SECONDS);
		clickOn(submenuXtpath, WaitStrategy.CLICKABLE, submenuName);
	}
}

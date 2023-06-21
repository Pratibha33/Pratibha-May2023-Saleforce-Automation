package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class MySettingsPage extends BasePage {

	public MySettingsPage(WebDriver driver) {
		super(driver);
	}

	protected By personalLinkLocator = By.xpath("//*[@id='PersonalInfo_font']");
	protected By loginHistory = By.xpath("//span[@id='LoginHistory_font']");
	protected By loginHistoryDwnld = By
			.xpath("//a[contains(text(),'Download login history for last six months, includ')]");

	protected By displayNLayout = By.xpath("//span[@id='DisplayAndLayout_font']");
	protected By customizeTab = By.xpath("//span[@id='CustomizeTabs_font']");

	protected By customAppmenu = By.xpath("//*[@id='p4']");
	protected By reportOption = By.xpath("//*[@id='duel_select_0']");
	protected By add = By.xpath("//a[@id='duel_select_0_right']");
	protected By save = By.xpath("//*[@id='bottomButtonRow']/input[1]");

	public void clickPersonalLinkLocator() {
		buildClick(personalLinkLocator);
		clickElement(personalLinkLocator);
	}

	public void clickLoginHistory() {
		clickElement(loginHistory);
	}

	public void clickLoginHistoryDwnld() {
		clickElement(loginHistoryDwnld);

	}

	public void clickDisplayNLayout() {
		buildClick(displayNLayout);
		clickElement(displayNLayout);
	}

	public void clickCustomTab() {
		clickElement(customizeTab);
	}

	public void selectSalesForceChatter() {
		Select salesChatter = new Select(driver.findElement(customAppmenu));
		salesChatter.selectByValue("02uHs000001RCK1");
	}

	public void selectReports() {
		Select reports = new Select(driver.findElement(reportOption));
		reports.selectByValue("report");

	}

	public void clickAdd() {
		clickElement(add);
	}
	public void save () {
		clickElement(save);
	}

}

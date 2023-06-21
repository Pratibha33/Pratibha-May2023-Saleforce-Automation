package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MergeAccountsPage extends BasePage {

	public MergeAccountsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	protected By mergeAccountsLink = By.xpath("//a[normalize-space()='Merge Accounts']");
	protected By mergeAccountFilter = By.xpath("//input[@id='srch']");
	protected By chkBox1 = By.xpath("//input[@id='cid0']");
	protected By chkBox2 = By.xpath("//input[@id='cid1']");
	protected By next = By.xpath("//div[@class='pbBottomButtons']//input[@title='Next']");
	protected By merge = By.xpath("//div[@class='pbBottomButtons']//input[@title='Merge']");
	protected By recentView = By.xpath("//a[contains(text(),'Pratibha Project1')]");

	public void clickMergeAccounts() {
		clickElement(mergeAccountsLink);
	}

	public void setMergeAccountFilter(String data) {
		enterText(mergeAccountFilter, data);
	}

	public void clickchkBox1() {
		clickElement(chkBox1);
	}

	public void clickchkBox2() {
		clickElement(chkBox2);
	}

	public void clickNext() {
		clickElement(next);
	}

	public void clickMerge() {
		clickElement(merge);
	}
}

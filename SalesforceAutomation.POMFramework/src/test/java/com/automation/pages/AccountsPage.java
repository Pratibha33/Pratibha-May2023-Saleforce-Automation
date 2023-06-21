package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class AccountsPage extends BasePage {

	public AccountsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	protected By newAccount = By.xpath("//input[@title='New']");
	protected By accountName = By.id("acc2");
	protected By techType = By.xpath("//select[@id='acc6']"); // *[@id="acc6"]/option[7]
	protected By customerPriority = By.xpath("//*[@id='00NHs00000DkRX8']");
	
	protected By createNewView = By.xpath("//*[@id='filter_element']/div/span/span[2]/a[2]");
	protected By viewName = By.xpath("//input[@id='fname']"); 
	protected By viewUniqueName = By.xpath("//input[@id='devname']");
	protected By saveView = By.xpath("//*[@id=\'editPage\']/div[1]/table/tbody/tr/td[2]/input[1]");
	protected By viewMenuDropdown = By.xpath("//select[@id='fcf']");
	protected By editView = By.xpath("//a[normalize-space()='Edit']");
	protected By filterAccName = By.xpath("//select[@id='fcol1']");
	protected By filterOperator = By.xpath("//select[@id='fop1']");
	protected By filterValue = By.xpath("//input[@id='fval1']");
	protected By accountsLessThan30 = By.linkText("Accounts with last activity > 30 days");
	protected By saveNewView = By.xpath("//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]");

	public void clickNewAccount() {
		waitFluentForVisibility(newAccount);
		clickElement(newAccount);
	}

	public void setAccountName(String data) {

		enterText(accountName, data);
	}

	public void selectTechType() {
		Select selectType = new Select(driver.findElement(techType));
		selectType.selectByIndex(7);
	}

	public void selectCustomerPriority() {
		Select selectCustomerPriority = new Select(driver.findElement(customerPriority));
		selectCustomerPriority.selectByIndex(2);
	}



	public void clickCreateNewView() {
		clickElement(createNewView);
	}

	public void setViewName(String data) {
		enterText(viewName,data);
	}

	public void setViewUniqueName(String data) {
		enterText(viewUniqueName,data);
	}
	public void clickSaveView() {
		clickElement(saveView);
	}
	
	public void selectviewDropdown() {
		Select selectCustomerPriority = new Select(driver.findElement(viewMenuDropdown));
		selectCustomerPriority.selectByValue("00BHs00000CmZ7k");
	}
	
	public void clickEditView() {
		clickElement(editView);
	}
	
	public void selectFilterAaccName() {
		Select selectCustomerPriority = new Select(driver.findElement(filterAccName));
		selectCustomerPriority.selectByValue("ACCOUNT.NAME");
	}
	public void selectFilterOperator() {
		Select selectCustomerPriority = new Select(driver.findElement(filterOperator));
		selectCustomerPriority.selectByValue("c");
	}
	public void setFilterValue(String data) {
		enterText(filterValue,data);
	}
	public void clickSaveNewView() {
		clickElement(saveNewView);
	}
	
	public void closeLighningPopUp() {
		closeLighningViewPopUp();
	}
	
	public void clickAccountsLessThan30() {
		buildClick(accountsLessThan30);
		clickElement(accountsLessThan30);
	}
	
	
}
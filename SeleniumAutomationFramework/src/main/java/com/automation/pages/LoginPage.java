package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.automation.base.BaseTest;

public class LoginPage extends BaseTest {

	String userName;
	String password;
	boolean rememberMe = false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	public void login_to_SalesForce() {

		log.info("SalesForce Login page opened");
		System.out.println("Salesforce Login page opened...");

		if (userName != null) {
			By uname = By.id("username");

			waitFluentForVisibility(uname);
			// inputUsername.sendKeys(userName);
			enterText(uname, userName);
		}
		By pswd = By.xpath("//*[@name='pw']");
		WebElement inputPassword = driver.findElement(pswd);
		if (password != null) {

			waitFluentForVisibility(pswd);
			clearElement(inputPassword);
			enterText(pswd, password);
		}

		if (rememberMe) {
			By rememberUname = By.xpath("//*[@id='rememberUn']");
			clickElement(rememberUname);

		}
		By loginLocator = By.xpath("//*[@id='Login']");
		waitFluentUntilelementToBeClickable(loginLocator, 30);

		// clickElement(loginButton, loginButton.getTagName());
		clickElement(loginLocator);

	}

	public String getErrorMsg() {
		By errorLocator = By.xpath("//*[@id='error']");
		waitFluentForVisibility(errorLocator);
		WebElement alert = driver.findElement(errorLocator);
		String errorMsg = alert.getText();
		return errorMsg;
	}

}
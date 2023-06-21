package com.automation.pages;

import org.openqa.selenium.By;

import com.automation.base.BaseTest;

public class ForgotPage extends BaseTest {

	public void forgotPassword() {
		//driver.get("https://login.salesforce.com/");
		//System.out.println("Salesforce login page opened...");
		By linkLocator = By.xpath("//*[@id='forgot_password_link']");
		clickElement(linkLocator);
		// waitFluentForVisibility(linkLocator);
		By usrNameLocator = By.xpath("//*[@id='un']");
		enterText(usrNameLocator, "admin123@gmail.com");
		By continueLocator = By.xpath("// *[@id='continue']");
		clickElement(continueLocator);
		System.out.println("Test Case Forgot Password- 4A Passed");
		// closeBrowser();

	}

}

package com.automation.tests;

import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.ForgotPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utility.PropertiesUtility;

public class LoginTest extends BaseTest {
	public LoginPage loginPage = new LoginPage();
	public HomePage home = new HomePage();
	public ForgotPage forgotPage = new ForgotPage();
	protected PropertiesUtility prop = new PropertiesUtility();
	protected Properties appProp = prop.loadFile("applicationDataProperties");

	@Test
	public void loginEmptyPassword() throws InterruptedException {

		// launchBrowser("chrome");
		loginPage.setUserName("admin123@gmail.com");
		loginPage.setPassword("");
		loginPage.login_to_SalesForce();
		String expectedError = "Please enter your password.";
		String errorMsg = loginPage.getErrorMsg();
		if (errorMsg.equals(expectedError)) {
			System.out.println("testcase  Login Error Message - 1  passed");
		} else {
			System.out.println("testcase  Login Error Message - 1 failed");
		}

	}

	@Test
	public void rememberMe() {
		LoginPage page = new LoginPage();
		page.setRememberMe(true);

		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		page.setUserName(userId);
		page.setPassword(password);

		page.login_to_SalesForce();
		home.logOut();
		if (page.getUserName().equals("admin123@gmail.com")) {
			System.out.println("Test Case Check RemeberMe - 3 passed");
		} else
			System.out.println("Test Case Check RemeberMe - 3 failed");

	}

	@Test
	public void successfulLogin() {
		LoginPage page = new LoginPage();
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		page.setUserName(userId);
		page.setPassword(password);
		page.login_to_SalesForce();
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		String actualTitle = getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Login to salesforce successful");
	}

	@Test
	public void incorrectLogin() {
		LoginPage page = new LoginPage();
		page.setUserName("123");
		page.setPassword("22131");
		page.login_to_SalesForce();
		String expectedError = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String errorMsg = loginPage.getErrorMsg();
		Assert.assertEquals(errorMsg, expectedError);
	}

}

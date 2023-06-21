package com.automation.tests;

import java.util.Properties;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.CheckYourEmailPage;
import com.automation.pages.ForgotPage;
import com.automation.pages.HomePage;
import com.automation.pages.LoginPage;
import com.automation.utility.PropertiesUtility;

public class LoginTest extends BaseTest {
	public LoginPage loginPage = new LoginPage(driver);
	public HomePage home = new HomePage(driver);
	public ForgotPage forgotPage = new ForgotPage(driver);
	protected PropertiesUtility prop = new PropertiesUtility();
	protected Properties appProp = prop.loadFile("applicationDataProperties");

	//Test CAse 1 - Empty password login
	@Test
	public void loginEmptyPassword() throws InterruptedException {

		log.info("Test Case 1 : Test ID: Login Error Message 1");
		System.out.println("Test Case 1 : Test ID: Login Error Message 1");
		loginPage.setUserName("admin123@gmail.com");
		loginPage.setPassword("");
		loginPage.login_to_SalesForce();
		String expectedError = "Please enter your password.";
		String errorMsg = loginPage.getErrorMsg();
		Assert.assertEquals(expectedError, errorMsg);

	}
 //Test Case 3 Remember Me
	@Test
	public void rememberMe() {
		LoginPage page = new LoginPage(driver);
		page.setRememberMe(true);

		log.info("Test Case 3 Test Id :Check Remember Me 3 ");
		System.out.println("Test Case 3 : Test ID: Check Remember Me 3");
		
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		page.setUserName(userId);
		page.setPassword(password);

		page.login_to_SalesForce();
		home.clickLogOut();
		if (page.getUserName().equals("admin123@gmail.com")) {
			System.out.println("Test Case Check RemeberMe - 3 passed");
		} else
			System.out.println("Test Case Check RemeberMe - 3 failed");

	}

	//Test Case 2 - valid login
	@Test
	public void successfulLogin() {
		LoginPage page = new LoginPage(driver);
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		page.setUserName(userId);
		page.setPassword(password);
		page.login_to_SalesForce();
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		String actualTitle = page.getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
		log.info("Login to salesforce successful, On home page now");
		System.out.println("Login to salesforce successful, On Home Page now");
	}
	
	//Test Case 4A incorrect login
		@Test
		public void forgotPassword() {
			LoginPage page = new LoginPage(driver);
			
			page.clickForgotPswd();
			ForgotPage forgotPage = new ForgotPage(driver);
			forgotPage.setUserEmail();
			forgotPage.clickContinue();
			CheckYourEmailPage chkPage = new CheckYourEmailPage(driver);
			
			String expectedTitle = "Check Your Email | Salesforce";
			String actualTitle = chkPage.getPageTitle();
			Assert.assertEquals(actualTitle,expectedTitle);
		}

	//Test Case 4B incorrect login
	@Test
	public void incorrectLogin() {
		LoginPage page = new LoginPage(driver);
		page.setUserName("123");
		page.setPassword("22131");
		page.login_to_SalesForce();
		String expectedError = "Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		String errorMsg = loginPage.getErrorMsg();
		Assert.assertEquals(errorMsg, expectedError);
	}

}

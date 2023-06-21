package com.automation.tests;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationScripts extends BaseTest{

	@Test
	public void login_to_firebase_testscript() throws InterruptedException {
		log.info("inside the login_to_firebase_testscript test method");		// applicationDataProperties
		//studentRegistrationProperties
		PropertiesUtility pro=new PropertiesUtility();
		Properties appProp= pro.loadFile("applicationDataProperties");
	
		String userId=appProp.getProperty("login.valid.userid");
		String password=appProp.getProperty("login.valid.password");
		
		String expectedTitle = "Selenium";
		
		String actualTitle = getPageTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	
		
		By userName = By.id("email_field");
		WebElement userNameElement = driver.findElement(userName);
		waitFluentForVisibility(userName);
		enterText(userName,userId);
		
		By passwrd = By.id("password_field");
		WebElement passwordElement = driver.findElement(passwrd);
		enterText(passwrd,password);
		
		By button = By.tagName("button");
		WebElement buttonElement = driver.findElement(button);
		clickElement(button);
		
		
	
	}
	
	@Test
	public void error_login_firebase_testscript() throws InterruptedException {
	
		String expectedError = "Error : The password is invalid or the user does not have a password.";
		String expectedTitle = "Selenium";
		
		String actualTitle = getPageTitle();
		if (expectedTitle.equals(actualTitle)) {
			System.out.println("title matched");
		} else {
			System.out.println("title is not matched");
		}
		Thread.sleep(4000);
		By userName = By.id("email_field");
		//WebElement userNameElement = driver.findElement(userName);
		enterText(userName,"admin123@gmail.com");
		
		By password = By.id("password_field");
		//WebElement passwordElement = driver.findElement(password);
		enterText(password,"admin123");
		
		By button = By.tagName("button");
		//WebElement buttonElement = driver.findElement(button);
		clickElement(button);
		Thread.sleep(4000);
		
		Actions action=new Actions(driver);
		//action.keyDown(Keys.ENTER).keyUp(Keys.ENTER).perform();
		
		
		
		Alert alert = switchToAlert();
		String errorMsg = alert.getText();
		acceptAlert(alert);
		
		if (errorMsg.equals(expectedError)) {
			System.out.println("testcase passed");
		} else {
			System.out.println("testcae failed");
		}
		
	}
	
	

	

}
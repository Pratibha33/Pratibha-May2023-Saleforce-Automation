package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ForgotPage extends BasePage{
	

	protected By userEmail = By.id("un");
	protected By continueBtn = By.id("continue");
	
	
	public ForgotPage(WebDriver driver) {
		super(driver);
	}
	
	
	public void clickContinue() {
		clickElement(continueBtn);
	}
	
	public void setUserEmail() {
		enterText(userEmail, "admin123@gmail.com");
	}


	
}

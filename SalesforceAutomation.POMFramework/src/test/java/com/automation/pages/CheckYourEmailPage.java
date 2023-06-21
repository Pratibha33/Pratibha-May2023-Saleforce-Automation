package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class CheckYourEmailPage extends BasePage{
	
	protected By passwordResetText = By.xpath("//*[@id='forgotPassForm']/div/p[1]");

	public CheckYourEmailPage(WebDriver driver) {
		super(driver);
	}

	
	public String getPageTitle() {
		return driver.getTitle();
	}
}

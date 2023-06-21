package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ReportsPage extends BasePage {
	public ReportsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	protected By fromDateCalendar = By.xpath("//img[@id='ext-gen152']"); 
	protected By toDateCalendar = By.xpath("//img[@id='ext-gen154']");
	protected By fromToday = By.xpath("//button[text()='Today']");
	protected By toToday = By.xpath("//*[@id='ext-gen290']");
	
	public void clickFrom() {
		clickElement(fromDateCalendar);
	}
	
	public void clickTo() {
		clickElement(toDateCalendar);
	}
	
	public void clickFromToday() {
		buildClick(fromToday);
		System.out.println("I clicked from today date");
	}
	public void clickToToday() {
		buildClick(toToday);
	}
}

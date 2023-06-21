package com.automation.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class OpportunityPage extends BasePage {
	public OpportunityPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	protected By opportunities = By.xpath("//a[@class='listRelatedObject opportunityBlock title']");
	protected By opportunitiesDropDown = By.xpath("//*[@id='fcf']");
	protected By newOpportunity = By.xpath("//input[@title='New']");
	protected By opportunityName = By.xpath("//input[@id='opp3']");
	protected By accName = By.xpath("//input[@id='opp4']");
	By lookUpIcon = By.xpath("//*[@id='opp4_lkwgt']/img");
	By projName = By.linkText("Pratibha Project1");
	protected By type = By.xpath("//*[@id='opp5']");
	protected By leadSource = By.xpath("//*[@id='opp6']");
	protected By todayDate = By.xpath("//*[@id='ep']/div[2]/div[3]/table/tbody/tr[2]/td[4]/div/span/span/a");
	protected By stage = By.xpath("//*[@id='opp11']");
	protected By probability = By.xpath("//*[@id='opp12']");
	protected By primarySourceCampaign = By.xpath("//*[@id='opp17']");
	protected By save = By.cssSelector("#bottomButtonRow > .btn:nth-child(1)");
	protected By oppPipeline = By.xpath("//a[normalize-space()='Opportunity Pipeline']");

	public void clickOpportunities() {
		clickElement(opportunities);
	}

	public List<WebElement> getOppDropDown() {
		Select selectOptions = new Select(driver.findElement(opportunitiesDropDown));
		return selectOptions.getOptions();
	}

	public void clickNewOpportunity() {
		clickElement(newOpportunity);
	}

	public void setOpportunityName(String data) {
		enterText(opportunityName, data);
	}

	public void setAccountName(String data) {
		enterText(accName, data);
	}

	public void clickLookUpIcon() {
		clickElement(lookUpIcon);
	}

	public void clickProjName() {
		clickElement(projName);
	}

	public void selectType() {
		Select typeOptions = new Select(driver.findElement(type));
		typeOptions.selectByIndex(3);
	}

	public void selectLeadSource() {
		Select leadSourceOptions = new Select(driver.findElement(leadSource));
		leadSourceOptions.selectByIndex(2);
	}

	public void clickToday() {
		clickElement(todayDate);

	}

	public void selectStage() {
		Select leadSourceOptions = new Select(driver.findElement(stage));
		leadSourceOptions.selectByIndex(2);
	}

	public void setProbability(String data) {
		enterText(probability, data);

	}

	public void setPrimarySourceCampaign(String data) {
		enterText(primarySourceCampaign, data);

	}

	public void clickSave() {
		buildClick(save);

	}
	
	public void clickOppPipeline() {
		clickElement(oppPipeline);
	}

}

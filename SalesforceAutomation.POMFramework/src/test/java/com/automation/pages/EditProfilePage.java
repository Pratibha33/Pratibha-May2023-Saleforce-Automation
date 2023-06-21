package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EditProfilePage extends BasePage {

	public EditProfilePage(WebDriver driver) {
		super(driver);
	}

	protected By editProfileLocator = By.xpath("//img[@title='Edit Profile']");
	// protected WebElement editProfileBtn = driver.findElement(editProfileLocator);
	protected By aboutLocator = By.xpath("//*[@id='aboutTab']/a");
	protected String iframeTitle = "contactInfoContentId";
	protected By lastName = By.xpath("//*[@id='lastName']");
	protected By saveAllBtn = By.xpath("//*[@id='TabPanel']/div/div[2]/form/div/input[1]");
	protected By postBtn = By.xpath("//a[@id=\'publisherAttachTextPost\']/span[1]");
	protected By postTab = By.cssSelector("#publisherAttachTextPost > .publisherattachtext");
	protected By postText = By.xpath("//*[@id='publisherTextArea']");

	protected By shareBtn = By.xpath("//input[@id='publishersharebutton']");
	protected By fileLink = By.xpath("//*[@id='publisherAttachContentPost']/span[1]");
	protected By upload = By.xpath("//*[@id='chatterUploadFileAction']");
	protected By uploadFromComputer = By.xpath("//*[@id='chatterFile']");
	protected By fileShare = By.xpath("//*[@id='publishersharebutton']");
	protected By photoUploadLocator = By.xpath("//a[@id='uploadLink']");
	// protected WebElement photoUpload = driver.findElement(photoUploadLocator);
	protected By uploadPhotoFile = By.xpath("//*[@id='j_id0:uploadFileForm:uploadInputFile']");
	// protected WebElement uploadPhotoFileElement =
	// driver.findElement(uploadPhotoFile);
	protected By savePhotoFile = By.xpath("//*[@id='j_id0:uploadFileForm:uploadBtn']");
	protected By finalSaveImg = By.xpath("//*[@id='j_id0:j_id7:save']");

	public void clickeditProfile() {
		buildClick(editProfileLocator);
		clickElement(editProfileLocator);
	}

	public void clickAbout() {
		waitFluentForVisibility(aboutLocator);
		clickElement(aboutLocator);
	}

	public void switchToFrame1() {
		switchToFrame(iframeTitle);
	}

	public void setLastName(String data) {
		enterText(lastName, data);
	}

	public void clicksaveAllBtn() {
		waitFluentForElementToBeClickable(saveAllBtn);
		clickElement(saveAllBtn);
	}

	public void clickPostBtn() {
		clickElement(postBtn);
	}

	public void selectPostTab() {

		buildClick(postTab);
	}

	public void clickPostText(String data) {
		WebElement postTextInput = driver.findElement(postText);
		waitFluentForVisibility(postText);
		postTextInput.click();
		action.sendKeys(data).build().perform();
	}

	public void setPostText(String data) {
		enterText(postText, data);
	}

	public void clickShareBtn() {
		clickElement(shareBtn);
	}

	public void clickFileLink() {

		clickElement(fileLink);
	}

	public void clickUpload() {

		clickElement(upload);
	}

	public void setUploadFromComputer(String filePath) {
		driver.findElement(uploadFromComputer).sendKeys(filePath);
		action.moveToElement(driver.findElement(uploadFromComputer)).build().perform();
	}

	public void clickFileShare() {
		clickElement(fileShare);
	}

	public void clickPhotoUpload() {
		// waitFluentForVisibility(photoUploadLocator);
		// action.moveToElement(photoUpload).click().build().perform();
		clickElement(photoUploadLocator);
	}

	public void setUploadPhotoFile(String data) {
		enterText(uploadPhotoFile, data);
		// action.moveToElement(uploadPhotoFileElement).build().perform();
	}

	public void clickSavePhotoFile() {
		clickElement(savePhotoFile);
	}

	public void clickFinalImgSave() {
		buildClick(finalSaveImg);
		clickElement(finalSaveImg);
	}

	public void switchToDefaultContentHome() {
		switchToDefaultContent();
	}

}

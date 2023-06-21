package com.automation.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.AccountsPage;
import com.automation.pages.EditProfilePage;
import com.automation.pages.HomePage;
import com.automation.pages.MySettingsPage;

public class HomePageTest extends BaseTest {

	public HomePage home;
	public EditProfilePage editProfilePage;
	public MySettingsPage mySettingsPage;
	public AccountsPage accPage;

	public void onHomePage() {
		LoginTest test = new LoginTest();
		test.successfulLogin();
		log.info("On Home Page");
	}

	@Test
	public void selectUserName_TC_05() {

		onHomePage();
		home = new HomePage(driver);
		home.clickUserName();

		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("My Profile");
		expectedValues.add("My Settings");
		expectedValues.add("Developer Console");
		expectedValues.add("Switch to Lightning Experience");
		expectedValues.add("Logout");

		List<String> actualValues = new ArrayList<String>();

		List<WebElement> menuItemsList = driver.findElements(home.getMenuItems());
		for (WebElement menu : menuItemsList) {
			actualValues.add(menu.getText());
		}
		Assert.assertEquals(expectedValues, actualValues);
		log.info("Test Case TC_05 passed");

	}

	@Test
	public void selectMyProfile_TC_06() throws InterruptedException {
		onHomePage();
		home = new HomePage(driver);
		home.clickUserName();
		home.clickMyProfile();
		Thread.sleep(4000);
		driver.switchTo().window(driver.getWindowHandle());

		editProfilePage = new EditProfilePage(driver);
		editProfilePage.clickeditProfile();
		editProfilePage.switchToFrame1();
		editProfilePage.clickAbout();
		editProfilePage.setLastName("M");
		editProfilePage.clicksaveAllBtn();

		// come back to main page after closing iframe
		editProfilePage.switchToDefaultContent();
		editProfilePage.clickPostBtn();
		editProfilePage.selectPostTab();

		editProfilePage.clickPostText("Testing Post Function");
		editProfilePage.clickShareBtn();
		log.info("Post shared");
		editProfilePage.clickFileLink();

		editProfilePage.clickUpload();

		editProfilePage.setUploadFromComputer("C:/Pratibha/file.txt");

		editProfilePage.clickFileLink();
		log.info("File uploaded");

		Thread.sleep(2000);

		driver.switchTo().frame("uploadPhotoContentId"); // new frame is opened

		editProfilePage.setUploadPhotoFile("C:/Pratibha/Pratibha.png");
		Thread.sleep(2000);
		editProfilePage.clickSavePhotoFile();
		log.info("photo selected");

		Thread.sleep(2000);
		editProfilePage.clickFinalImgSave();
		log.info("Photo saved");

		Thread.sleep(3000);
		editProfilePage.switchToDefaultContentHome();

	}

	// Test Case 07 - Select My Settings from UserName dropdown menu
	@Test
	public void select_MySettings_TC07() throws InterruptedException {
		onHomePage();
		home = new HomePage(driver);
		home.clickUserName();
		home.clickMySettings();
		Thread.sleep(2000);
		driver.switchTo().window(driver.getWindowHandle());

		mySettingsPage = new MySettingsPage(driver);
		mySettingsPage.clickPersonalLinkLocator();
		mySettingsPage.clickLoginHistory();
		mySettingsPage.clickLoginHistoryDwnld();

		Thread.sleep(3000);
		mySettingsPage.clickDisplayNLayout();
		mySettingsPage.clickCustomTab();
		mySettingsPage.selectSalesForceChatter();
		mySettingsPage.selectReports();
		mySettingsPage.clickAdd();
		mySettingsPage.save();
	}

	// Test Case 08 - Select Developers Console Option from userMenu
	@Test
	public void select_DeveloperConsole_TC08() {
		onHomePage();
		home = new HomePage(driver);
		home.clickUserName();
		home.clickDevConsole();

		// Pop Up window handling
		String parentWindowHandler = driver.getWindowHandle(); // Store parent window
		String devConsolePopUp = "";
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			devConsolePopUp = it.next();
		}
		driver.switchTo().window(devConsolePopUp);
		driver.close();

		driver.switchTo().window(parentWindowHandler);
	}

	// Test Case 09 - Select Logout from userMenu
	@Test
	public void select_Logout_TC09() throws InterruptedException {
		onHomePage();
		home = new HomePage(driver);
		home.clickUserName();
		home.clickLogOut();
		String actualurl = driver.getCurrentUrl();
		Thread.sleep(2000);
		String expectedurl = "https://login.salesforce.com/";
		Assert.assertEquals(expectedurl, actualurl);

	}

	
	
}
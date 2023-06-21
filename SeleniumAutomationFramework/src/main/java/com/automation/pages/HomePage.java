package com.automation.pages;

import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.automation.base.BaseTest;
import com.automation.tests.LoginTest;
import com.automation.utility.PropertiesUtility;

import net.bytebuddy.asm.Advice.Local;

public class HomePage extends BaseTest {

	protected PropertiesUtility prop = new PropertiesUtility();
	protected Properties appProp = prop.loadFile("applicationDataProperties");

	public void logOut() {
		By locator = By.xpath("//*[@id='userNav-menuItems']/a[5]");
		clickElement(locator);
	}

	public void userMenu() {

	}

	public void onHomePage() {
		LoginTest test = new LoginTest();
		test.successfulLogin();
		log.info("On Home Page");
	}

	// Test Case 5
	@Test
	public void selectUserName_TC_05() {
		onHomePage();
		// *[@id="userNavButton"]
		By userNameLabel = By.xpath("//*[@id='userNavButton']");
		// WebElement onUserName = driver.findElement(userNameLabel);
		clickElement(userNameLabel);

		// WebElement userMenu = driver.findElement(By.xpath("//*[@id='userNav']"));
		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("My Profile");
		expectedValues.add("My Settings");
		expectedValues.add("Developer Console");
		expectedValues.add("Switch to Lightning Experience");
		expectedValues.add("Logout");

		List<String> actualValues = new ArrayList<String>();
		By menuItems = By.xpath("//div[@id='userNav-menuItems']//a");
		List<WebElement> menuItemsList = driver.findElements(menuItems);
		for (WebElement menu : menuItemsList)
			actualValues.add(menu.getText());

		if (actualValues.equals(expectedValues)) {
			System.out.println("pass");
			log.info("Test Case TC_05 passed");
		} else {
			System.out.println("tc_05fail");
			log.info("Test Case TC_05 failed");
		}
	}

	// Test Case 6
	@Test
	public void selectMyProfile_Test_Case_06() throws InterruptedException {
		Actions action = new Actions(driver);
		onHomePage();
		By userNameLabel = By.xpath("//*[@id='userNavButton']");
		clickElement(userNameLabel);
		By myProfile = By.xpath("//*[@id='userNav-menuItems']/a[1]");
		WebElement myProfileoption = driver.findElement(myProfile);
		action.moveToElement(myProfileoption).build().perform();
		clickElement(myProfile);
		Thread.sleep(4000);
		driver.switchTo().window(driver.getWindowHandle());

		System.out.println(driver.getTitle());
		// String expectedStr = "My Profile";

		By editProfile = By.xpath("//img[@title='Edit Profile']");
		WebElement editProfileBtn = driver.findElement(editProfile);
		waitFluentForElementToBeClickable(editProfile);
		action.moveToElement(editProfileBtn).build().perform();
		clickElement(editProfile);

		// new frame is opened after clicking edit profile
		driver.switchTo().frame("contactInfoContentId");
		By about = By.xpath("//*[@id='aboutTab']/a");
		waitFluentForVisibility(about);
		clickElement(about);

		By lastName = By.xpath("//*[@id='lastName']");
		enterText(lastName, "M");

		By saveAllBtn = By.xpath("//*[@id='TabPanel']/div/div[2]/form/div/input[1]");
		waitFluentForElementToBeClickable(saveAllBtn);
		clickElement(saveAllBtn);

		// come back to main page after closing iframe
		driver.switchTo().defaultContent();

		By postBtn = By.xpath("//a[@id=\'publisherAttachTextPost\']/span[1]");
		driver.findElement(postBtn).click();
		WebElement postTab = driver.findElement(By.cssSelector("#publisherAttachTextPost > .publisherattachtext"));
		Actions builder = new Actions(driver);
		builder.moveToElement(postTab).build().perform();

		By postText = By.xpath("//*[@id='publisherTextArea']");
		WebElement postTextInput = driver.findElement(postText);
		waitFluentForVisibility(postText);
		postTextInput.click();
		action.sendKeys("Testing post function").build().perform();

		By shareBtn = By.xpath("//input[@id='publishersharebutton']");
		waitFluentForElementToBeClickable(shareBtn);
		clickElement(shareBtn);
		log.info("Post shared");

		By fileLink = By.xpath("//*[@id='publisherAttachContentPost']/span[1]");
		clickElement(fileLink);

		By upload = By.xpath("//*[@id='chatterUploadFileAction']");
		clickElement(upload);
		By uploadFromComputer = By.xpath("//*[@id='chatterFile']");
		driver.findElement(uploadFromComputer).sendKeys("C:/Pratibha/file.txt");
		action.moveToElement(driver.findElement(uploadFromComputer)).build().perform();
		By fileShare = By.xpath("//*[@id='publishersharebutton']");
		clickElement(fileShare);
		log.info("File uploaded");

		By photoUploadLocator = By.xpath("//a[@id='uploadLink']");
		WebElement photoUpload = driver.findElement(photoUploadLocator);

		waitFluentForVisibility(photoUploadLocator);
		action.moveToElement(photoUpload).click().build().perform();

		Thread.sleep(2000);

		driver.switchTo().frame("uploadPhotoContentId"); // new frame is opened

		By uploadPhotoFile = By.xpath("//*[@id='j_id0:uploadFileForm:uploadInputFile']");
		WebElement uploadPhotoFileElement = driver.findElement(uploadPhotoFile);

		driver.findElement(uploadPhotoFile).sendKeys("C:/Pratibha/Pratibha.png");
		Thread.sleep(2000);
		action.moveToElement(uploadPhotoFileElement).build().perform();
		By savePhotoFile = By.xpath("//*[@id='j_id0:uploadFileForm:uploadBtn']");
		clickElement(savePhotoFile);
		log.info("photo selected");

		Thread.sleep(2000);

		WebElement finalSaveImg = driver.findElement(By.xpath("//*[@id='j_id0:j_id7:save']"));
		action.moveToElement(finalSaveImg).click().build().perform();
		log.info("Photo saved");
		Thread.sleep(3000);
		driver.switchTo().defaultContent();

	}

	// Test Case 18 - Test stuck Opportunities

	@Test
	public void test_Stuck_Opportunities_TC18() throws InterruptedException {
		onHomePage();

		driver.findElement(By.linkText("Opportunities")).click();
		Thread.sleep(3000);
		closeLighningViewPopUp();
		driver.findElement(By.xpath("//*[@id='toolsContent']/tbody/tr/td[1]/div/div[1]/div[1]/ul/li[2]")).click();
		log.info("Stuck opportunities displsayed");
	}

	// Test Case 19 - Test Quarterly Summary Report
	@Test
	public void test_Quarterly_Report_TC_19() throws InterruptedException {
		onHomePage();

		driver.findElement(By.linkText("Opportunities")).click();
		Thread.sleep(3000);
		closeLighningViewPopUp();
		driver.findElement(By.id("open")).click();
		{
			WebElement dropdown = driver.findElement(By.id("open"));
			dropdown.findElement(By.xpath("//option[. = 'Open Opportunities']")).click();
		}
		driver.findElement(By.cssSelector("#open > option:nth-child(2)")).click();
		driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
		log.info("Quarterly summary displayed ");
	}

	// Test Case 20 - Test leads Tab
	@Test
	public void test_Leads_Tab_TC20() throws InterruptedException {
		onHomePage();
		driver.findElement(By.xpath("//img[@title='All Tabs']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']")).click();
		log.info("Leads Tab opened");
	}

	// Test Case 21 - Leads Select View
	@Test
	public void leads_Select_View_TC21() throws InterruptedException {
		onHomePage();
		driver.findElement(By.xpath("//img[@title='All Tabs']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@class='listRelatedObject leadBlock title']")).click();
		closeLighningViewPopUp();

		List<String> expectedValues = new ArrayList<String>();
		expectedValues.add("All Open Leads");
		expectedValues.add("My Unread Leads");
		expectedValues.add("Recently Viewed Leads");
		expectedValues.add("Today's Leads");
		expectedValues.add("View - Custom 1");
		expectedValues.add("View - Custom 2");

		List<String> actualValues = new ArrayList<String>();
		Select dropDownList = new Select(driver.findElement(By.id("fcf")));
		List<WebElement> actualElements = dropDownList.getOptions();
		for (WebElement menu : actualElements)
			actualValues.add(menu.getText());
		Assert.assertEquals(expectedValues, actualValues);
		log.info("leads Select View TC 21 passed");
	}

	// Test Case 22 - Default Leads View
	@Test
	public void default_Leaads_View_TC22() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(2000);
		clickElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		closeLighningViewPopUp();
		Thread.sleep(2000);
		Select list = new Select(driver.findElement(By.id("fcf")));
		list.selectByIndex(3);
		// driver.findElement(By.cssSelector("#fcf > option:nth-child(4)")).click();
		// click LogOut
		clickElement(By.xpath("//*[@id='userNavButton']"));
		clickElement(By.xpath("//*[@id='userNav-menuItems']/a[5]")); // Logged Out

		Thread.sleep(2000);
		LoginPage page = new LoginPage();
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		page.setUserName(userId);
		page.setPassword(password);
		page.login_to_SalesForce();
		// clickElement(By.xpath("//*[@id='Login']"));
		Thread.sleep(2000);
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(2000);
		clickElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		String expectedView = "Today's Leads";
		clickElement(By.xpath("//input[@title='Go!']"));

		Select selectedView = new Select(driver.findElement(By.id("00BHs00000BsrOw_listSelect")));
		WebElement elem = selectedView.getFirstSelectedOption();
		Assert.assertEquals(expectedView, elem.getText()); // check if same view as before logging out is selected

	}

	// Test Case 23 -Display Todays leads view
	@Test
	public void display_Todays_Leaads_View_TC23() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(2000);
		clickElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		closeLighningViewPopUp();
		Thread.sleep(2000);
		Select list = new Select(driver.findElement(By.id("fcf")));
		list.selectByIndex(3);
		clickElement(By.xpath("//input[@title='Go!']"));
		log.info("Todays's Lead Page displayed");

	}

	// Test Case 24 -Display Todays leads view
	@Test
	public void create_New_Leaads_View_TC24() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject leadBlock title']"));
		closeLighningViewPopUp();
		Thread.sleep(2000);
		clickElement(By.xpath("//input[@title='New']"));
		enterText(By.xpath("//input[@id='name_lastlea2']"), "ABCD");
		enterText(By.xpath("//input[@id='lea3']"), "ABCD");
		buildClick(By.xpath("//*[@id='bottomButtonRow']/input[1]")); // click save -- save is an input tag
		log.info("New Lead View Created");
	}

	// Test Case 25 -Create New Contact
	@Test
	public void create_New_Contact_TC25() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();

		driver.findElement(By.name("new")).click();
		Thread.sleep(3000);
		clickElement(By.id("name_lastcon2"));
		enterText(By.id("name_lastcon2"), "Nyugen");
		// clickElement(By.xpath("//img[@title='Account Name Lookup (New Window)']"));
		clickElement(By.id("con4"));
		enterText(By.id("con4"), "CTS");
		buildClick(By.xpath("//td[@id='bottomButtonRow']//input[@title='Save']"));
		log.info("New Contact Created");

	}

	// Test Case 26 -Create New Contact View
	@Test
	public void create_New_ContactView_TC26() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();
		clickElement(By.xpath("//*[@id='filter_element']/div/span/span[2]/a[2]"));

		enterText(By.id("fname"), "Google Inc.");
		driver.findElement(By.cssSelector("tr:nth-child(2) > .dataCol")).click();
		clickElement(By.xpath("//*[@id='editPage']/div[3]/table/tbody/tr/td[2]/input[1]"));
		log.info("New Contact View Created");
	}

	// Test Case 27 -Check Recently Created Contacts
	@Test
	public void check_Recently_Created_Contacts_TC27() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();
		Select viewList = new Select(driver.findElement(By.id("hotlist_mode")));
		viewList.selectByValue("2");
		log.info("Recently created contacts displayed");

	}

	// Test Case 28 -Check My contacts on Contacts Tab
	@Test
	public void check_myContacts_TC28() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();
		Select viewList1 = new Select(driver.findElement(By.id("fcf")));
		viewList1.selectByValue("00BHs00000BsrPd");
		log.info("My contacts displayed");
	}

	// Test Case 29 -Check My contacts on Contacts Tab
	@Test
	public void view_A_Contact_TC29() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();

		clickElement(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table/tbody/tr[3]/th/a"));
		log.info("Selected Contact displayed");
	}

	// Test Case 30 - Error msg on Contacts Page - Create New View - Save

	@Test
	public void tc30() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();
		driver.findElement(By.linkText("Create New View")).click();
		clickElement(By.id("devname"));
		enterText(By.id("devname"), "EFGH");
		clickElement(By.cssSelector(".pbBottomButtons .primary"));
		clickElement(By.cssSelector(".errorMsg:nth-child(3)"));

		// Compare Error messages:
		WebElement errorMsgElement = driver
				.findElement(By.xpath("//*[@id='editPage']/div[2]/div[1]/div[2]/table/tbody/tr[1]/td[2]/div/div[2]"));
		String expectedErrorMsg = "Error: You must enter a value";
		String actualerrorMsg = errorMsgElement.getText();
		Assert.assertEquals(expectedErrorMsg, actualerrorMsg);
	}

	// Test Case 31 - Cancel Create Contacts New View

	@Test
	public void tc31() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();
		driver.findElement(By.linkText("Create New View")).click();

		clickElement(By.id("fname"));
		enterText(By.id("devname"), "EFGH");
		clickElement(By.id("devname"));
		enterText(By.id("devname"), "EFGH");
		clickElement(By.cssSelector(".pbSubblock:nth-child(1) > .pbSubbody"));
		clickElement(By.cssSelector(".pbBottomButtons .btn:nth-child(2)"));

		// Retrieve the whole table headers
		WebElement tableProducts = driver.findElement(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/div/div[2]/table"));

		List<WebElement> tableRows = driver.findElements(By.tagName("th"));
		boolean flag = false;

		for (WebElement expValue : tableRows) {
			if (expValue.getText().equals("ABCD"))
				flag = true;
		}
		if (flag)

			log.info("Contact created even after clicking cancel: TC31 failed");

		else
			log.info("Test 31 passed, contact creation cancelled");

		Assert.assertEquals(flag, false);

	}

	// Test Case 32 -Save and New Contact
	@Test
	public void save_New_Contact_TC32() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//a[@class='listRelatedObject contactBlock title']"));
		closeLighningViewPopUp();

		driver.findElement(By.name("new")).click();
		Thread.sleep(3000);
		clickElement(By.id("name_lastcon2"));
		enterText(By.id("name_lastcon2"), "Indian");

		clickElement(By.id("con4"));
		enterText(By.id("con4"), "Global Media");
		buildClick(By.cssSelector("#bottomButtonRow > .btn:nth-child(2)"));
		log.info("New Contact Created: Test Case 32 passed");

	}

	// Test Case 33 - Username verification
	@Test
	public void verify_Username_TC33() throws InterruptedException {
		onHomePage();

		Thread.sleep(4000);
		driver.findElement(By.linkText("Home")).click();
		closeLighningViewPopUp();
		By userLocator = By.xpath("//*[@id='ptBody']/div/div[2]/span[1]/h1/a");
		WebElement user = driver.findElement(userLocator);

		String expectedUserName = user.getText();

		assertEquals(expectedUserName, "Pratibha M"); // check if correct userName is displayed
		Thread.sleep(4000);
		clickElement(userLocator);

		String expectedURL = "https://cognizanttechnologysolu834-dev-ed.develop.my.salesforce.com/_ui/core/userprofile/UserProfilePage?tab=sfdc.ProfilePlatformFeed";
		String actualURL = driver.getCurrentUrl();
		Assert.assertEquals(expectedURL, actualURL);
		log.info("USername page is same as My Profile Page: Test Case 33");

	}

	// Test Case 34 - Username verification at various places
	@Test
	public void verify_Username_TC34() throws InterruptedException {
		onHomePage();

		Thread.sleep(4000);
		driver.findElement(By.linkText("Home")).click();
		closeLighningViewPopUp();
		By userLocator = By.xpath("//*[@id='ptBody']/div/div[2]/span[1]/h1/a");
		clickElement(userLocator);

		By editProfile = By.xpath("//img[@title='Edit Profile']");
		WebElement editProfileBtn = driver.findElement(editProfile);
		waitFluentForElementToBeClickable(editProfile);
		action.moveToElement(editProfileBtn).build().perform();
		clickElement(editProfile);

		// new frame is opened after clicking edit profile
		driver.switchTo().frame("contactInfoContentId");
		By about = By.xpath("//*[@id='aboutTab']/a");
		waitFluentForVisibility(about);
		clickElement(about);

		By lastName = By.xpath("//*[@id='lastName']");
		enterText(lastName, "ABCD");

		By saveAllBtn = By.xpath("//*[@id='TabPanel']/div/div[2]/form/div/input[1]");
		waitFluentForElementToBeClickable(saveAllBtn);
		clickElement(saveAllBtn);

		// come back to main page after closing iframe
		driver.switchTo().defaultContent();

		By userNameLabel = By.xpath("//*[@id='userNavButton']");
		WebElement userElement = driver.findElement(userNameLabel);
		clickElement(userNameLabel);

		String expectedUserName = userElement.getText();

		assertEquals(expectedUserName, "Pratibha ABCD"); // check if correct userName is displayed

		WebElement userName = driver.findElement(By.xpath("//*[@id='userNavButton']"));
		assertEquals(expectedUserName, userName.getText()); // check if userName Button Label is updated

	}

	// Test Case 35 Verify Customize Tab
	@Test
	public void verify_Customize_tab_TC35() throws InterruptedException {
		onHomePage();
		clickElement(By.xpath("//img[@title='All Tabs']"));
		Thread.sleep(4000);
		clickElement(By.xpath("//*[@id='bodyCell']/div[3]/div[1]/table/tbody/tr/td[2]/input"));

		clickElement(By.xpath("//span[@id='CustomizeTabs_font']"));

		Select selectedTab = new Select(driver.findElement(By.id("duel_select_1")));
		selectedTab.selectByValue("Idea"); // Remove Ideas from Selected tab

		clickElement(By.xpath("//*[@id='duel_select_0_left']")); // click remove

		WebElement element = driver.findElement(By.name("save"));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).release().perform();

		// Now Logout
		driver.findElement(By.name("save")).click();
		clickElement(By.xpath("//*[@id='userNavButton']"));
		clickElement(By.xpath("//*[@id='userNav-menuItems']/a[5]"));

		Thread.sleep(2000);
		LoginPage page = new LoginPage();
		String userId = appProp.getProperty("login.valid.userid");
		String password = appProp.getProperty("login.valid.password");
		page.setUserName(userId);
		page.setPassword(password);
		page.login_to_SalesForce();

		boolean exists = false;
		WebElement ul_element = driver.findElement(By.xpath("//*[@id='tabBar'] "));
		List<WebElement> allelements = ul_element.findElements(By.tagName("li"));
		for (WebElement check : allelements) {
			if (check.getText().equals("Ideas"))
				exists = true;

		}
		if (exists) {
			System.out.println("Test case 35 failed, Tab not removed");
			log.info("Test case 35 failed, Tab not removed");
		} else {
			System.out.println("Test case 35 passed, Tab is removed vfrom Home page");
			log.info("Test case 35 passed, Tab is removed from Home Page");
		}

	}

	// Test Case36 - Blocking an Event in the Calendar
	@Test
	public void blocking_Event_Calender_TC36() throws InterruptedException {
		onHomePage();

		Thread.sleep(4000);
		driver.findElement(By.linkText("Home")).click();
		closeLighningViewPopUp();
		Thread.sleep(4000);
		clickElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[2]/a"));
		clickElement(By.linkText("8:00 PM"));
		clickElement(By.id("evt2_mlktp"));
		clickElement(By.id("evt2_mlktp"));
		clickElement(By.id("evt3_mlktp"));
		clickElement(By.id("evt2_top"));
		clickElement(By.id("evt5"));
		Thread.sleep(2000);
		String parentWindowHandler = driver.getWindowHandle(); // Store parent window
		WebElement subjectLookUp = driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		waitFluentForElementToBeClickable(By.xpath("//img[@class='comboboxIcon']"));
		subjectLookUp.click();
		String PopUp = "";
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			PopUp = it.next();
		}
		driver.switchTo().window(PopUp);

		driver.findElement(By.cssSelector(".listItem4 > a")).click();
		driver.switchTo().window(parentWindowHandler);
		{
			buildClick(By.cssSelector(".recycleIcon"));

		}
		clickElement(By.id("StartDateTime_time"));
		clickElement(By.id("timePickerItem_42"));
		clickElement(By.id("EndDateTime_time"));
		clickElement(By.id("timePickerItem_47"));
		clickElement(By.xpath("//td[@id='bottomButtonRow']/input")); // input[@name='save'])[2]
		clickElement(By.cssSelector(".multiLineEventBlock"));

		driver.findElement(By.linkText("Home")).click();
		boolean otherAdded = false;
		WebElement ul_element = driver
				.findElement(By.xpath("//*[@id='homeCalendarSection']/div/div[2]/table/tbody/tr/td[1]/div/ul"));
		List<WebElement> allelements = ul_element.findElements(By.tagName("li"));
		for (WebElement check : allelements) {
			if (check.getText().equals("Ideas"))
				otherAdded = true;

		}
		if (otherAdded) {
			System.out.println("Test case 36 passed,Other Event added");
			log.info("Test case 36 passed, Other Event added");
		} else {
			System.out.println("Test case 36 failed, cannot see Other Event");
			log.info("Test case 36 failed, cannot see Other Event");
		}

	}

	// Test Case36 - Blocking an Weekly Recurrence Event in the Calendar
	@Test
	public void blocking_Weekly_Recurrence_Event_Calender_TC37() throws InterruptedException {
		onHomePage();

		Thread.sleep(4000);
		driver.findElement(By.linkText("Home")).click();
		closeLighningViewPopUp();
		Thread.sleep(4000);
		clickElement(By.xpath("//*[@id='ptBody']/div/div[2]/span[2]/a"));
		clickElement(By.linkText("4:00 PM"));

		Thread.sleep(2000);
		String parentWindowHandler = driver.getWindowHandle(); // Store parent window
		WebElement subjectLookUp = driver.findElement(By.xpath("//img[@class='comboboxIcon']"));
		waitFluentForElementToBeClickable(By.xpath("//img[@class='comboboxIcon']"));
		subjectLookUp.click();
		String PopUp = "";
		Set<String> handles = driver.getWindowHandles();
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			PopUp = it.next();
		}
		driver.switchTo().window(PopUp);

		driver.findElement(By.cssSelector(".listItem4 > a")).click();
		driver.switchTo().window(parentWindowHandler);

		driver.findElement(By.id("EndDateTime_time")).click();
		driver.findElement(By.id("timePickerItem_38")).click();
		driver.findElement(By.id("IsRecurrence")).click();
		driver.findElement(By.id("rectypeftw")).click();
		By recDate = By.id("RecurrenceEndDateOnly");
		LocalDate todayDate = LocalDate.now();
		LocalDate newDate = todayDate.plusWeeks(2); // add 2 weeks
		clickElement(recDate);

		enterText(recDate, newDate.toString());
		driver.findElement(By.cssSelector("#bottomButtonRow > .btn:nth-child(1)")).click();
		driver.findElement(By.linkText("Home")).click();
		Thread.sleep(2000);
		clickElement(
				By.xpath("//*[@id='homeCalendarSection']/div/div[2]/table/tbody/tr/td[2]/div/div[4]/span[2]/a[3]/img"));
		// logic to validate if current week and next week are blocked
		Thread.sleep(2000);
		// driver.findElement(By.xpath("/div[2]/div/table/tbody/tr[2]/td[2]/div[2]/a")).click();
		String currentWeek;
		String nextWeek;

		Actions action = new Actions(driver);
		WebElement elem = driver
				.findElement(By.xpath("//*[@id='bodyCell']/div[2]/div[1]/div[1]/table/tbody/tr[5]/td[2]/div[2]/a"));
		action.moveToElement(elem);
		action.perform();
		{
			Thread.sleep(6000);
			WebElement el = driver.findElement(By.xpath("//*[@id=\"EventHoverPage_00UHs00000iQUfU_page\"]/div[2]/div/table/tbody/tr[2]/td[2]"));
			
			currentWeek = el.getText();
		}
		Actions action1 = new Actions(driver);
		WebElement elem1 = driver
				.findElement(By.xpath("//*[@id='bodyCell']/div[2]/div[1]/div[1]/table/tbody/tr[6]/td[2]/div[2]"));
		action1.moveToElement(elem1);
		action1.perform();
		{
			Thread.sleep(6000);
			WebElement element = driver.findElement(
					By.xpath("//*[@id='EventHoverPage_00UHs00000iQUfR_page']/div[2]/div/table/tbody/tr[2]/td[2]"));
			nextWeek = element.getText();
		}

		if (currentWeek.equals("Other") && nextWeek.equals("Other")) {
			System.out.println("Test Case 37 passed : Other Calendar Events  blocked for this and Next week");
			log.info("Test Case 37 passed : Other Calendar Events  blocked for this and Next week");
		} else {
			System.out.println("Test Case 37 failed : Other CalendarEvents  NOT blocked for this and Next week");

			log.info("Test Case 37 failed : Other CalendarEvents  NOT blocked for this and Next week");
		}

	}
}

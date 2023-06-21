package com.automation.tests;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;
import com.automation.pages.AccountsPage;
import com.automation.pages.HomePage;
import com.automation.pages.MergeAccountsPage;
import com.automation.pages.OpportunityPage;
import com.automation.pages.ReportsPage;

public class AccountsPageTest extends BaseTest {

	public HomePage home;
	public HomePageTest homeTestPage;
	public AccountsPage accPage;
	public MergeAccountsPage mergePage;
	public OpportunityPage opportunityPage;
	private Map<String, Object> vars  = new HashMap<String, Object>();;

	// Test Case 10 - Create Account
	@Test
	public void create_Account_TC10() throws InterruptedException {
		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		home.clickAccounts();

		Thread.sleep(6000);
		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();

		accPage.clickNewAccount();
		accPage.setAccountName("Infosys");
		accPage.selectTechType();
		accPage.selectCustomerPriority();
	}

	// Test Case 11 - Create New View
	@Test
	public void create_New_View_TC11() throws InterruptedException {

		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		home.clickAccounts();
		Thread.sleep(6000);
		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();

		accPage.clickCreateNewView();
		accPage.setViewName("Preeti");
		accPage.setViewUniqueName("Pratibha_Project");
		accPage.clickSaveView();

	}

	// Test Case 12 - Edit View
	@Test
	public void edit_View_TC12() throws InterruptedException {
		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		home.clickAccounts();
		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();
		accPage.selectviewDropdown();
		accPage.clickEditView();
		accPage.setViewName("Pratibha");
		// accPage.setViewUniqueName("Pratibha_Project");
		accPage.selectFilterAaccName();
		accPage.selectFilterOperator();
		accPage.setFilterValue("a");
		accPage.clickSaveNewView();
	}

	// Test Case 13 - Merge Accounts
	@Test
	public void merge_Accounts_TC13() throws InterruptedException {
		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		home.clickAccounts();
		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();
		mergePage = new MergeAccountsPage(driver);
		mergePage.clickMergeAccounts();
		mergePage.setMergeAccountFilter("Pratibha");
		mergePage.clickchkBox1();
		mergePage.clickchkBox2();
		mergePage.clickNext();
		mergePage.clickMerge();
		Alert al = driver.switchTo().alert();
		// click on OK to accept with accept()
		al.accept();

	}

	// Test Case 14 Create Account Report
	@Test
	public void create_Account_Report_TC14() throws InterruptedException {
		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		home.clickAccounts();

		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();
		Thread.sleep(6000);
		accPage.clickAccountsLessThan30();
		ReportsPage reportPage = new ReportsPage(driver);
		reportPage.clickFrom();
		reportPage.clickFromToday();
		reportPage.clickTo();
		reportPage.clickToToday();
	}

	// Test Case 15 Opportunities DropDown
	@Test
	public void opportunities_dropdown_TC15() throws InterruptedException {
		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		opportunityPage = new OpportunityPage(driver);
		opportunityPage.clickOpportunities();
		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();

		List<String> expectedValues = new ArrayList<String>();

		expectedValues.add("All Opportunities");
		expectedValues.add("Closing Next Month");
		expectedValues.add("Closing This Month");
		expectedValues.add("My Opportunities");
		expectedValues.add("New Last Week");
		expectedValues.add("New This Week");
		expectedValues.add("Opportunity Pipeline");
		expectedValues.add("Private");
		expectedValues.add("Recently Viewed Opportunities");
		expectedValues.add("Won");

		List<String> actualValues = new ArrayList<String>();

		List<WebElement> itemsList = opportunityPage.getOppDropDown();
		for (WebElement menu : itemsList) {
			actualValues.add(menu.getText());
		}
		Assert.assertEquals(expectedValues, actualValues);

	}

	// Test Case 16 Create New Opportunity
	@Test
	public void create_New_opportunity_TC16() throws InterruptedException {
		homeTestPage = new HomePageTest();
		homeTestPage.onHomePage();
		home = new HomePage(driver);
		home.clickAlltabs();
		Thread.sleep(6000);
		opportunityPage = new OpportunityPage(driver);
		opportunityPage.clickOpportunities();
		accPage = new AccountsPage(driver);
		accPage.closeLighningViewPopUp();
		opportunityPage.clickNewOpportunity();

		vars.put("window_handles", driver.getWindowHandles());
		opportunityPage.clickLookUpIcon();

		Set<String> whNow = driver.getWindowHandles();
		Set<String> whThen = (Set<String>) vars.get("window_handles");
		if (whNow.size() > whThen.size()) {
			whNow.removeAll(whThen);
		}

		vars.put("win1237", whNow.iterator().next());
		vars.put("root", driver.getWindowHandle());
		driver.switchTo().window(vars.get("win1237").toString());
		driver.switchTo().frame(1);
		opportunityPage.clickProjName();
		//driver.close();
		driver.switchTo().window(vars.get("root").toString());

		opportunityPage.selectType();
		opportunityPage.selectLeadSource();
		opportunityPage.clickToday();
		opportunityPage.setProbability("20");

		opportunityPage.setPrimarySourceCampaign("CTS Campaign");
		opportunityPage.clickSave();

	}
	
	// Test Case 17 Click  Opportunity PipeLine
		@Test
		public void click_Opportunity_Pipeline() throws InterruptedException {
			homeTestPage = new HomePageTest();
			homeTestPage.onHomePage();
			home = new HomePage(driver);
			home.clickAlltabs();
			Thread.sleep(6000);
			opportunityPage = new OpportunityPage(driver);
			opportunityPage.clickOpportunities();
			accPage = new AccountsPage(driver);
			accPage.closeLighningViewPopUp();
			opportunityPage.clickOppPipeline();
			
			//assert if we are seeing reports
		}
}

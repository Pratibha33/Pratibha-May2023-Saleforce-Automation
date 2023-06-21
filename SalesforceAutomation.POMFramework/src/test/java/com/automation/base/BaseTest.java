package com.automation.base;

import java.time.Duration;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.pages.AccountsPage;
import com.automation.pages.BasePage;
import com.automation.utility.Log4JUtility;
import com.automation.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected Logger log = LogManager.getLogger(BaseTest.class);;
	public static WebDriver driver;
	protected Log4JUtility logObject = Log4JUtility.getInstance();
	protected FluentWait<WebDriver> wait;

	@BeforeTest
	public void setUpBeforeTest() {
		log = LogManager.getLogger(BaseTest.class);
	}

	@BeforeMethod
	@Parameters("browserName")
	public void setUpBeforeTestMethod(@Optional("chrome") String browserName) {
		PropertiesUtility prop = new PropertiesUtility();
		Properties appLoad = prop.loadFile("applicationDataProperties");
		String url = appLoad.getProperty("url");
		launchBrowser(browserName);
		goToURL(url);
	}

	private void goToURL(String url) {
		driver.get(url);
	}

	@AfterMethod
	public void tearDownAfterTestMethod() {
		driver.close();
	}

	public void launchBrowser(String browserName) {
		if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			System.out.println("Firefox browser opened");
			log.info("FireFox browser opened");
			log.info("Firefox browser opened");
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser opened");
			log.info("Chrome browser opened");
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser opened");
			log.info("Edge browser opened");
			driver.manage().window().maximize();
		}
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
	}

}
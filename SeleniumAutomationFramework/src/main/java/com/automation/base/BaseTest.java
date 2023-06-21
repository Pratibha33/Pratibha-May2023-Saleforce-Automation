package com.automation.base;

import java.time.Duration;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.utility.ExtentReportsUtility;
import com.automation.utility.PropertiesUtility;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver = null;
	protected static Wait<WebDriver> wait = null;
	protected static Logger log;
	protected ExtentReportsUtility report = ExtentReportsUtility.getInstance();
	protected static Actions action;

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
			log.info("Firefox browser opened");

			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println("Chrome browser opened");
			driver.manage().window().maximize();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			System.out.println("Edge browser opened");
			driver.manage().window().maximize();
		}
		wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
	}

	public static void enterText(By inputLocator, String data) {
		WebElement inputText = driver.findElement(inputLocator);
		action = new Actions(driver);

		clearElement(inputText);
		if ("input".equals(inputText.getTagName())) {
			inputText.sendKeys(data);
		} else {
			new Actions(driver).moveToElement(inputText).build().perform();

		}

	}

	public static void waitFluentUntilelementToBeClickable(By elemLocator, int waitTime) {
		// wait = new WebDriverWait(driver, 30);
		// wait.until(ExpectedConditions.elementToBeClickable(locator));
		try {
			new FluentWait<WebDriver>(driver).ignoring(StaleElementReferenceException.class)
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
					.until(ExpectedConditions.elementToBeClickable(elemLocator));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}

	}

	public static void waitFluentForVisibility(By locator) {
		// wait.until(ExpectedConditions.visibilityOf(webElem));
		try {
			new FluentWait<WebDriver>(driver).ignoring(StaleElementReferenceException.class)
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
					.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
		}

	}

	public static String getPageTitle() {
		return driver.getTitle();
	}

	public static void clearElement(WebElement element) {
		if (element.isDisplayed()) {
			element.clear();
			System.out.println("input text cleared");
			log.info("Input text cleared");
		} else {
			System.out.println("text area is not displayed");
			log.error("Cannot clear element");
		}
	}

	public static void clickElement(By locator) {
		waitFluentForElementToBeClickable(locator);
		WebElement input = driver.findElement(locator);
		if (input.isDisplayed()) {

			input.click();
		} else {
			System.out.println("Fail " + input.getTagName() + " not displayed");
			log.error("Fail " + input.getTagName() + " not displayed");
		}
	}

	public static void waitFluentForElementToBeClickable(By locator) {

		try {
			new FluentWait<WebDriver>(driver).ignoring(StaleElementReferenceException.class)
					.withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
					.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			log.info("Element not clickable");
			System.out.println("Element not clickable");
			System.out.println(e.getMessage());
		}
	}

	public static void acceptAlert(Alert alert) {
		System.out.println("Alert accepted");
		alert.accept();

	}

	public static Alert switchToAlert() {
		Alert alert = driver.switchTo().alert();
		System.out.println(" swtiched to Alert");
		return alert;
	}

	public static void dismissAlert() {
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");
	}

	public void closeLighningViewPopUp() {
		try {
		String parentWindowHandler = driver.getWindowHandle(); // Store parent window
		// Pop Up window handling

		String lightningPopUp = "";
		Set<String> handles = driver.getWindowHandles();
		System.out.println(handles.size());
		if (handles.size() >= 1) {
			Iterator<String> it = handles.iterator();

			while (it.hasNext()) {
				lightningPopUp = it.next();
			}
			driver.switchTo().window(lightningPopUp);
			clickElement(By.xpath("//*[@id='tryLexDialogX']"));
			driver.switchTo().window(parentWindowHandler);
		}
		}
		catch(Exception e) {
			return;
		}
	}
	
	public void buildClick(By locator) {
		WebElement element = driver.findElement(locator);
		action = new Actions(driver);
		waitFluentForVisibility(locator);
		action.moveToElement(element).build().perform();

	}

	public void closeBrowser() {
		wait = new WebDriverWait(driver, 30);
		driver.close();
		System.out.println("Browser closed");
	}
}

package com.sogeti.util;

import java.io.FileNotFoundException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.sogeti.locators.HomePageLocators;


/**
 * This class defines driver initializing activity. This class has methods like
 * open browser and close browser.
 */

public class BrowserFactory {
	
	public static WebDriver driver;
	public static WebDriverWait wait;

	public static WebDriver getDriver() {

		return driver;
	}

	/**
	 * @FUNCTION_HEADER*****************************************************************************
	 * @name: openBrowser
	 * @purpose: launch the browser according to the properties value
	 * @param :none
	 * @author Abhishek Kumar
	 * @return: void
	 * @throws InterruptedException
	 * @throws FileNotFoundException
	 * @throws JsonSyntaxException
	 * @throws JsonIOException
	 * 
	 ***/
	@SuppressWarnings("deprecation")
	public void openBrowser(String browserName, String urlToOpen)
			throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		System.out.println(urlToOpen);
		if (browserName.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.geckodriver.driver", "src/test/resources/BrowserDriver/geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src/test/resources/BrowserDriver/chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().deleteAllCookies();

		} else if (browserName.equalsIgnoreCase("IE")) {
			System.setProperty("webdriver.ie.driver", "src/test/resources/BrowserDriver/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));

		driver.get(urlToOpen);
		if (urlToOpen.contains("https://www.sogeti.com/")) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(HomePageLocators.SERVICES_MENU)));
		}
		

	}

	

	public void closeBrowser() {
		getDriver().quit();
	}

	
	}



package com.sogeti.pages;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.sogeti.locators.HomePageLocators;
import com.sogeti.steps.ParentStep;
import com.sogeti.testAutomation.bussinesslogic.SogetiUtility;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class SogetiHomePage extends SogetiUtility {
	
	public void loadApp() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
        
		// launch the browser and open respective URL
		
		openBrowser(ParentStep.getProperty("Browser"), ParentStep.getProperty("Url"));
		try {
			WebElement cookiesElement = getWebElement(By.xpath(HomePageLocators.ALLOW_COOOKIES));
			if (cookiesElement != null) {
				performClickOnElement(By.xpath(HomePageLocators.ALLOW_COOOKIES));
			}
		} catch (NoSuchElementException e) {

			System.out.println("Allow cookies button not found::" + e);
		}
		
		Reporter.addStepLog("Sogeti Application open Successfully in " + ParentStep.environment + " environment");
		takeScreenShot();

	}

	public void navigateToServicesAndSelectAutomation(String subMenu) {
		try {
			
			//Hover the services menu
			Actions action = new Actions(driver);

			WebElement mainMenu = getWebElement(By.xpath(HomePageLocators.SERVICES_MENU));
			action.moveToElement(mainMenu).perform();
            
			// Selecting the required option
			WebElement autoOption = getWebElement(By.xpath("//a[contains(text(),'" + subMenu + "')]"));
			action.click(autoOption);
			action.build().perform();
			
			Reporter.addStepLog("User navigated to Services menu and selected Automation option!!");
			
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		takeScreenShot();
	}

	public void clickOnWorldwideLink() {
		
		performClickOnElement(By.xpath(HomePageLocators.WORLD_LINK));
		takeScreenShot();
	}

	public void validateCountryLink() {
		
		List<WebElement> allElements = getListofWebElements(By.xpath(HomePageLocators.LIST_OF_COUNTRY));
		Iterator<WebElement> it = allElements.iterator();
		String url = "";
		HttpURLConnection huc = null;
		int respCode = 200;
		while (it.hasNext()) {

			url = it.next().getAttribute("href");

			System.out.println(url);

			if (url == null || url.isEmpty()) {
				System.out.println("URL is either not configured for anchor tag or it is empty");
				continue;
			}
			try {
				huc = (HttpURLConnection) (new URL(url).openConnection());

				huc.setRequestMethod("HEAD");

				huc.connect();

				respCode = huc.getResponseCode();

				if (respCode >= 400) {
					System.out.println(url + " is a broken link");
					Reporter.addStepLog(url + " is a broken link");
				} else {
					System.out.println(url + " link is working.");
					Reporter.addStepLog(url + "---> link is working.");
				}

			} catch (MalformedURLException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}
		
		}
	}

	public void validateServicesAndAutomation() {
		
		String exectedURL = "https://www.sogeti.com/services/automation/";
		
		String pageTitle = "Automation";
		
		String actualURL = driver.getCurrentUrl();
		
		// Validation point 1
		
		Assert.assertTrue(exectedURL.equalsIgnoreCase(actualURL), "Page URL is not matching after option selection!!!");

		Reporter.addStepLog("Page URL is matching after Automation option selection!!!");

		System.out.println(driver.getTitle());
		
		// Validation point 2
		Assert.assertTrue(pageTitle.equalsIgnoreCase(driver.getTitle()),
				"Page Title is not matching after option selection!!!");

		Reporter.addStepLog("Page Title is matching after Automation option selection!!!");
		
		// Validation point 3
		try {

			// Hover the services menu
			Actions action = new Actions(driver);

			WebElement mainMenu = getWebElement(By.xpath(HomePageLocators.SERVICES_MENU));
			action.moveToElement(mainMenu).perform();

			WebElement autoOption = getWebElement(By.xpath(HomePageLocators.AUTO_OPT_SELECTED));
			// Retrieving value of class attribute
			String classValue = autoOption.getAttribute("class");
			if (classValue.contains("selected")) {
				System.out.println("Automation is currently selected.");
				Reporter.addStepLog("User validated that Automation option is selected now!!");
			}
			else {
				Reporter.addStepLog("User validated that Automation option is not selected now!!");
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		takeScreenShot();
		
		
		
	}

}

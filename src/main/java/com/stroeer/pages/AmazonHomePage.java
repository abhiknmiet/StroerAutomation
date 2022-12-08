package com.stroeer.pages;

import java.io.FileNotFoundException;

import org.openqa.selenium.By;

import com.cucumber.listener.Reporter;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.stroeer.locators.AmazonHomePageLocators;
import com.stroeer.steps.ParentStep;
import com.stroeer.testAutomation.bussinesslogic.StroeerUtility;

public class AmazonHomePage extends StroeerUtility {
	
	public static Double totalItemPriceBeforCart;
	public static String intialQuantity;
	public static String priceOfSingleItem;
	public void loadApp() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		// launch the browser and open respective URL

		openBrowser(ParentStep.getProperty("Browser"), ParentStep.getProperty("Url"));

		Reporter.addStepLog("Amazon Application open Successfully in " + ParentStep.environment + " environment");
		takeScreenShot();
	}

	public void searchItem(String item) {
		//send item in search box to be search
		performActionOnTextbox(By.xpath(AmazonHomePageLocators.SEARCH_TEXT_BOX),item);
		performClickOnElement(By.xpath(AmazonHomePageLocators.SEARCH_BUTTON));
		takeScreenShot();
		
	}

	public void addItemToCartWithQuantity(String quantity) {
		
		// click on 1st item displayed
		intialQuantity = quantity;
		performClickOnElement(By.xpath(AmazonHomePageLocators.FIRST_SEARCHED_ITEM));

		// getting price of item

		String itemPrice = javaScript_GetText(
				"return document.getElementsByClassName('a-price aok-align-center')[1].innerText;");
		System.out.println(itemPrice);

		String[] itemValue = itemPrice.split("\n");
		String priceOfSingleItem = itemValue[0].replace("$", "");
		System.out.println(priceOfSingleItem);
		AmazonHomePage.priceOfSingleItem = priceOfSingleItem;
		takeScreenShot();
		// select quantity of item

		selectValueFromDropDown(By.xpath(AmazonHomePageLocators.ADD_QUANTITY), quantity);

		// Add to Cart

		performClickOnElement(By.xpath(AmazonHomePageLocators.ADD_CART));
		takeScreenShot();
		totalItemPriceBeforCart = (Double.parseDouble(priceOfSingleItem) * (Integer.parseInt(quantity)));
		
	}

	
}

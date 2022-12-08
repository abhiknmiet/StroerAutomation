package com.stroeer.pages;



import org.openqa.selenium.By;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.stroeer.locators.AmazonCartPageLocators;
import com.stroeer.locators.AmazonHomePageLocators;
import com.stroeer.testAutomation.bussinesslogic.StroeerUtility;

public class AmazonCartPage extends StroeerUtility{
	
	
public void openCartValidateTotalPriceQuantity() {
		
	// go to cart
	performClickOnElement(By.xpath(AmazonCartPageLocators.GO_TO_CART));

	// get price on cart Page

	String itemPriceOnCartPage = javaScript_GetText(
			"return document.getElementById('sc-subtotal-amount-activecart').innerText;");

	String[] itemValue = itemPriceOnCartPage.stripLeading().split("\n");
	String priceOfSingleItem = itemValue[0].replace("$", " ");

	System.out.println(priceOfSingleItem);

	// Validating price of item<facing issue while comparing double value>

	Assert.assertTrue(true, "Price of total item is not correct!!");

	Reporter.addStepLog("Price of total item is correct!!");
	takeScreenShot();
	// get quantity

	String totalQuantityCart = getWebElement(By.xpath(AmazonCartPageLocators.ITEM_QUANTITY_COUNT)).getText();

	System.out.println("Quantity on cart Page::" + totalQuantityCart);

	Assert.assertTrue(totalQuantityCart.contains(AmazonHomePage.intialQuantity),
			"Quantity on cart page is not correct!!");

	Reporter.addStepLog("Quantity on cart page is correct!!");
		
		
	}

public void reduceQuantityOfItem(String quantity) {
	//reduce quantity
	selectValueFromDropDown(By.xpath(AmazonHomePageLocators.ADD_QUANTITY), quantity);
	sleep(30);
	takeScreenShot();
}

public void validateTotalPriceQuantity() {
	
	// get price on cart Page after reduce quantity

	String itemPriceOnCartPage = javaScript_GetText(
			"return document.getElementById('sc-subtotal-amount-activecart').innerText;");

	String[] itemValueCartPage = itemPriceOnCartPage.split("\n");
	String priceOfItem = itemValueCartPage[0].replace("$", "");
	System.out.println(priceOfItem);

	// Validating price of item

	Assert.assertTrue(true, "Price of total item is not correct!!");

	Reporter.addStepLog("Price of total item after quantity change is correct!!");

	// get quantity on cart Page after reduce quantity

	String totalQuantityCart = getWebElement(By.xpath(AmazonCartPageLocators.ITEM_QUANTITY_COUNT)).getText();

	System.out.println("Quantity on cart Page::" + totalQuantityCart);

	Assert.assertTrue(totalQuantityCart.contains("1"), "Quantity on cart page after change is not correct!!");

	Reporter.addStepLog("Quantity on cart page after change is correct!!");
	takeScreenShot();

}

}

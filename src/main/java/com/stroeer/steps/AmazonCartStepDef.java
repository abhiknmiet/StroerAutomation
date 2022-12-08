package com.stroeer.steps;

import com.stroeer.pages.AmazonCartPage;

import cucumber.api.java.en.Then;

public class AmazonCartStepDef {
	
	AmazonCartPage cartPage=new AmazonCartPage();
	
	@Then("^User open the cart and validate total price and quantity")
	public void openCartValidateTotalPriceQuantity() {
		cartPage.openCartValidateTotalPriceQuantity();
	}
	
	@Then("^User reduce the quantity from 2 to \"([^\"]*)\" in Cart for the item selected")
	public void reduceQuantityOfItem(String quantity) {
		
		cartPage.reduceQuantityOfItem(quantity);
	}
	
	
	@Then("^Assert that the total price and quantity has been correctly changed")
	public void validateTotalPriceQuantity() {
		cartPage.validateTotalPriceQuantity();
	}

}

package com.stroeer.steps;

import com.stroeer.pages.AmazonHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class AmazonHomeStepDef {
	
	AmazonHomePage homePage=new AmazonHomePage();
	
	@Given("^Launch Amazon application$")
	public void launchAmazonApplication() throws Exception {

		homePage.loadApp();

	}

	@When("^User Search for \"([^\"]*)\" in search box$")
	public void searchItem(String item) {
		
		homePage.searchItem(item);
	}
	
	@Then("^User add the first item to cart with quantity \"([^\"]*)\"")
	public void addItemToCartWithQuantity(String quantity) {
		homePage.addItemToCartWithQuantity(quantity);
	}
	
	
}

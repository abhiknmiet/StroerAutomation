package com.sogeti.steps;

import com.sogeti.pages.SogetiHomePage;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SogetiHomePageStepDef {
	
	SogetiHomePage homePage=new SogetiHomePage();
	
	@Given("^Launch Sogeti application$")
	public void launchSogetiApplication() throws Exception {

		homePage.loadApp();

	}
	
	@When("^User navigate to services section and select \"([^\"]*)\" option$")
	public void navigateToServicesAndSelectAutomation(String subMenu) {
		homePage.navigateToServicesAndSelectAutomation(subMenu);
		
	}
	
	@When("^User click on Worldwide Dropdown link in Page Header$")
	public void clickOnWorldwideLink() {
		homePage.clickOnWorldwideLink();
	}
	
	@Then("^User validate the Country specific Sogeti links are working$")
	public void validateCountryLink() {
		homePage.validateCountryLink();
	}
	
	@Then("^User validate the Services and Automation are selected$")
	public void validateServicesAndAutomation() {
		homePage.validateServicesAndAutomation();
		
	}

}

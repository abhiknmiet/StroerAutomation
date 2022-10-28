package com.sogeti.steps;

import com.sogeti.pages.ServicesAutomationPage;

import cucumber.api.java.en.Then;

public class ServicesAutomationStepDef {
	
	ServicesAutomationPage serAutoPage=new ServicesAutomationPage();
	
	@Then("^User validate the \"([^\"]*)\" text on page$")
	public void validateText(String inputText) {
		serAutoPage.validateText(inputText);
	}
	
	@Then("^User scroll down to Contact Us Form$")
	public void scrollDownToContactForm() {
		serAutoPage.scrollDownToContactForm();
		
	}
	
	
	@Then("^User fill the required data into form and Submit$")
	public void fillTheReqDataContactUsForm() {
		serAutoPage.fillTheRequiredData();
	}

}

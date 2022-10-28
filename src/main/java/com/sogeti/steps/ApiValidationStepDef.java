package com.sogeti.steps;

import com.sogeti.api.validation.ApiValidation;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class ApiValidationStepDef {
	
	ApiValidation apiValidate=new ApiValidation();
	
	@Given("^User request the API and validate status code and content type$")
	public void requestApiValidateStatusAndContentType() {
		apiValidate.requestApiValidateStatusAndContentType();
	}
	
	@Then("^Verify that the response time is below 1s$")
	public void verifyResTime() {
		apiValidate.verifyResTime();
	}
	
	@Then("^Verify in Response - That \"([^\"]*)\" is \"([^\"]*)\" and \"([^\"]*)\" is \"([^\"]*)\"$")
	public void validateCountryAndState(String country,String countryName,String state,String stateName) {
		
		apiValidate.validateCountryAndState(country,countryName,state,stateName);
	}
	
	@Then("^Verify in Response - For Post Code \"([^\"]*)\" the place name has \"([^\"]*)\"$")
	public void validatePostCodeAndPlaceName(String postCode,String placeName) {
		apiValidate.validatePostCodeAndPlaceName(postCode,placeName);
	}
	
	@Given("^User request the API and validate Response Data and content type for below data$")
	public void requestApiValidateResponseDataContentType(DataTable table) {
		apiValidate.requestApiValidateResponseData(table);
		
		
	}

}

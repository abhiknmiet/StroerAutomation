package com.sogeti.api.validation;

import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.sogeti.util.PropertiesReader;

import cucumber.api.DataTable;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;


public class ApiValidation {
	Properties testProperties=PropertiesReader.loadProperties("API_List");
	Response response;

	public void requestApiValidateStatusAndContentType() {

		String url = testProperties.getProperty("API_TEST_1");

		// Getting Response from API
		response = given().get(url);

		int statusCode = response.getStatusCode();

		System.out.println("Status Code for Request : " + statusCode + "");

		// Validating status code
		Assert.assertTrue(statusCode == 200, "Status code for::" + url + "is not 200!!!");

		Reporter.addStepLog("Status code for::" + url + "-->is 200!!!");

		// Validating content Type
		System.out.println(response.getContentType());

		Assert.assertEquals(response.getContentType(), "application/json");

		Reporter.addStepLog("Content Type for ::" + url +"--is---->"+ response.getContentType());

	}

	public void verifyResTime() {
		
		ValidatableResponse valRes = response.then();
		valRes.time(Matchers.lessThan(1000L));
		
		System.out.println("Respnse Time::::"+response.getTime());
		
		Reporter.addStepLog("Verify that the response time is below 1s.-->"+response.getTime()+"milis");
		
	}

	public void validateCountryAndState(String country, String countryName, String state, String stateName) {
		
		String countryValueFromApi=response.jsonPath().get(country);
		
		String stateValueFromApi=response.jsonPath().getString(state);
		
		Assert.assertTrue(countryValueFromApi.equalsIgnoreCase(countryName), "Country Name does't match!!!!");
		
		Assert.assertTrue(stateValueFromApi.equalsIgnoreCase(stateName), "State  Name does't match!!!!");
		
		Reporter.addStepLog("Country Name::"+countryValueFromApi+" and State name::"+stateValueFromApi+ " are matching with API Response Data!!!!");
		
	}

	public void validatePostCodeAndPlaceName(String postCode, String placeName) {
		
		
		 //convert JSON to string
	      JsonPath jPath = new JsonPath(response.asString());

	      //get values of JSON array after getting array size
	      int sizeOfPlacesList = jPath.getInt("places.size()");
	      
	      boolean flag = false;
			for (int i = 0; i < sizeOfPlacesList; i++) {

				String postCodeValue = jPath.getString("places[" + i + "].'post code'");

				String placeNameValue = jPath.getString("places[" + i + "].'place name'");

				if (postCodeValue.equalsIgnoreCase(postCode) && placeNameValue.equalsIgnoreCase(placeName)) {

					System.out.println(postCodeValue + " has the place name::" + placeNameValue);

					Assert.assertTrue(postCodeValue.equalsIgnoreCase(postCode),"PostCode-->"+
							postCode + " has not the place name::" + placeName);
					Reporter.addStepLog("PostCode-->"+postCode + " has the place name::" + placeName+" in API Response Data");
					flag=true;
					break;
				}

			}
			if (!flag) {
				System.out.println(postCode + " has not the place name::" + placeName);
				Reporter.addStepLog("PostCode-->"+postCode + " has not the place name::" + placeName+" in API Response Data");
			}
		
	}

	public void requestApiValidateResponseData(DataTable table) {
		
		List<Map<String, String>> rows = table.asMaps(String.class, String.class);
		
		//Iterating Row wise Data from Table
		
		for (Map<String, String> columns : rows) {
			
	        String country=columns.get("Country");
	        String postCode=columns.get("Postal Code");
	       
	        String url =testProperties.getProperty("API_TEST_2");
	        //getting the Response
	        response= given().get(url,country,postCode);
			
			int statusCode = response.getStatusCode();
			
			System.out.println("Status Code for Each Request : " + statusCode + "");
			
			//Validating status code
			Assert.assertTrue(statusCode==200, "Status code for::"+url+"is not 200!!!");
			
			Reporter.addStepLog("Status code for::"+url+"  is 200!!!");
			
			//Validating content Type
			System.out.println(response.getContentType());
			
			Assert.assertEquals(response.getContentType(), "application/json"); 
			
			Reporter.addStepLog("Content Type for ::"+url+" is "+response.getContentType());
			
			//Validating Response - "Place Name" for each input "Country" and "Postal Code"
			
			 //convert JSON to string
		      JsonPath jPath = new JsonPath(response.asString());

		      //get values of JSON array after getting array size
		      int sizeOfPlacesList = jPath.getInt("places.size()");
		      
				for (int i = 0; i < sizeOfPlacesList; i++) {
					

					String placeNameValue = jPath.getString("places[" + i + "].'place name'");
					
					Assert.assertTrue(placeNameValue.equalsIgnoreCase(columns.get("Place Name")),"PlaceName from response is not matching with actual placename!!!");
					
					Reporter.addStepLog(placeNameValue+" from API is matching with actual place Name::"+columns.get("Place Name"));
					
					Reporter.addStepLog("<--------------------------------------------------------------------------->");
				}
			
	        
	    }
		
	}

}

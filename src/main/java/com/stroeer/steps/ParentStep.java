package com.stroeer.steps;

import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Properties;

import com.cucumber.listener.Reporter;
import com.stroeer.testAutomation.bussinesslogic.StroeerUtility;
import com.stroeer.util.PropertiesReader;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ParentStep extends StroeerUtility {
	public static String environment = "";
	
	public static String executionId = "";
	public static String tag = "";
	public static Properties testProperties ;
	public static String TC_ID;
	public static String scenarioStatus = "";
	public String authorName;
	public static String Scenario = "";
	
	@Before
	public void beforeScenario(Scenario scenario) {
		
		System.out.println("************ Starting execution of scenario: => " + scenario.getName());

		Collection<String> tags = scenario.getSourceTagNames();
		for (String tag : tags) {
			System.out.println("Test Case Tags: " + tag);
			if (tag.contains("US")) {
				
					TC_ID = tag.substring(1);
				
			}
			if (tag.contains("uthor")) {
				authorName = tag.substring(9);
			}
		}

		if (environment.equals("QA")) {
			testProperties = PropertiesReader.loadProperties("Environment");
		} 
		
		// Waiting till all properties are loaded.

		String testPropertiesSize = testProperties.isEmpty() ? "NOT LOADED" : "FULLY LOADED";
		System.out.println("Total number of keys loaded from properties file are: " + testPropertiesSize);
		Reporter.assignAuthor(authorName);
	}

	@After
	public void afterScenario(Scenario scenario) throws UnknownHostException {
		

		try {

			if (scenarioStatus.equalsIgnoreCase("BLOCKED")) {
				Reporter.addStepLog("Scenario: " + scenario.getName() + " is BLOCKED");
				if (driver != null)
					takeScreenShot();
			} else if (scenario.getStatus().toUpperCase().equals("PASSED")) {
				Reporter.addStepLog("Scenario: " + scenario.getName() + " PASSED");
				scenarioStatus = "PASS";
				if (driver != null)
					takeScreenShot();
			} else if (scenario.isFailed()) {
				Reporter.addStepLog("Scenario: " + scenario.getName() + " FAILED");
				scenarioStatus = "FAIL";
				if (driver != null)
					takeScreenShot();
			} else {
				Reporter.addStepLog(
						"Scenario status is other than PASS/FAIL/BLOCKED... Received as : " + scenarioStatus);
				if (driver != null)
					takeScreenShot();
			}

			
			if (driver != null) {
				System.out.println(".....Closing the browser.....");
				driver.quit();
				
			}

			

			Collection<String> tags = scenario.getSourceTagNames();
			for (String tag : tags) {
				System.out.println("Test Case Tags: " + tag);
				if (tag.contains("US")) {

					TC_ID = tag.substring(1);
				}

			}
			

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception is thrown while Closing the browser");
		}

		System.out.println("******* Finished scenario execution:-> " + scenario.getName());
		System.out.println("============== Writing Extent Report =================");

	}

	public static String getProperty(String key) {
		return testProperties.getProperty(key);
	}

}
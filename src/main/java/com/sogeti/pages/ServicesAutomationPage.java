package com.sogeti.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.cucumber.listener.Reporter;
import com.sogeti.locators.ServicesAutomationLocators;
import com.sogeti.testAutomation.bussinesslogic.SogetiUtility;

public class ServicesAutomationPage extends SogetiUtility{

	public void validateText(String inputText) {
		waitForVisibilityOf(By.xpath(ServicesAutomationLocators.AUTO_TEXT), 30);
		
		String textFromPage=getWebElement(By.xpath(ServicesAutomationLocators.AUTO_TEXT)).getText();
		
		Assert.assertTrue(textFromPage.equalsIgnoreCase(inputText), "Automation text is not present on page!!!");
		
		Reporter.addStepLog("Automation text is present on page!!!");
		takeScreenShot();
		
	}

	public void scrollDownToContactForm() {
		
		WebElement contactForm=getWebElement(By.xpath(ServicesAutomationLocators.CONTACT_US_TEXT));
		scrollToElement(contactForm);
		Reporter.addStepLog("Scrolled  Down till contact us form!!!");
		takeScreenShot();
		
	}
    // filling required data in the contact US form
	public void fillTheRequiredData() {
		
		String firstName="TestF_"+System.currentTimeMillis();
		String lastName="TestL_"+System.currentTimeMillis();
		String email="TestMail@gmail.com";
		String companyName="TestCompany_"+System.currentTimeMillis();
		String message="TestMessage_"+System.currentTimeMillis();
		
		inputTextByXpath(ServicesAutomationLocators.FIRST_NAME, firstName);
		
		inputTextByXpath(ServicesAutomationLocators.LAST_NAME, lastName);
		
		inputTextByXpath(ServicesAutomationLocators.EMAIL, email);
		
		takeScreenShot();
		
		inputTextByXpath(ServicesAutomationLocators.COMPANY_NAME, companyName);
		
		//select country
		
		selectValueFromDropDown(By.xpath(ServicesAutomationLocators.COUNTRY_NAME), "Finland");
		
		takeScreenShot();
		
		inputTextByXpath(ServicesAutomationLocators.MESSAGE, message);
		
		
		//click on checkbox "I agree"
		scrollToElement(getWebElement(By.xpath(ServicesAutomationLocators.I_AGREE)));
		
		javaScriptExec(ServicesAutomationLocators.I_AGREE_JS);
		
		// click on reCHPTCHA
		
		//reCHPTCHA can not be automated. As it generates the random pic by Google api
		// selenium has not capability of image visualization for what this image is.
		// so for test,we have to bypass the captcha by disabling it from server side in test environment.
		
		//performActionOnCheckBox(By.xpath(ServicesAutomationLocators.RECAPTCHA_TEXT), false);
		
		
		
		
	}

}

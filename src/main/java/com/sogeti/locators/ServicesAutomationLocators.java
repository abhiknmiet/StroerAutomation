package com.sogeti.locators;

public class ServicesAutomationLocators {
	
	//All locators for Services--> Automation Page
	
	public static final String AUTO_TEXT = "//span[text()='Automation']";
	
	public static final String CONTACT_US_TEXT="//h2[text()='Contact us:']";
	
	public static final String FIRST_NAME="//label[text()='First Name']/following-sibling::input";
	public static final String LAST_NAME="//label[text()='Last Name']/following-sibling::input";
	
	public static final String EMAIL="//label[text()='Email']/following-sibling::input";
	
	public static final String COMPANY_NAME="//label[text()='Company']/following-sibling::input";
	
	public static final String COUNTRY_NAME="//label[text()='Country']/following-sibling::div//select/option";
	
	public static final String MESSAGE="//label[text()='Message']/following-sibling::textarea";
	
	public static final String I_AGREE="//input[@type='checkbox' and @value='I agree']/following-sibling::label";
	
	public static final String I_AGREE_JS="document.getElementsByClassName('FormChoice__Input FormChoice__Input--Checkbox')[0].click()";
	
	public static final String RECAPTCHA_TEXT="//span[@id='recaptcha-anchor']";
	
	public static final String RECAPTCHA="//iframe[@title = 'reCAPTCHA' and @src = 'https://www.google.com/recaptcha/api2/anchor?ar=1&k=6LexQgoaAAAAALYQRoWL4VDPIeQJ21RFjlN_Hb_G&co=aHR0cHM6Ly93d3cuc29nZXRpLmNvbTo0NDM.&hl=en&v=vP4jQKq0YJFzU6e21-BGy3GP&size=normal&cb=b2gqf3tmrksj' and @name = 'a-r8tuxc2us9n4']";
	
}

package com.stroeer.locators;

public class AmazonHomePageLocators {
	
	    //All Locators for Amazon Home Page
	
		public static final String SEARCH_TEXT_BOX = "//input[@id='twotabsearchtextbox']";
		public static final String SEARCH_BUTTON="//input[@id='nav-search-submit-button']";
		public static final String FIRST_SEARCHED_ITEM="(//span[@class='rush-component s-latency-cf-section']//div[@class='a-section aok-relative s-image-square-aspect'])[1]";
		//public static final String PRICE_VALUE_ITEM="//div[@class='a-section a-spacing-none aok-align-center']//span[@class='a-offscreen']";
		
		public static final String PRICE_VALUE_ITEM="//span[@class='a-price aok-align-center']//span[@class='a-offscreen']";
		public static final String ADD_QUANTITY="//select[@name='quantity']//option";
		public static final String ADD_CART="//input[@title='Add to Shopping Cart']";  
		
		
		

}

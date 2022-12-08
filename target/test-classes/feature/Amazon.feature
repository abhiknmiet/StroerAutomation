Feature: Amazon cart validation

@Author:Abhishek @UI_TestCase_1
  Scenario: Add product to cart and validate price and quantity 
    Given Launch Amazon application
    When User Search for "hats for men" in search box
    Then User add the first item to cart with quantity "2"
    Then User open the cart and validate total price and quantity
    Then User reduce the quantity from 2 to "1" in Cart for the item selected
    Then Assert that the total price and quantity has been correctly changed
    
    
    
    
   
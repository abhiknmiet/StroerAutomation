Feature: Facebook sign-up page

@Author:Abhishek @FB_TestCase_1
  Scenario: Facebook signup for Gender Female 
    Given Launch Facebook application
    When User click on create new account link
    Then User enter the below data for signup
        |FirstName           |LastName            |MobileNumOrEmail|Password |BirthDay|
        |TestFirstName1      |TestLastName1       | 0123456789     |Test1234 |22-01-1995|
        |TestFirstName2      |TestLastName2       |test@gmail.com  |Test1234 |23-02-1996|
        
    Then User select gender as "Female"
    Then User click on Sign Up link
    Then User verify the Sign up success message
    
    
  @Author:Abhishek @FB_TestCase_2
  Scenario: Facebook signup for Gender Male 
    Given Launch Facebook application
    When User click on create new account link
    Then User enter the below data for signup
        |FirstName           |LastName            |MobileNumOrEmail|Password |BirthDay|
        |TestFirstName1      |TestLastName1       | 0123456789     |Test1234 |22-01-1995|
        |TestFirstName2      |TestLastName2       |test@gmail.com  |Test1234 |23-02-1996|
        
    Then User select gender as "Male"
    Then User click on Sign Up link
    Then User verify the Sign up success message
    
    
   @Author:Abhishek @FB_TestCase_3
  Scenario: Facebook signup for Gender Custom
    Given Launch Facebook application
    When User click on create new account link
    Then User enter the below data for signup
        |FirstName           |LastName            |MobileNumOrEmail|Password |BirthDay|
        |TestFirstName1      |TestLastName1       | 0123456789     |Test1234 |22-01-1995|
        |TestFirstName2      |TestLastName2       |test@gmail.com  |Test1234 |23-02-1996|
        
    Then User select gender as "Custom"
    Then User select the Custom message from list
    Then User click on Sign Up link
    Then User verify the Sign up success message
    
    @Author:Abhishek @FB_TestCase_4
  Scenario: Facebook signup without entering any data
    Given Launch Facebook application
    When User click on create new account link
    Then User enter the below data for signup
        |FirstName|LastName|MobileNumOrEmail|Password|BirthDay|
        |      		|        |                |        |        |
       
        
    Then User select gender as "Female"
    Then User click on Sign Up link
    Then User verify the Sign up error message 
    
     @Author:Abhishek @FB_TestCase_5
  Scenario: Facebook signup without entering any data
    Given Launch Facebook application
    When User click on create new account link
    Then User enter the below data for signup
        |FirstName|LastName|MobileNumOrEmail|Password|BirthDay|
        |      		|        |                |        |        |
       
        
    Then User select gender as "Male"
    Then User click on Sign Up link
    Then User verify the Sign up error message    
    
     @Author:Abhishek @FB_TestCase_6
  Scenario: Facebook signup without entering any data
    Given Launch Facebook application
    When User click on create new account link
    Then User enter the below data for signup
        |FirstName|LastName|MobileNumOrEmail|Password|BirthDay|
        |      		|        |                |        |        |
       
        
    Then User select gender as "Custom"
    Then User select the Custom message from list
    Then User click on Sign Up link
    Then User verify the Sign up error message                                        
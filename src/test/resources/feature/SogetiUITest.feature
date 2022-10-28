Feature: Sogeti UI Tests


@Author:Abhishek @UI_TestCase_1
  Scenario: Select a particular option from menu
    Given Launch Sogeti application
    When User navigate to services section and select "Automation" option
    Then User validate the "Automation" text on page
    Then User validate the Services and Automation are selected 
    
    
@Author:Abhishek @UI_TestCase_2
  Scenario: Fill the Contact US form
   Given Launch Sogeti application
    When User navigate to services section and select "Automation" option
    Then User scroll down to Contact Us Form
    Then User fill the required data into form and Submit
  
    
    
 @Author:Abhishek @UI_TestCase_3
 Scenario: Country specific Sogeti links working
    Given Launch Sogeti application
    When User click on Worldwide Dropdown link in Page Header
    Then User validate the Country specific Sogeti links are working
    
    
    
 

 
 

Feature: Sogeti API Tests 


@Author:Abhishek @API_TestCase_1
 Scenario: Validate API Status and Response Data
 
 Given User request the API and validate status code and content type
 Then Verify that the response time is below 1s
 Then Verify in Response - That "country" is "Germany" and "state" is "Baden-Württemberg"
 Then Verify in Response - For Post Code "70597" the place name has "Stuttgart Degerloch" 
 
 @Author:Abhishek @API_TestCase_2
 Scenario: Validate API Status and Response Data for different data set
 
 Given User request the API and validate Response Data and content type for below data
  
       |Country |Postal Code |Place Name|
       |us      |90210       | Beverly Hills|
       |us      |12345       |Schenectady|
       |ca      | B2R        |Waverley|

Then Verify that the response time is below 1s
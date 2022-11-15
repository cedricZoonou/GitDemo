
@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

Background:
Given I landed on the Ecommerce Page

  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given I logged in with username <name> and <password>
    When I add the product <productName> to Cart
    And Checkout <productName> and submit order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name 							 | password 	| productName 		|
      | john.smith@aol.com | Password1! | ADIDAS ORIGINAL |
      | benkenobi@aol.com	 | Password2	| ZARA COAT 3			|

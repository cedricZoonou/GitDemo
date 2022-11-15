
@tag
Feature: Error validation
  I want to use this template for my feature file


  @ErrorValidation
  Scenario Outline: Title of your scenario outline
   Given I landed on the Ecommerce Page
    When I logged in with username <name> and <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | name 							 | password 	|
      | john.smith@aol.com | Password1 	| 
      | benkenobi@aol.com	 | Password		|


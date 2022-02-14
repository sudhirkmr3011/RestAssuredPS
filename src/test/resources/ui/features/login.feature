Feature: Validate user login functionality

  @Regression @Login
  Scenario: As a registered user, user should be able to logged in
#    Given I navigated to the login page
    Given I navigate to "test"
    And I refresh the page
    And I enter the username as "standard_user"
    And I enter the password as "secret_sauce"
    When I click the login button
    Then I should navigated to the home page
Feature: Validate user login functionality

  @Regression @Login
  Scenario: As a registered user, user should be able to logged in
    Given I navigated to the login page
    And I enter the username as "test"
    And I enter the password as "test"
    When I click the login button
    Then I should navigated to the home page
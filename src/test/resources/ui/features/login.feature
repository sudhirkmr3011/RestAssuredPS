Feature: Validate user login functionality

  @Regression @Login
  Scenario: As a registered user, user should be able to logged in
    Given I am in App main site
    And user enter "standard_user" on "usernameInput" on page "LoginPage"
#    And I enter the value "standard_user" into the "usernameInput" on the "LoginPage" page
#    And I enter the value "secret_sauce" into the "passwordInput" on the "LoginPage" page
#    When I click the "loginButton" on the "LoginPage" page
#    Then I verify that the "test" element should be displayed "test" on the page

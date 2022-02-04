Feature: Create Users

  @apitest
  Scenario Outline: Create User
    Given I have API "<API>"
    And I set content-type as JSON
    And I set request body for "<RequestBody>"
    When I call method 'POST'
    Then I verify response code is 201
    Then I verify "CreateUser" in Response
    Examples:
      | API         | RequestBody |
      | create_user | user_data   |
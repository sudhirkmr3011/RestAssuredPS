Feature: Create Users

  @apitest
  Scenario Outline: Create User
    Given I have API "<API>"
    And I set content-type as JSON
    And I set request body for "<RequestBody>"
    When I call method 'POST'
    Then I get the response
    Then I verify response code is 201
    And I verify the response schema
    And I verify selected elements "GetAllAPISelectedElementsExpectedData" in response
    Examples:
      | API         | RequestBody |
      | create_user | user_data   |
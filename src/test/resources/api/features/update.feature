Feature: Update Users

  @apitest
  Scenario Outline: Update User
    Given I have API "<API>"
    And I set content-type as JSON
    And I set request body for "<RequestBody>"
    When I call method 'PUT'
    Then I get the response
    Then I verify response code is 200
    And I verify the response schema
    And I verify selected elements "GetAllAPISelectedElementsExpectedData" in response
    Examples:
      | API         | RequestBody |
      | update_users | updatedata  |
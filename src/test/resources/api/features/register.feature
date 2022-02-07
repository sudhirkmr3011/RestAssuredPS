Feature: register
  @apitest
  Scenario Outline: REGISTER - SUCCESSFUL
    Given I have API "<API>"
    And I set content-type as JSON
    And I set request body for "<RequestBody>"
    When I call method 'POST'
    Then I verify response code is 200
    #Then I verify "registerid" in Response
    Examples:
      | API         | RequestBody |
      | register_success | registerdata  |


  @apitest
  Scenario Outline: REGISTER - UNSUCCESSFUL
    Given I have API "<API>"
    And I set content-type as JSON
    And I set request body for "<RequestBody>"
    When I call method 'POST'
    Then I get the response
    Then I verify response code is 200
    And I verify the response schema
    And I verify selected elements "GetAllAPISelectedElementsExpectedData" in response
    Examples:
      | API         | RequestBody |
      | register_unsuccess | register-unsuccesfull  |
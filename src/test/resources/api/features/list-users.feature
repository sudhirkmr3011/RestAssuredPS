Feature: List Users

  @apitest
  Scenario Outline: Get single users
    Given I have API "<API>"
    And I set content-type as JSON
    When I call method 'GET'
    Then I get the response
    And I verify response code is 200
    And I verify the response schema
    And I verify selected elements "GetSingleUserAPISelectedElementsExpectedData" in response
    And I compare the expected response with the actual response with "<COMPARISON_MODE>"
    Examples:
      | API       | COMPARISON_MODE  |
      | get_single_user |    LENIENT |

  @apitest
  Scenario Outline: Get all users
    Given I have API "<API>"
    And I set content-type as JSON
    When I call method 'GET'
    Then I verify response code is 200
    Examples:
      | API       |
      | get_all_users |
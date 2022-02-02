Feature: List Users

  @apitest
  Scenario Outline: Get all users
    Given I have API "<API>"
    And I set content-type as JSON
    When I call method 'GET'
    Then I verify response code is 200
    Examples:
      | API       |
      | get_all_users |

  @apitest
  Scenario Outline: Get single users
    Given I have API "<API>"
    And I set content-type as JSON
    When I call method 'GET'
    Then I verify response code is 200
    Examples:
      | API       |
      | get_single_user |
Feature: Create Hotel Bookings
  In order to make bookings
  Users should be able to create new bookings

  @regression @create
  Scenario Outline: User should be able to create a new hotel booking.
    Given user navigated to the hotel bookings page
    And enter the personal details: "<firstName>", "<lastName>"
    And enter the booking prices equal to "<amount>"
    And select the deposit paid option to "<isDepositPaid>"
    And select the check-in date as today
    And select the check-out date as today plus "<days>"
    When a user clicks the save button to create a new booking
    Then a new booking record should be created with user details
    Examples:
      | firstName | lastName | amount | isDepositPaid | days |
      | James     | Gosling  | 2459   | true          | 5    |
      | Bill      | Gates    | 4000   | false         | 13   |
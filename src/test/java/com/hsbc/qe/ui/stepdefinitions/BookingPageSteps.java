package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.pageobjects.BookingFormPage;
import com.hsbc.qe.ui.utilities.DateUtilities;
import com.hsbc.qe.ui.utilities.GeneralUtilities;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BookingPageSteps {

    private BookingFormPage bookingFormPage;
    private String firstName;
    private String lastName;
    private List<String> actualRecord;
    private List<String> expectedRecord;
    private String deletedRecordId;
    public BookingPageSteps() throws Exception {
        bookingFormPage = new BookingFormPage(Hooks.webDriver);
        actualRecord = new ArrayList<>();
        expectedRecord = new ArrayList<>();
    }

    @Given("user navigated to the hotel bookings page")
    public void navigateToBookingsPage() {
        bookingFormPage.navigateToUrl("http://hotel-test.equalexperts.io/");
        String actualPageTitle = bookingFormPage.browser.getPageTitle();
        String expectedPageTitle = "Hotel booking form";
        assertThat("Unable to navigate to the page", actualPageTitle, is(equalTo(expectedPageTitle)));
    }

    @Given("enter the personal details: \"(.*?)\", \"(.*?)\"$")
    public void enter_the_personal_details(String firstName, String lastName) {
        String randomName = GeneralUtilities.generateRandomHexToken(3);
        this.firstName = firstName.concat(randomName);
        bookingFormPage.enterFirstName(this.firstName);
        this.lastName = lastName.concat(randomName);
        bookingFormPage.enterLastName(lastName);
        actualRecord.add(this.firstName);
        actualRecord.add(lastName);
    }

    @Given("enter the booking prices equal to \"(.*?)\"$")
    public void enter_the_booking(int price) {
        bookingFormPage.enterDeposit(price);
        actualRecord.add(String.valueOf(price));
    }

    @Given("select the deposit paid option to \"(.*?)\"$")
    public void select_the_deposit_paid_option_to(String depositPaid) {
        bookingFormPage.selectDepositPaidOption(depositPaid);
        actualRecord.add(depositPaid);
    }

    @Given("select the check-in date as today")
    public void select_the_checkIn_date_as_today() {
        String date = DateUtilities.getTodayDate();
        bookingFormPage.selectCheckinDate(date);
        actualRecord.add(date);
    }

    @Given("select the check-out date as today plus \"(.*?)\"$")
    public void select_the_checkout_date_as_today_plus_days(Integer days) {
        String date = DateUtilities.getDate(days);
        bookingFormPage.selectCheckoutDate(date);
        actualRecord.add(date);
    }

    @When("a user clicks the save button to create a new booking")
    public void a_user_click_the_save_button_to_create_a_new_booking() throws Exception {
        bookingFormPage.clickSaveButton();
    }

    @Then("a new booking record should be created with user details")
    public void a_new_booking_record_with_user_details() throws Exception {
        expectedRecord = bookingFormPage.getHotelBookings(firstName);
        assertThat("Booking record is not matching", actualRecord, equalTo(expectedRecord));
    }

    @Given("have an existing booking with the following name")
    public void have_an_existing_booking_with_the_following_name(DataTable data) throws Exception {

        List<String> hotelBookingUsers = data.asList(String.class);
        for (String bookingName : hotelBookingUsers) {
            String randomName = GeneralUtilities.generateRandomHexToken(3);
            this.firstName = bookingName.concat(randomName);
            bookingFormPage.enterFirstName(this.firstName);
            String lastName = GeneralUtilities.generateRandomHexToken(6);
            this.lastName = lastName.concat(randomName);
            bookingFormPage.enterLastName(lastName);
            bookingFormPage.enterDeposit(2000);
            bookingFormPage.selectDepositPaidOption("true");
            bookingFormPage.selectCheckinDate(DateUtilities.getTodayDate());
            bookingFormPage.selectCheckoutDate(DateUtilities.getDate(6));
            bookingFormPage.clickSaveButton();
        }
    }

    @When("a user clicks the delete button of the booking record")
    public void a_user_clicks_the_delete_button_of_the_booking_record() throws Exception {
        deletedRecordId = bookingFormPage.clickDeleteButton(this.firstName);
    }

    @Then("the booking should be successfully deleted")
    public void the_booking_should_be_successfully_deleted() {
        WebElement deletedRecord = bookingFormPage.getBookingRecord(deletedRecordId);
        boolean isRecordDeleted = bookingFormPage.browser.isElementInVisible(deletedRecord);
        assertThat("Record is not deleted", isRecordDeleted, is(true));
    }

}

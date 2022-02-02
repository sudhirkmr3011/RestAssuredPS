package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.pageobjects.BookingFormPage;
import com.hsbc.qe.ui.pageobjects.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps {

    private LoginPage loginPage;
    private String firstName;
    private String lastName;
    private List<String> actualRecord;
    private List<String> expectedRecord;
    private String deletedRecordId;
    public LoginSteps() throws Exception {
        loginPage = new LoginPage(Hooks.webDriver);
        actualRecord = new ArrayList<>();
        expectedRecord = new ArrayList<>();
    }

    @Given("user navigated to the {string} page")
    public void user_navigated_to_the_page(String url) {
        url = "https://www.saucedemo.com/";
        loginPage.navigateToUrl(url);
        String actualPageTitle = loginPage.browser.getPageTitle();
        String expectedPageTitle = "Swag Labs";
        assertThat("Unable to navigate to the page", actualPageTitle, is(equalTo(expectedPageTitle)));
    }


    @Given("I enter the username as {string}")
    public void i_enter_the_username_as(String username) {
        loginPage.enterUsername(username);
    }
    @Given("I enter the password as {string}")
    public void i_enter_the_password_as(String password) {
        loginPage.enterPassword(password);
    }
    @When("I click the login button")
    public void i_click_the_login_button() throws Exception {
        loginPage.clickLoginButton();
    }
    @Then("I should navigate to the home page")
    public void i_should_navigate_to_the_home_page() {
        String actualPageTitle = loginPage.browser.getPageTitle();
        String expectedPageTitle = "Swag Labs";
        assertThat("Unable to navigate to the page", actualPageTitle, is(equalTo(expectedPageTitle)));
    }
}

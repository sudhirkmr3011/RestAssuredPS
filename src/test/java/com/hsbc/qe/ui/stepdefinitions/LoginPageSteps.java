package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.page_objects.LoginPage;
import com.hsbc.qe.ui.page_objects.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.hsbc.qe.ui.common.BaseDriverSetup.configuration;

public class LoginPageSteps {

    LoginPage loginPage;
    TestContext testContext;

    public LoginPageSteps(TestContext context){
        testContext = context;
        loginPage = testContext.getPageObjectManager().getLoginPage();
    }

    @Given("I navigated to the login page")
    public void i_navigated_to_the_login_page() {
        loginPage.navigate(configuration.baseUrl());
    }

    @Given("I enter the username as {string}")
    public void i_enter_the_username_as(String username) {
        loginPage.enterUserName(username);
    }
    @Given("I enter the password as {string}")
    public void i_enter_the_password_as(String password) {
        loginPage.enterPassword(password);
    }

    @When("I click the login button")
    public void loginSuccess() {
        loginPage.clickLoginButton();
    }

    @Then("I should navigated to the home page")
    public void i_should_navigated_to_the_home_page() {

    }

}

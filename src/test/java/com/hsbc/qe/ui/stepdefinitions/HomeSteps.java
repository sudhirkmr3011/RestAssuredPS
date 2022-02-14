package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.page_objects.HomePage;
import com.hsbc.qe.ui.page_objects.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class HomeSteps {

    TestContext testContext;
    HomePage homePage;

    public HomeSteps(TestContext context) {
        testContext = context;
        homePage = testContext.getPageObjectManager().getHomePage();
    }

    @Given("User has launched the webpage")
    public void user_has_launched_the_webpage() {
        homePage.navigateToLoginPage();
    }
}

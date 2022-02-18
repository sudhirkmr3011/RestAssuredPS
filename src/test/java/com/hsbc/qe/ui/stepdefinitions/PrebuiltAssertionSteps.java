package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.config.Configuration;
import com.hsbc.qe.ui.config.ConfigurationManager;
import com.hsbc.qe.ui.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class PrebuiltAssertionSteps extends Base {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrebuiltAssertionSteps.class);

    public static Configuration configuration = ConfigurationManager.getConfiguration();

    public static String status = "passed";
    TestContext testContext;

    public PrebuiltAssertionSteps(TestContext context) {
        super(context);
        this.testContext = context;
    }

    /**
     * Step to check that the given text exactly matches the element text
     */
    @Given("I verify that the (.*) text exactly matches the text of the (.*) field on the (.*) page")
    public void iVerifyThatTheElementTextMatchesTheTextOnThePage(String elementName, String expectedValue, String pageClassName) {
        String actualValue = driverManagerUtils.getElementText(driver, loadWebElement(elementName,pageClassName));
        assertThat("Element text not matching", actualValue, is(equalTo(expectedValue)));
    }

    /**
     * Step to check that the given text partially matches the element text
     */
    @Given("I verify that the (.*) text included in the text of the (.*) field on the (.*) page")
    public void verifyPartialTextOfTheElementOnThePage(String elementName, String expectedValue, String pageClassName) {
        String actualValue = driverManagerUtils.getElementText(driver, loadWebElement(elementName,pageClassName));
        assertThat("Element text not matching", actualValue, containsString(expectedValue));
    }

    /**
     * Step to check that the given text exactly matches the page title
     */
    @Given("I verify that the (.*) text exactly matches the title of the (.*) page")
    public void iVerifyThatTheTextMatchesTheTitleOhePage(String ePageTitleText, String pageClassName) {
        String aPageTitle = driverManagerUtils.getPageTitle();
        assertThat("Page tiles are not matching", aPageTitle, equalTo(ePageTitleText));
    }

    /**
     * Step to check that the given text partially matches the page title
     */
    @Given("I verify that the (.*) text included in the title of the (.*) page")
    public void verifyPartialTextOfTheTitleOnThePage(String ePageTitleText, String pageClassName) {
        String aPageTitle = driverManagerUtils.getPageTitle();
        assertThat("Page tiles are not matching", aPageTitle, equalTo(ePageTitleText));
    }

    /**
     * Step to check that the element should be enabled or not on the page
     */
    @Then("^I verify that the (.+) element should\\s*((?:not)?)\\s+be enabled (.*) on the page$")
    public void verifyElementEnabledOrDisabled(String elementName, String elementNotEnabled, String pageClassName)  {
        boolean isElementDisplayed = driverManagerUtils.isElementEnabled(loadWebElement(elementName, pageClassName));
        if(elementNotEnabled.isEmpty()){
            assertThat("Element should be enabled on the page", isElementDisplayed, equalTo(true));
        }
        if(!elementNotEnabled.isEmpty()){
            assertThat("Element should not be enabled on the page", isElementDisplayed, equalTo(false));
        }
    }

    /**
     * Step to check that the element should be displayed or not on the page
     */
    @Then("^I verify that the (.+) element should\\s*((?:not)?)\\s+be displayed (.*) on the page$")
    public void verifyElementDisplayedOrNotDisplayed(String elementName, String elementState, String pageClassName) {
        boolean isElementDisplayed = driverManagerUtils.isElementDisplayed(loadWebElement(elementName, pageClassName));
        if(elementState.isEmpty()){
            assertThat("Element should be displayed",  true, is(isElementDisplayed));
        }
        if(!elementState.isEmpty()){
            assertThat("Element should not be displayed",  false, is(isElementDisplayed));
        }
    }

    /**
     * Step to check that the element should be checked or not on the page
     */
    @Given("I verify that the {string} checkbox is checked|unchecked on the {string} page")
    public void i_verify_that_the_checkbox_is_checked_unchecked_on_the_page(String elementName, String elementState, String pageClassName) {
        boolean isElementChecked = driverManagerUtils.isElementSelected(loadWebElement(elementName, pageClassName));
        if(elementState.equals("checked")){
            assertThat("Element should not be unchecked",  true, is(isElementChecked));
        }
        if(elementState.equals("unchecked")){
            assertThat("Element should not be checked",  false, is(isElementChecked));
        }
    }

    /**
     * Step to check that the element should be checked or not on the page
     */
    @Then("^I should see alert text as \"(.*?)\"$")
    public void check_alert_text(String eAlertMessage)
    {
        String aAlertMessage = driverManagerUtils.getAlertText();
        assertThat("Alert messages are not matching",  aAlertMessage, is(eAlertMessage));
    }
}

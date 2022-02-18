package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.context.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrebuiltInputSteps extends Base {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrebuiltBrowserSteps.class);
    TestContext testContext;
    public PrebuiltInputSteps(TestContext context) {
        super(context);
        this.testContext = context;
    }

    @Given("I click the {string} on the {string} page")
    public void iClickTheElementOnThePage(String elementName, String pageClassName) {
        driverManagerUtils.clickElement(driver, loadWebElement(elementName, pageClassName));
    }

    @Given("I click the {string} using java script executor on the {string} page")
    public void ClickJSElement(String elementName, String pageClassName) throws Exception
    {
        driverManagerUtils.clickJSElement(loadWebElement(elementName, pageClassName));
    }

    @Given("I double click the {string} on the {string} page")
    public void iDoubleClickTheElementOnThePage(String elementName, String pageClassName) {
        driverManagerUtils.doubleClickTheElement(loadWebElement(elementName, pageClassName));
    }

    @Given("I enter the value {string} into the {string} on the {string} page")
    public void iEnterTheValueInToTheFieldOnThePage(String valueToEnter, String elementName, String pageClassName) {
        WebElement element = loadWebElement(elementName, pageClassName);
        driverManagerUtils.enterText(driver, element, valueToEnter);
    }

    @Given("I select the {string} option type with value {string} from the {string} dropdown menu on the page {string}")
    public void iSelectFromDropDown(String optionType, String optionValue, String elementName, String pageClassName) {
        WebElement element = loadWebElement(elementName, pageClassName);
        driverManagerUtils.selectOptionFromDropdown(element, optionType, optionValue);
    }

    @Then("I accept the alert")
    public void acceptAlert(){
        driverManagerUtils.acceptTheAlert();
    }

    @Then("I dismiss the alert")
    public void dismissAlert(){
        driverManagerUtils.dismissTheAlert();
    }
}

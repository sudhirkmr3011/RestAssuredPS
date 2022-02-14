package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import com.hsbc.qe.ui.page_objects.TestContext;
import io.cucumber.java.en.Then;

public class CommonAssertionSteps {

    TestContext testContext;
    DriverHelper driverHelper;

    public CommonAssertionSteps(TestContext testContext){
        this.testContext = testContext;
        this.driverHelper = testContext.getDriverHelper();
    }

    /** page title checking
     * @param present :
     * @param title :
     */
    @Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
    public void check_title(String present,String title)
    {
        System.out.println("Present :" + present.isEmpty());
        driverHelper.checkTitle(title);
    }


    // step to check element partial text
    @Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
    public void check_partial_text(String partialTextTitle)
    {
        driverHelper.checkPartialTitle(partialTextTitle);
    }

    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have text as \"(.*?)\"$")
    public void check_element_text(String elementLocatorType, String elementLocatorName,String expectedValue) throws Exception
    {
        driverHelper.validateLocator(elementLocatorType);
        driverHelper.checkElementText(elementLocatorType, elementLocatorName, expectedValue);
    }

    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have partial text as \"(.*?)\"$")
    public void check_element_partial_text(String elementLocatorType, String elementLocatorName,String expectedValue) throws Exception
    {
        driverHelper.validateLocator(elementLocatorType);
        driverHelper.checkElementPartialText(elementLocatorType, elementLocatorName, expectedValue);
    }

    // step to check attribute value
    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+have attribute \"(.*?)\" with value \"(.*?)\"$")
    public void check_element_attribute(String type,String accessName,String attrb,String value) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.checkElementAttribute(type, attrb, value, accessName);
    }

    // step to check element enabled or not
    @Then("^element having (.+) \"([^\"]*)\" should\\s*((?:not)?)\\s+be (enabled|disabled)$")
    public void check_element_enable(String type, String accessName,String present,String state) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.checkElementEnable(type, accessName);
    }

    //step to check element present or not
    @Then("^element having (.+) \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
    public void check_element_presence(String type,String accessName,String present) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.checkElementIsVisible(type, accessName);
    }

    //step to assert checkbox is checked or unchecked
    @Then("^checkbox having (.+) \"(.*?)\" should be (checked|unchecked)$")
    public void is_checkbox_checked(String type, String accessName,String state) throws Exception
    {
        driverHelper.validateLocator(type);
        boolean flag = state.equals("checked");
        driverHelper.isCheckboxSelected(type, accessName, flag);
    }

    @Then("^radio button having (.+) \"(.*?)\" should be (selected|unselected)$")
    public void is_radio_button_selected(String type,String accessName,String state) throws Exception
    {
        driverHelper.validateLocator(type);
        boolean flag = state.equals("selected");
        driverHelper.isRadioButtonSelected(type, accessName, flag);
    }

    @Then("^option \"(.*?)\" by (.+) from radio button group having (.+) \"(.*?)\" should be (selected|unselected)$")
    public void is_option_from_radio_button_group_selected(String option,String attrb,String type,String accessName,String state) throws Exception
    {
        driverHelper.validateLocator(type);
        boolean flag = state.equals("selected");
        driverHelper.isOptionFromRadioButtonGroupSelected(type,attrb,option,accessName,flag);
    }

    @Then("^link having text \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
    public void check_element_presence(String accessName,String present)
    {
        driverHelper.checkElementIsVisible("linkText",accessName);
    }

    //steps to check partail link presence
    @Then("^link having partial text \"(.*?)\" should\\s*((?:not)?)\\s+be present$")
    public void check_partial_element_presence(String accessName,String present)
    {
        driverHelper.checkElementIsVisible("partialLinkText", accessName);
    }

    //step to assert javascript pop-up alert text
    @Then("^I should see alert text as \"(.*?)\"$")
    public void check_alert_text(String actualValue)
    {
        driverHelper.checkAlertText(actualValue);
    }



}

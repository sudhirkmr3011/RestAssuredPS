package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import com.hsbc.qe.ui.page_objects.TestContext;
import io.cucumber.java.en.Then;

public class CommonInputSteps {

    TestContext testContext;
    DriverHelper driverHelper;

    public CommonInputSteps(TestContext testContext){
        this.testContext = testContext;
        this.driverHelper = testContext.getDriverHelper();
    }


    //Input steps

    // enter text into input field steps
    @Then("^I enter \"([^\"]*)\" into input field having (.+) \"([^\"]*)\"$")
    public void enter_text(String text, String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.enterValue(type, text, accessName);
    }

    // clear input field steps
    @Then("^I clear input field having (.+) \"([^\"]*)\"$")
    public void clear_text(String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.clearField(type, accessName);
    }

    @Then("^I select \"(.*?)\" option by (.+) from dropdown having (.+) \"(.*?)\"$")
    public void select_option_from_dropdown(String option,String optionBy,String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.validateOptionBy(optionBy);
        driverHelper.selectOptionFromDropdown(type,optionBy, option, accessName);
    }

    @Then("^I select (\\d+) option by index from dropdown having (.+) \"(.*?)\"$")
    public void select_option_from_dropdown_by_index(String option, String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.selectOptionFromDropdown(type,"selectByIndex", option, accessName);
    }

    @Then("^I select \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
    public void select_option_from_multiselect_dropdown(String option,String optionBy, String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.validateOptionBy(optionBy);
        driverHelper.selectOptionFromDropdown(type,optionBy, option, accessName);
    }

    @Then("^I select (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
    public void select_option_from_multiselect_dropdown_by_index(String option, String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.selectOptionFromDropdown(type,"selectByIndex", option, accessName);
    }

    // deselect option by text/value from multiselect
    @Then("^I deselect \"(.*?)\" option by (.+) from multiselect dropdown having (.+) \"(.*?)\"$")
    public void deselect_option_from_multiselect_dropdown(String option,String optionBy, String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.validateOptionBy(optionBy);
        driverHelper.deselectOptionFromDropdown(type, optionBy, option, accessName);
    }

    @Then("^I deselect (\\d+) option by index from multiselect dropdown having (.+) \"(.*?)\"$")
    public void deselect_option_from_multiselect_dropdown_by_index(String option, String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.deselectOptionFromDropdown(type, "selectByIndex", option, accessName);
    }


    @Then("^I deselect all options from multiselect dropdown having (.+) \"(.*?)\"$")
    public void unselect_all_option_from_multiselect_dropdown(String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.unselectAllOptionFromMultiselectDropdown(type, accessName);
    }

    //check checkbox steps
    @Then("^I check the checkbox having (.+) \"(.*?)\"$")
    public void check_checkbox(String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.checkCheckbox(type, accessName);
    }

    @Then("^I uncheck the checkbox having (.+) \"(.*?)\"$")
    public void uncheck_checkbox(String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.uncheckCheckbox(type, accessName);
    }

    @Then("^I toggle checkbox having (.+) \"(.*?)\"$")
    public void toggle_checkbox(String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.toggleCheckbox(type, accessName);
    }

    @Then("^I select radio button having (.+) \"(.*?)\"$")
    public void select_radio_button(String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.selectRadioButton(type, accessName);
    }

    // steps to select option by text from radio button group
    @Then("^I select \"(.*?)\" option by (.+) from radio button group having (.+) \"(.*?)\"$")
    public void select_option_from_radio_btn_group(String option,String by, String type, String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.selectOptionFromRadioButtonGroup(type, option, by, accessName);
    }


    // click on web element
    @Then("^I click on element having (.+) \"(.*?)\"$")
    public void click(String type,String accessName) throws Exception
    {
        driverHelper.validateLocator(type);
        driverHelper.click(type, accessName);
    }

//    //Forcefully click on element
//    @Then("^I forcefully click on element having (.+) \"(.*?)\"$")
//    public void click_forcefully(String type,String accessName) throws Exception
//    {
//        miscmethodObj.validateLocator(type);
//        clickObj.clickForcefully(type,accessName);
//    }
//
//    // double click on web element
//    @Then("^I double click on element having (.+) \"(.*?)\"$")
//    public void double_click(String type, String accessValue) throws Exception
//    {
//        miscmethodObj.validateLocator(type);
//        clickObj.doubleClick(type, accessValue);
//    }
//
//    // steps to click on link
//    @Then("^I click on link having text \"(.*?)\"$")
//    public void click_link(String accessName)
//    {
//        clickObj.click("linkText", accessName);
//    }
//
//    //Step to click on partial link
//    @Then("^I click on link having partial text \"(.*?)\"$")
//    public void click_partial_link(String accessName)
//    {
//        clickObj.click("partialLinkText", accessName);
//    }
//
}

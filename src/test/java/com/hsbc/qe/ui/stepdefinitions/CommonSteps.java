package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import com.hsbc.qe.ui.page_objects.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static com.hsbc.qe.ui.common.BaseDriverSetup.configuration;

public class CommonSteps {

    TestContext testContext;
    DriverHelper driverHelper;

    public CommonSteps(TestContext testContext){
        this.testContext=testContext;
        this.driverHelper = testContext.getDriverHelper();
    }

    @Given("^I navigate to \"([^\"]*)\"$")
    public void navigate_to(String link)
    {
        driverHelper.navigate(configuration.baseUrl());
    }

    @Then("^I navigate to forward")
    public void navigate_forward()
    {
        driverHelper.getDriver().navigate().forward();
    }

    @Then("^I navigate to back")
    public void navigate_back()
    {
        driverHelper.getDriver().navigate().back();
    }

    @Then("^I refresh the page$")
    public void refresh_page()
    {
        driverHelper.getDriver().navigate().refresh();
    }

    //Switch to new window
    @Then("^I switch to new window$")
    public void switch_to_new_window()
    {
        driverHelper.switchToNewWindow();
    }

    //Switch to old window
    @Then("^I switch to previous window$")
    public void switch_to_old_window()
    {
        driverHelper.switchBackToOldWindow();
    }

    @Then("^I switch to window having title \"(.*?)\"$")
    public void switch_to_window_by_title(String windowTitle) throws Exception
    {
        driverHelper.switchToWindowByTitle(windowTitle);
    }

    @Then("^I close new window$")
    public void close_new_window()
    {
        driverHelper.closeNewWindow();
    }

    @Then("^I switch to frame having (.+) \"(.*?)\"$")
    public void switch_frame_by_element(String method, String value)
    {
        driverHelper.switchFrame(method, value);
    }

    @Then("^I switch to main content$")
    public void switch_to_default_content()
    {
        driverHelper.switchToDefaultContent();
    }

    // To interact with browser

    // step to resize browser
    @Then("^I resize browser window size to width (\\d+) and height (\\d+)$")
    public void resize_browser(int width, int heigth)
    {
        driverHelper.resizeBrowser(width, heigth);
    }

    // step to maximize browser
    @Then("^I maximize browser window$")
    public void maximize_browser()
    {
        driverHelper.maximizeBrowser();
    }

    @Then("^I close browser$")
    public void close_browser()
    {
        driverHelper.quitDriver();
    }

    @Then("^I zoom in page$")
    public void zoom_in()
    {
        driverHelper.zoomInOut("ADD");
    }

    @Then("^I zoom out page$")
    public void zoom_out()
    {
        driverHelper.zoomInOut("SUBTRACT");
    }

    @Then("^I reset page view$")
    public void reset_page_zoom()
    {
        driverHelper.zoomInOut("reset");
    }

    @Then("^I scroll to (top|end) of page$")
    public void scroll_page(String to) throws Exception
    {
        driverHelper.scrollPage(to);
    }

    @Then("^I scroll to element having (.+) \"(.*?)\"$")
    public void scroll_to_element(String elementLocatorType, String accessName) throws Exception
    {
        driverHelper.validateLocator(elementLocatorType);
        driverHelper.scrollToElement(elementLocatorType, accessName);
    }

    // Note: Doesn't work on Windows firefox
    @Then("^I hover over element having (.+) \"(.*?)\"$")
    public void hover_over_element(String elementLocatorType, String elementLocatorName) throws Exception
    {
        driverHelper.validateLocator(elementLocatorType);
        driverHelper.hoverOverElement(elementLocatorType, elementLocatorName);
    }


    // step to select dropdown list
    @Then("^option \"(.*?)\" by (.+) from dropdown having (.+) \"(.*?)\" should be (selected|unselected)$")
    public void is_option_from_dropdown_selected(String option,String by,String type,String accessName,String state) throws Exception
    {
        driverHelper.validateLocator(type);
        boolean flag = state.equals("selected");
        driverHelper.isOptionFromDropdownSelected(type,by,option,accessName,flag);
    }

//    //Progress methods
//
//    // wait for specific period of time
//    @Then("^I wait for (\\d+) sec$")
//    public void wait(String time) throws NumberFormatException, InterruptedException
//    {
//        progressObj.wait(time);
//    }
//
//    //wait for specific element to display for specific period of time
//    @Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to display$")
//    public void wait_for_ele_to_display(String duration, String type, String accessName) throws Exception
//    {
//        miscmethodObj.validateLocator(type);
//        progressObj.waitForElementToDisplay(type, accessName, duration);
//    }
//
//    // wait for specific element to enable for specific period of time
//    @Then("^I wait (\\d+) seconds for element having (.+) \"(.*?)\" to be enabled$")
//    public void wait_for_ele_to_click(String duration, String type, String accessName) throws Exception
//    {
//        miscmethodObj.validateLocator(type);
//        progressObj.waitForElementToClick(type, accessName, duration);
//    }
//
//    //JavaScript handling steps
//
//    //Step to handle java script
//    @Then("^I accept alert$")
//    public void handle_alert()
//    {
//        javascriptObj.handleAlert("accept");
//    }
//
//    //Steps to dismiss java script
//    @Then("^I dismiss alert$")
//    public void dismiss_alert()
//    {
//        javascriptObj.handleAlert("dismiss");
//    }
//
//    //Screen shot methods
//
//    @Then("^I take screenshot$")
//    public void take_screenshot() throws IOException
//    {
//        screenshotObj.takeScreenShot();
//    }
}

package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.context.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrebuiltBrowserSteps extends BaseSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrebuiltBrowserSteps.class);
    TestContext testContext;

    public PrebuiltBrowserSteps(TestContext context) {
        super(context);
        this.testContext = context;
    }

    @Given("^I am in App main site")
    public void iAmInAppMainSite() {
        LOGGER.info("Navigate to: " + configuration.url());
        driver.get(configuration.url());
        driverManagerUtils.HandleMyWindows.put("Principal", driver.getWindowHandle());
        driverManagerUtils.verifyPageLoaded();
    }

    @Given("^I navigate to \"([^\"]*)\"$")
    public void navigate_to(String link)
    {
        driverManagerUtils.navigateTo(link);
    }

    @Given("^I navigate forward on the browser")
    public void navigate_forward()
    {
        driverManagerUtils.navigateForward();
    }

    @Then("^I navigate back on the browser")
    public void navigate_back()
    {
        driverManagerUtils.navigateBack();
    }

    @Then("^I refresh the current web page$")
    public void refresh_page()
    {
        driverManagerUtils.refreshPage();
    }

    @When("^I switch to new window$")
    public void switchNewWindow()
    {
        driverManagerUtils.switchToNewWindow();
    }

    /** Switch to a new windows by name */
    @When("^I go to (.*?) window$")
    public void switchNewNamedWindow(String windowsName)
    {
        driverManagerUtils.windowHandle(windowsName);
    }

    /** Switch to the previous windows */
    @When("^I switch to previous window$")
    public void switchPreviousWindows()
    {
        LOGGER.info("Switching of previous windows");
        driver.switchTo().defaultContent();

    }

    /** Close a windows by title */
    @And("^I close window \"(.*?)\"$")
    public void closeNewWindows(String windowTitle)
    {
        driverManagerUtils.closeWindowByName(windowTitle);
    }

    @Then("^I maximize the windows")
    public void iMaximizeTheWindows() {
        driver.manage().window().maximize();
    }

    /** Zoom out until the element is displayed  */
    @And("^I zoom out page till I see element \"(.*?)\"$")
    public void zoomTillElementDisplay(String elementName, String pageClassName) throws Exception
    {
        driverManagerUtils.zoomTillElementDisplay(loadWebElement(elementName, pageClassName));
    }

    @Given("^I open new tab with URL (.*)")
    public void OpenNewTabWithURL(String url){
        driverManagerUtils.openNewTabWithURL(url);
    }


    @And("I switch to parent frame")
    public void iSwitchToParentFrame() {
        driverManagerUtils.switchToParentFrame();
    }

    @Given("I switch to the {string} frame on the {string}")
    public void i_switch_to_the_frame_on_the(String elementName, String pageClassName) {
        driverManagerUtils.switchToFrame(loadWebElement(elementName, pageClassName));
    }

    // step to switch to main content
    @Then("^I switch to main content of the page$")
    public void switch_to_default_content()
    {
        driverManagerUtils.switchToDefaultContent();
    }

    /** Scroll to the (top/end) of the page. */
    @And("^I scroll to (top|end) of page$")
    public void scrollPage(String to) throws Exception
    {
        driverManagerUtils.scrollPage(to);
    }

    /** Scroll to an element. */
    @And("^I scroll to element (.+)$")
    public void scrollToElement(String elementName, String pageClassName) throws Exception
    {
        driverManagerUtils.scrollToElement(loadWebElement(elementName, pageClassName));
    }
}

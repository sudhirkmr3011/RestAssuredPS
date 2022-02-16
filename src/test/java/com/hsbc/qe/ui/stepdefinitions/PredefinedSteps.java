package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.config.Configuration;
import com.hsbc.qe.ui.config.ConfigurationManager;
import com.hsbc.qe.ui.page_objects.PageObjectLoader;
import com.hsbc.qe.ui.webdriver.DriverManager;
import com.hsbc.qe.ui.webdriver.DriverManagerUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PredefinedSteps extends PageObjectLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(PredefinedSteps.class);

    public static Configuration configuration = ConfigurationManager.getConfiguration();

    public static String status = "passed";
    WebDriver driver;
    DriverManagerUtils driverManagerUtils;

    public PredefinedSteps() {
        driver = DriverManager.getDriver();
        driverManagerUtils = new DriverManagerUtils();
    }


    /**
     *********************************************************************
     * NAVIGATION STEPS
     *********************************************************************
     */
    @Given("^I am in App main site")
    public void iAmInAppMainSite() {
        LOGGER.info("Navigate to: " + configuration.url());
        driver.get(configuration.url());
        driverManagerUtils.HandleMyWindows.put("Principal", driver.getWindowHandle());
        driverManagerUtils.verifyPageLoaded();
    }

    @Then("^I navigate to \"([^\"]*)\"$")
    public void navigate_to(String link)
    {
        driverManagerUtils.navigateTo(link);
    }
    @Then("^I navigate forward on the browser")
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


    /**
     *********************************************************************
     * BROWSER WINDOW INTERACTION STEPS
     *********************************************************************
     **/

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

    /**
     *********************************************************************
     * BROWSER WINDOW TABS HANDLING STEPS
     *********************************************************************
     **/

    @Given("^I open new tab with URL (.*)")
    public void OpenNewTabWithURL(String url){
        driverManagerUtils.openNewTabWithURL(url);
    }

    /**
     *********************************************************************
     * BROWSER WINDOW FRAMES HANDLING STEPS
     *********************************************************************
     **/
    @And("I switch to parent frame")
    public void iSwitchToParentFrame() {
        driverManagerUtils.switchToParentFrame();
    }

    /** I switch to Frame */
    @When("^I switch to Frame: (.*?)$")
    public void switchToFrame(WebElement frameLocator) throws Exception {
        driverManagerUtils.switchToFrame(frameLocator);
    }

    /**
     *********************************************************************
     * WEB ELEMENT INTERACTION STEPS
     *********************************************************************
     **/
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

    @Given("I enter the value {string} into the field {string} on the {string} page")
    public void iEnterTheValueInToTheFieldOnThePage(String valueToEnter, String elementName, String pageClassName) {
        WebElement element = loadAndFindPageElement(elementName, pageClassName);
        driverManagerUtils.enterText(driver, element, valueToEnter);
    }

    @And("I select (.*?) option from the (.*?) on the page(.*?)$")
    public void iSetTextInDropdown(String option, String elementName, String pageClassName) throws Exception {
        WebElement element = loadAndFindPageElement(elementName, pageClassName);
        driverManagerUtils.selectOptionDropdownByText(element, option);
    }

    @Given("I select the {string} option type with value {string} from the {string} dropdown menu on the page {string}")
    public void iSelectTheOptionTypeWithValueFromTheDropdownMenuOnThePage(String optionType, String optionValue, String elementName, String pageClassName) {
        WebElement element = loadAndFindPageElement(elementName, pageClassName);
        driverManagerUtils.selectOptionFromDropdown(element, optionType, optionValue);
    }

    /**
     *********************************************************************
     * ALERTS INTERACTION STEPS
     *********************************************************************
     **/
    @Given("I accept alert on the page")
    public void iAcceptAlertOnThePage() {
        driverManagerUtils.acceptTheAlert();
    }

    @Given("I dismiss alert on the page")
    public void iDismissAlertOnThePage() {
        driverManagerUtils.dismissTheAlert();
    }

    /**
     *********************************************************************
     * WAIT FOR ELEMENTS STEPS
     *********************************************************************
     **/
    /** Wait for an element to be present for a specific period of time */
    @Then("^I wait for element (.*) to be present on the (.*) page$")
    public void waitForElementPresent(String elementName,String pageClassName) throws Exception
    {
        driverManagerUtils.waitForElementPresent(loadWebElement(elementName, pageClassName));
    }

    /**Wait for an element to be visible for a specific period of time */
    @Then("^I wait element (.*?) to be visible on the (.*) page$$")
    public void waitForElementVisible(String elementName, String pageClassName) throws Exception
    {
        driverManagerUtils.waitForElementVisible(loadWebElement(elementName, pageClassName));
    }


    /**
     *********************************************************************
     * ASSERTION STEPS
     *********************************************************************
     **/
    @Given("I verify that the (.*) text matches the text (.*) on the (.*) page")
    public void iVerifyThatTheElementTextMatchesTheTextOnThePage(String elementName, String expectedValue, String pageClassName) {
        String actualValue = driverManagerUtils.getElementText(driver, loadWebElement(elementName,pageClassName));
        assertThat("Element text not matching", actualValue, is(equalTo(expectedValue)));
    }

    @Given("I verify that the (.*) field on the (.*) page")
    public void iVerifyThatTheFieldOnThePage(String elementName, String pageClassName) throws Exception {
        boolean elementIsVisible = driverManagerUtils.isElementDisplayed(loadWebElement(elementName, pageClassName));
        assertThat("Element not visible on the page", elementIsVisible, equalTo(true));
    }

    @Then("^I should\\s*((?:not)?)\\s+see page title as \"(.+)\"$")
    public void checkPageTitle(String ePageTitleText)
    {
        String aPageTitle = driverManagerUtils.getPageTitle();
        assertThat("Page tiles are not matching", aPageTitle, equalTo(ePageTitleText));
    }

    @Then("^I should\\s*((?:not)?)\\s+see page title having partial text as \"(.*?)\"$")
    public void checkPageTitlePartially(String ePartialTitleText)
    {
        String aPageTitleText = driverManagerUtils.getPageTitle();
        assertThat("Page tiles are not matching partially", aPageTitleText, equalTo(ePartialTitleText));
    }

    private WebElement loadWebElement(String elementName, String pageClassName) {
        return loadAndFindPageElement(elementName, pageClassName);
    }
}

package com.hsbc.qe.ui.common.driver_helpers;

import com.hsbc.qe.ui.common.BaseDriverSetup;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class DriverHelper extends BaseDriverSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverHelper.class);
    private static final int TIME_OUT_IN_SECONDS = 60;

    WebDriverWait wait;

    public DriverHelper(){
       wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
    }


    /**
     * Function to wait for presence of the element
     *
     * @param driver  Web driver object
     * @param locator web element to identify
     * @return
     */
    public WebElement explicitlyWaitForElement(WebDriver driver, WebElement locator) {
        WebElement foundLocator = null;
        try {
            WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
            wait.ignoring(StaleElementReferenceException.class);
            foundLocator = wait.until(ExpectedConditions.visibilityOf(locator));
        } catch (TimeoutException e) {
            handleException(e, locator);
        }
        return foundLocator;
    }


    /**
     * Function to wait for frame and switch to it
     *
     * @param driver  Web driver object
     * @param locator web element to identify
     */
    public void explicitlyWaitForFrame(WebDriver driver, WebElement locator) {
        try {
            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
            wait.ignoring(StaleElementReferenceException.class);
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
        } catch (TimeoutException e) {
            handleException(e, locator);
        }
    }

    /**
     * Navigate to specific url
     *
     * @param url Url to navigate
     */
    public void navigate(String url) {
        driver.get(url);
    }

    public void refresh() {
        driver.navigate().back();
    }

    /**
     * Function to handle exception
     *
     * @param e       exception
     * @param locator web element to identify
     */
    private void handleException(TimeoutException e, WebElement locator) {
        throw new RuntimeException("Timeout looking for " + locator.toString() + " after " + TIME_OUT_IN_SECONDS + " seconds at page " + driver.getCurrentUrl());
    }


    /**
     * Function to return the web page tile
     *
     */
    public String getPageTitle() {
        return driver.getTitle();
    }

    /**
     * Function to click
     *
     * @param accessType Web element locator type
     * @param  accessName Web element locator name
     */
    public void click(String accessType, String accessName) {
        WebElement element = driver.findElement(getElementByType(accessType, accessName));
        try {
            findElementAndHighlight(driver, element, "red");
            element.click();
        } catch (NoSuchElementException e) {
            handleException(e);
        } catch (StaleElementReferenceException e) {
            findElementAndHighlight(driver, element, "red");
            element.click();
        }
    }

    public void click(WebElement element) {
        try {
            findElementAndHighlight(driver, element, "red");
            element.click();
        } catch (NoSuchElementException e) {
            handleException(e);
        } catch (StaleElementReferenceException e) {
            findElementAndHighlight(driver, element, "red");
            element.click();
        }
    }

    protected void handleException(NoSuchElementException e) {
        Assert.fail(e.getMessage() + " on page " + driver.getCurrentUrl());
    }
    /**
     * function to wait for element to be clickable
     *
     * @param driver  Webdriver object
     * @param locator By object to identify
     * @return returns element
     */
    public WebElement explicitlyWaitForClickable(WebDriver driver, By locator) {
        WebElement foundLocator = null;
        WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        wait.ignoring(StaleElementReferenceException.class);
        foundLocator = wait.until(ExpectedConditions.elementToBeClickable(locator));
        return foundLocator;
    }

    /**
     * Function to switch to default content/window
     */
    public void switchToDefaultWindow() {
        driver.switchTo().defaultContent();
    }

    public void switchToNewWindow() {
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    public void switchToNewTab() {
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void switchBackToOldWindow() {
        String originalWindow = driver.getWindowHandle();
        driver.switchTo().window(originalWindow);
    }

    /** Method to switch to window by title
     * @param windowTitle : String : Name of window title to switch
     * @throws Exception */
    public void switchToWindowByTitle(String windowTitle) throws Exception
    {
        boolean windowFound = false;
        for(String winHandle : driver.getWindowHandles())
        {
            String currentWindowName = driver.switchTo().window(winHandle).getTitle();
            if (currentWindowName.equals(windowTitle))
            {
                windowFound = true;
                break;
            }
        }
        if (!windowFound)
            throw new Exception("Window having title "+windowTitle+" not found");
    }

    /**Method to close new window*/
    public void closeNewWindow()
    {
        driver.close();
    }

    /** Method to switch frame using web element frame
     * @param accessType : String : Locator type (index, id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * */
    public void switchFrame(String accessType, String accessName)
    {
        if(accessType.equalsIgnoreCase("index"))
            driver.switchTo().frame(accessName);
        else
        {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
            driver.switchTo().frame(element);
        }
    }

    /** method to switch to default content*/
    public void switchToDefaultContent()
    {
        driver.switchTo().defaultContent();
    }

    /** Method to resize browser
     * @param width : int : Width for browser resize
     * @param height : int : Height for browser resize
     */
    public void resizeBrowser(int width, int height)
    {
        driver.manage().window().setSize(new Dimension(width,height));
    }

    /** Method to maximize browser	 */
    public void maximizeBrowser()
    {
        driver.manage().window().maximize();
    }

    /** Method to quite webdriver instance */
    public void quitDriver()
    {
        driver.quit();
    }

    /** Method to zoom in/out page
     * @param inOut : String : Zoom in or out
     */
    public void zoomInOut(String inOut)
    {
        WebElement Sel= driver.findElement(getElementByType("tagName","html"));
        if(inOut.equals("ADD"))
            Sel.sendKeys(Keys.chord(getKey(), Keys.ADD));
        else if(inOut.equals("SUBTRACT"))
            Sel.sendKeys(Keys.chord(getKey(), Keys.SUBTRACT));
        else if(inOut.equals("reset"))
            Sel.sendKeys(Keys.chord(getKey(), Keys.NUMPAD0));
    }

    /** Method to return key by OS wise
     * @return Keys : Return control or command key as per OS
     */
    public Keys getKey()
    {
        String os = System.getProperty("os.name").toLowerCase();
        if(os.contains("win"))
            return Keys.CONTROL;
        else if (os.contains("nux") || os.contains("nix"))
            return Keys.CONTROL;
        else if (os.contains("mac"))
            return Keys.COMMAND;
        else
            return null;
    }

    /** Method to scroll page to top or end
     * @param to : String : Scroll page to Top or End
     * @throws Exception
     */
    public void scrollPage(String to) throws Exception
    {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        if (to.equals("end"))
            executor.executeScript("window.scrollTo(0,Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight));");
        else if (to.equals("top"))
            executor.executeScript("window.scrollTo(Math.max(document.documentElement.scrollHeight,document.body.scrollHeight,document.documentElement.clientHeight),0);");
        else
            throw new Exception("Exception : Invalid Direction (only scroll \"top\" or \"end\")");
    }

    /** Method to scroll page to particular element
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     */
    public void scrollToElement(String accessType, String accessName)
    {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Function to type value
     *
     * @param accessType Access type of the element
     * @param accessName Access name of the element
     * @param value  Input to be entered
     */

    public void enterValue(String accessType, String accessName, String value) {

        WebElement element = driver.findElement(getElementByType(accessType, accessName));
        try {
            findElementAndHighlight(driver, element, "red");
            element.click();
            element.clear();
            element.sendKeys(value);
        } catch (NoSuchElementException e) {
            handleException(e);
        }
    }

    public void enterValue(WebElement element, String value) {
        try {
            findElementAndHighlight(driver, element, "red");
            element.click();
            element.clear();
            element.sendKeys(value);
        } catch (NoSuchElementException e) {
            handleException(e);
        }
    }

    public void clearField(String accessType, String accessName) {
        WebElement element = driver.findElement(getElementByType(accessType, accessName));
        try {
            element.clear();
        } catch (Exception e) {
            LOGGER.error("Error in clearing the text from the element: {}", element.getText());
        }
    }

    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            LOGGER.error("Error in clearing the text from the element: {}", element.getText());
        }
    }

    /**
     * @param: String locator. This method verifies if the element is visible.
     */
    public void verifyElementIsVisible(WebDriver driver, WebElement locator, String locatorName) {
        findElementAndHighlight(driver, locator, "green");
        assertTrue(locator.isDisplayed());
        LOGGER.info(locatorName + " is displayed");
    }


    /**
     * Function to replace a string
     *
     * @param regex       regular expression to replace
     * @param actualValue value to be replaced
     * @return updated string
     */
    public String getStringFormat(String regex, String actualValue) {
        return String.format(regex, actualValue);
    }

    /**
     * Function to dynamically generate the webelement by substituting strings
     *
     * @param driver            Webdriver object
     * @param locator           Webelement
     * @param runtimeValue      value to be passed during the run time
     * @param substitutionValue value to be substituted
     * @return
     */
    public WebElement prepareWebElementWithDynamicValue(WebDriver driver, String locator, String runtimeValue,
                                                        String substitutionValue) {
        WebDriverWait wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
        WebElement we = null;
        try {
            if (locator.equalsIgnoreCase("id")) {

                we = driver.findElement(By.id(getStringFormat(runtimeValue, substitutionValue)));

            } else if (locator.equalsIgnoreCase("class")) {
                we = driver.findElement(By.className(getStringFormat(runtimeValue, substitutionValue)));
            } else if (locator.equalsIgnoreCase("name")) {
                we = driver.findElement(By.name(getStringFormat(runtimeValue, substitutionValue)));
            } else if (locator.equalsIgnoreCase("css")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(getStringFormat(runtimeValue, substitutionValue))));
                we = driver.findElement(By.cssSelector(getStringFormat(runtimeValue, substitutionValue)));
            } else if (locator.equalsIgnoreCase("xpath")) {
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(getStringFormat(runtimeValue, substitutionValue))));
                we = driver.findElement(By.xpath(getStringFormat(runtimeValue, substitutionValue)));
            } else if (locator.equalsIgnoreCase("linktext")) {
                we = driver.findElement(By.linkText(getStringFormat(runtimeValue, substitutionValue)));
            } else {
                return null;
            }

        } catch (NoSuchElementException ignore) {
            handleException(ignore);
        }
        return we;
    }


    //NEW METHODS
    public void waitAndClickElement(WebElement element) throws InterruptedException {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(element)).click();
                LOGGER.info("Successfully clicked on the WebElement: " + "<" + element.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                LOGGER.error("Unable to wait and click on WebElement, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
            attempts++;
        }
    }

    public void waitAndClickElementsUsingByLocator(By by) throws InterruptedException {
        boolean clicked = false;
        int attempts = 0;
        while (!clicked && attempts < 10) {
            try {
                this.wait.until(ExpectedConditions.elementToBeClickable(by)).click();
                LOGGER.info("Successfully clicked on the element using by locator: " + "<" + by.toString() + ">");
                clicked = true;
            } catch (Exception e) {
                LOGGER.error("Unable to wait and click on the element using the By locator, Exception: " + e.getMessage());
                Assert.fail("Unable to wait and click on the element using the By locator, element: " + "<" + by.toString() + ">");
            }
            attempts++;
        }
    }

    public void clickOnTextFromDropdownList(WebElement list, String textToSearchFor) throws Exception {
        Wait<WebDriver> tempWait = new WebDriverWait(driver, 30);
        try {
            tempWait.until(ExpectedConditions.elementToBeClickable(list)).click();
            list.sendKeys(textToSearchFor);
            list.sendKeys(Keys.ENTER);
            LOGGER.info("Successfully sent the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to send the following keys: " + textToSearchFor + ", to the following WebElement: " + "<" + list.toString() + ">");
            Assert.fail("Unable to select the required text from the dropdown menu, Exception: " + e.getMessage());
        }
    }


    public void clickOnElementUsingCustomTimeout(WebElement locator, WebDriver driver, int timeout) {
        try {
            final WebDriverWait customWait = new WebDriverWait(driver, timeout);
            customWait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
            locator.click();
            LOGGER.info("Successfully clicked on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
        } catch (Exception e) {
            LOGGER.error("Unable to click on the WebElement, using locator: " + "<" + locator + ">" + ", using a custom Timeout of: " + timeout);
            Assert.fail("Unable to click on the WebElement, Exception: " + e.getMessage());
        }
    }

    // ACTION METHODS
    public void actionMoveAndClick(WebElement element) throws Exception {
        Actions ob = new Actions(driver);
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            ob.moveToElement(element).click().build().perform();
            LOGGER.info("Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(elementToClick)).isEnabled();
            if (elementPresent == true) {
                ob.moveToElement(elementToClick).click().build().perform();
                LOGGER.info("(Stale Exception) - Successfully Action Moved and Clicked on the WebElement, using locator: " + "<" + element.toString() + ">");
            }
        } catch (Exception e) {
            LOGGER.error("Unable to Action Move and Click on the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public void actionMoveAndClickByLocator(By element) throws Exception {
        Actions ob = new Actions(driver);
        try {
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(element)).isEnabled();
            if (elementPresent == true) {
                WebElement elementToClick = driver.findElement(element);
                ob.moveToElement(elementToClick).click().build().perform();
                LOGGER.info("Action moved and clicked on the following element, using By locator: " + "<" + element.toString() + ">");
            }
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement elementToClick = driver.findElement(element);
            ob.moveToElement(elementToClick).click().build().perform();
            LOGGER.info("(Stale Exception) - Action moved and clicked on the following element, using By locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to Action Move and Click on the WebElement using by locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to Action Move and Click on the WebElement using by locator, Exception: " + e.getMessage());
        }
    }


    // SEND KEYS METHODS

    public void sendKeysToWebElement(WebElement element, String textToSend) throws Exception {
        try {
            this.WaitUntilWebElementIsVisible(element);
            element.clear();
            element.sendKeys(textToSend);
            LOGGER.info("Successfully Sent the following keys: '" + textToSend + "' to element: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to locate WebElement: " + "<" + element.toString() + "> and send the following keys: " + textToSend);
            Assert.fail("Unable to send keys to WebElement, Exception: " + e.getMessage());
        }
    }


    //JS METHODS & JS SCROLL

    public void scrollToElementByWebElementLocator(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element)).isEnabled();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -400)");
            LOGGER.info("Successfully scrolled to the WebElement, using locator: " + "<" + element.toString() + ">");
        } catch (Exception e) {
            LOGGER.error("Unable to scroll to the WebElement, using locator: " + "<" + element.toString() + ">");
            Assert.fail("Unable to scroll to the WebElement, Exception: " + e.getMessage());
        }
    }

    public void jsPageScroll(int numb1, int numb2) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("scroll(" + numb1 + "," + numb2 + ")");
            LOGGER.info("Successfully scrolled to the correct position! using locators: " + numb1 + ", " + numb2);
        } catch (Exception e) {
            LOGGER.error("Unable to scroll to element using locators: " + "<" + numb1 + "> " + " <" + numb2 + ">");
            Assert.fail("Unable to manually scroll to WebElement, Exception: " + e.getMessage());
        }
    }

    public void waitAndclickElementUsingJS(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            js.executeScript("arguments[0].click();", element);
            LOGGER.info("Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
        } catch (StaleElementReferenceException elementUpdated) {
            WebElement staleElement = element;
            Boolean elementPresent = wait.until(ExpectedConditions.elementToBeClickable(staleElement)).isEnabled();
            if (elementPresent == true) {
                js.executeScript("arguments[0].click();", elementPresent);
                LOGGER.info("(Stale Exception) Successfully JS clicked on the following WebElement: " + "<" + element.toString() + ">");
            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Unable to JS click on the following WebElement: " + "<" + element.toString() + ">");
            Assert.fail("Unable to JS click on the WebElement, Exception: " + e.getMessage());
        }
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);
    }


    //WAIT METHODS

    public boolean WaitUntilWebElementIsVisible(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOf(element));
            LOGGER.info("WebElement is visible using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT visible, using locator: " + "<" + element.toString() + ">");
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean WaitUntilWebElementIsVisibleUsingByLocator(By element) {
        try {
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            LOGGER.info("Element is visible using By locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT visible, using By locator: " + "<" + element.toString() + ">");
            Assert.fail("WebElement is NOT visible, Exception: " + e.getMessage());
            return false;
        }
    }

    public boolean isElementClickable(WebElement element) {
        try {
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
            LOGGER.info("WebElement is clickable using locator: " + "<" + element.toString() + ">");
            return true;
        } catch (Exception e) {
            LOGGER.error("WebElement is NOT clickable using locator: " + "<" + element.toString() + ">");
            return false;
        }
    }


    public boolean waitUntilPreLoadElementDissapears(By element) {
        return this.wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }


    public String getCurrentURL() {
        try {
            String url = driver.getCurrentUrl();
            LOGGER.info("Found(Got) the following URL: " + url);
            return url;
        } catch (Exception e) {
            LOGGER.error("Unable to locate (Get) the current URL, Exception: " + e.getMessage());
            return e.getMessage();
        }
    }

    public String waitForSpecificPage(String urlToWaitFor) {
        try {
            String url = driver.getCurrentUrl();
            this.wait.until(ExpectedConditions.urlMatches(urlToWaitFor));
            LOGGER.info("The current URL was: " + url + ", " + "navigated and waited for the following URL: " + urlToWaitFor);
            return urlToWaitFor;
        } catch (Exception e) {
            LOGGER.error("Exception! waiting for the URL: " + urlToWaitFor + ",  Exception: " + e.getMessage());
            return e.getMessage();
        }
    }


    //ALERT & POPUPS METHODS

    public void closePopups(By locator) throws InterruptedException {
        try {
            List<WebElement> elements = this.wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            for (WebElement element : elements) {
                if (element.isDisplayed()) {
                    element.click();
                    this.wait.until(ExpectedConditions.invisibilityOfAllElements(elements));
                    LOGGER.info("The popup has been closed Successfully!");
                }
            }
        } catch (Exception e) {
            LOGGER.error("Exception! - could not close the popup!, Exception: " + e.toString());
            throw (e);
        }
    }

    public boolean checkPopupIsVisible() {
        try {
            @SuppressWarnings("unused")
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            LOGGER.info("A popup has been found!");
            return true;
        } catch (Exception e) {
            LOGGER.error("Error came while waiting for the alert popup to appear. " + e.getMessage());
        }
        return false;
    }

    public boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void closeAlertPopupBox() {
        try {
            Alert alert = this.wait.until(ExpectedConditions.alertIsPresent());
            alert.accept();
        } catch (UnhandledAlertException f) {
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (Exception e) {
            LOGGER.error("Unable to close the popup");
            Assert.fail("Unable to close the popup, Exception: " + e.getMessage());
        }
    }

    /**
     * Function to find element and highlight
     *
     * @param driver Web driver object
     * @param elem   web element to identify
     * @param color  highlighting element
     */
    public void findElementAndHighlight(WebDriver driver, WebElement elem, String color) {
        try {
            explicitlyWaitForElement(driver, elem);
            if (driver instanceof JavascriptExecutor) {
                LOGGER.info("Highlighting: " + elem);
                ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid " + color + "'",
                        elem);

            }
        } catch (NoSuchElementException e) {
            LOGGER.error("Element highlight method could not find the locator: " + elem);
        } catch (Exception e) {
            LOGGER.error("Some error occurred while highlighting the locator: " + elem);
        }
    }

    /**Method to select element 'by' type
     * @param type : String : 'By' type
     * @param access_name : String : Locator value
     * @return By
     */
    public By getElementByType(String type, String access_name)
    {
        switch(type)
        {
            case "id" : return By.id(access_name);
            case "name" : return By.name(access_name);
            case "class" : return By.className(access_name);
            case "xpath" : return By.xpath(access_name);
            case "css" : return By.cssSelector(access_name);
            case "linkText" : return By.linkText(access_name);
            case "partialLinkText" : return By.partialLinkText(access_name);
            case "tagName" : return By.tagName(access_name);
            default : return null;
        }
    }

    /** Method to hover on element
     * @param elementLocatorType : String : Locator type (id, name, class, xpath, css)
     * @param elementLocatorName : String : Locator value
     */
    public void hoverOverElement(String elementLocatorType, String elementLocatorName)
    {
        Actions action = new Actions(driver);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(elementLocatorType, elementLocatorName)));
        action.moveToElement(element).perform();
    }

    /** Method to verify locator type
     * @param type : String : Locator type (id, name, class, xpath, css)
     */
    public void validateLocator(String type) throws Exception
    {
        if(!validateElementLocatorType(type))
            throw new Exception("Invalid locator type - "+type);
    }

    private boolean validateElementLocatorType(String type)
    {
        return Arrays.asList("id","class","css","name","xpath").contains(type);
    }

    /** Method to verify dropdown selector (text, value or index)
     * @param optionBy : String : Locator type (text, value, index)
     */
    public void validateOptionBy(String optionBy) throws Exception
    {
        if(!validOptionBy(optionBy))
            throw new Exception("Invalid option by - "+optionBy);
    }

    private boolean validOptionBy(String option_by)
    {
        return Arrays.asList("text","value","index").contains(option_by);
    }

    /** Method to select option from dropdown list
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param optionBy : String : Name of by type
     @param option : String : Option to select
     @param accessName : String : Locator value
     */
    public void selectOptionFromDropdown(String accessType, String optionBy, String option, String accessName)
    {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        Select selectList = new Select(dropdown);

        if(optionBy.equals("selectByIndex"))
            selectList.selectByIndex(Integer.parseInt(option)-1);
        else if (optionBy.equals("value"))
            selectList.selectByValue(option);
        else if (optionBy.equals("text"))
            selectList.selectByVisibleText(option);
    }

    /** Method to unselect option from dropdwon list
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void deselectOptionFromDropdown(String accessType, String optionBy, String option, String accessName)
    {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        Select selectList = new Select(dropdown);

        if(optionBy.equals("selectByIndex"))
            selectList.deselectByIndex(Integer.parseInt(option)-1);
        else if (optionBy.equals("value"))
            selectList.deselectByValue(option);
        else if (optionBy.equals("text"))
            selectList.deselectByVisibleText(option);
    }

    /** Method to unselect all option from dropdwon list
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void unselectAllOptionFromMultiselectDropdown(String accessType, String accessName)
    {
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        Select selectList = new Select(dropdown);
        selectList.deselectAll();
    }

    /** Method to check check-box
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void checkCheckbox(String accessType, String accessName)
    {
        WebElement checkbox= wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        if (!checkbox.isSelected())
            checkbox.click();
    }

    /** Method to uncheck check-box
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void uncheckCheckbox(String accessType, String accessName)
    {
        WebElement checkbox= wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        if (checkbox.isSelected())
            checkbox.click();
    }

    /** Method to toggle check-box status
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void toggleCheckbox(String accessType, String accessName)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName))).click();
    }

    /** Method to select radio button
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void selectRadioButton(String accessType, String accessName)
    {
        WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        if(!radioButton.isSelected())
            radioButton.click();
    }

    /** Method to select option from radio button group
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param by : String : Name of by type
     @param option : String : Option to select
     @param accessName : String : Locator value
     */
    public void selectOptionFromRadioButtonGroup(String accessType, String option, String by, String accessName)
    {
        List<WebElement> radioButtonGroup = driver.findElements(getElementByType(accessType, accessName));
        for(WebElement rb : radioButtonGroup)
        {
            if(by.equals("value"))
            {
                if(rb.getAttribute("value").equals(option) && !rb.isSelected())
                    rb.click();
            }
            else if(by.equals("text"))
            {
                if(rb.getText().equals(option) && !rb.isSelected())
                    rb.click();
            }
        }
    }


    // ASSERTION METHODS


    /** Method to verify page title
     * @param title : String : expected title
     */
    public void checkTitle(String title)
    {
        String pageTitle = getPageTitle();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(pageTitle).isNotNull();
        softly.assertThat(pageTitle).isEqualTo(title);
        softly.assertAll();
    }

    /** Method to verify partial page title
     * @param partialTitle : String : partial title string
     */
    public void checkPartialTitle(String partialTitle)
    {
        String pageTitle = getPageTitle();
        assertThat(pageTitle, containsString(partialTitle));
    }

    /** Method to get element text
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param accessName : String : Locator value
     * @return String
     */
    public String getElementText(String accessType, String accessName)
    {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        return element.getText();

    }

    /** Method to check element text
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param expectedValue : String : Expected element text
     * @param accessName : String : Locator value
     */
    public void checkElementText(String accessType, String accessName, String expectedValue)
    {
        String actualValue = getElementText(accessType, accessName);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(actualValue).isNotNull();
        softly.assertThat(actualValue).isEqualTo(expectedValue);
        softly.assertAll();
    }

    /** Method to check partial element text
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param expectedValue : String : Expected element text
     * @param accessName : String : Locator value
     */
    public void checkElementPartialText(String accessType, String accessName, String expectedValue)
    {
        String actualValue = getElementText(accessType, accessName);
        assertThat(actualValue, containsString(expectedValue));
    }

    //    /** Method to return element status - enabled?
//     * @param accessType : String : Locator type (id, name, class, xpath, css)
//     * @param accessName : String : Locator value
//     * @return Boolean
//     */
//    public boolean isElementEnabled(String accessType, String accessName)
//    {
//        element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//        return element.isEnabled();
//    }

    /** Element enabled checking
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void checkElementEnable(String accessType, String accessName)
    {
        By element = getElementByType(accessType, accessName);
        boolean eleEnabled= driver.findElement(element).isEnabled();
        assertThat("Element is not enabled", eleEnabled, is(true));

    }

//    /** method to get attribute value
//     @param accessType : String : Locator type (id, name, class, xpath, css)
//     @param accessName : String : Locator value
//     @param attributeName : String : attribute name
//     @return String
//     */
//    public String getElementAttribute(String accessType,String accessName,String attributeName)
//    {
//        element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//        return element.getAttribute(attributeName);
//    }
//
    /** method to check attribute value
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param attributeName : String : attribute name
     @param eAttributeValue : String : attribute value
     @param accessName : String : Locator value
     */
    public void checkElementAttribute(String accessType, String attributeName, String eAttributeValue, String accessName)
    {
        String aAttributeValue = getElementAttribute(accessType, accessName, attributeName);
        assertThat("Attribute value is not correct", aAttributeValue, is(equalTo(eAttributeValue)));
    }

    /** method to get attribute value
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     @param attributeName : String : attribute name
     @return String
     */
    public String getElementAttribute(String accessType,String accessName,String attributeName)
    {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        return element.getAttribute(attributeName);
    }

//    /** method to get element status - displayed?
//     @param accessType : String : Locator type (id, name, class, xpath, css)
//     @param accessName : String : Locator value
//     @return Boolean
//     */
//    public boolean isElementDisplayed(String accessType,String accessName)
//    {
//        element = wait.until(ExpectedConditions.presenceOfElementLocated(getelementbytype(accessType, accessName)));
//        return element.isDisplayed();
//    }

    /** method to check element presence
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     */
    public void checkElementIsVisible(String accessType,String accessName)
    {
        WebElement element = driver.findElement(getElementByType(accessType, accessName));
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Element: {} is found within {} seconds", element.getTagName(), TIME_OUT_IN_SECONDS);
            throw new NoSuchElementException("Element not found.");
        }
    }

    /** method to assert checkbox check/uncheck
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     @param shouldBeChecked : Boolean : test case [true or false]
     */
    public void isCheckboxSelected(String accessType, String accessName, boolean shouldBeChecked)
    {
        WebElement checkbox = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));

        if((!checkbox.isSelected()) && shouldBeChecked) {
            LOGGER.error("Checkbox with name: {} is not selected", checkbox.getTagName());
            throw new NoSuchElementException("Element not found.");
        }

        if((!checkbox.isSelected()) && shouldBeChecked) {
            LOGGER.error("Checkbox with name: {} is not selected", checkbox.getTagName());
            throw new NoSuchElementException("Element not found.");
        }
    }

    /** method to assert radio button selected/unselected
     @param accessType : String : Locator type (id, name, class, xpath, css)
     @param accessName : String : Locator value
     @param shouldBeSelected : Boolean : radio button selected [true or false]
     */
    public void isRadioButtonSelected(String accessType,String accessName,boolean shouldBeSelected)
    {
        WebElement radioButton = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));

        if((!radioButton.isSelected()) && shouldBeSelected) {
            LOGGER.error("Radiobutton with name: {} is not selected", radioButton.getTagName());
            throw new NoSuchElementException("Element not found.");
        }

        if(radioButton.isSelected() && !shouldBeSelected) {
            LOGGER.error("Radiobutton with name: {} is not selected", radioButton.getTagName());
            throw new NoSuchElementException("Element not found.");
        }
    }

    public void isOptionFromRadioButtonGroupSelected(String accessType,String by,String option,String accessName,boolean shouldBeSelected)
    {
        List<WebElement> radioButtonGroup = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getElementByType(accessType, accessName)));

        for (WebElement rb : radioButtonGroup) {
            if(by.equals("value"))
            {
                if(rb.getAttribute("value").equals(option))
                {
                    if((!rb.isSelected()) && shouldBeSelected) {
                        LOGGER.error("Radiobutton with name: {} is not selected", rb.getTagName());
                        throw new NoSuchElementException("Radio Button not selected");
                    }
                    else if(rb.isSelected() && !shouldBeSelected)
                        LOGGER.error("Radiobutton with name: {} is not selected", rb.getTagName());
                    throw new NoSuchElementException("Radio Button is selected");
                }
            }
            else if(rb.getText().equals(option))
            {
                if((!rb.isSelected()) && shouldBeSelected) {
                    LOGGER.error("Radiobutton with name: {} is not selected", rb.getTagName());
                    throw new NoSuchElementException("Radio Button not selected");
                }
                else if(rb.isSelected() && !shouldBeSelected)
                    LOGGER.error("Radiobutton with name: {} is not selected", rb.getTagName());
                throw new NoSuchElementException("Radio Button is selected");
            }
        }
    }

    /** method to get javascript pop-up alert text
     * @return String
     */
    public String getAlertText()
    {
        return driver.switchTo().alert().getText();
    }

    /**method to check javascript pop-up alert text
     * @param text : String : Text to verify in Alert
     */
    public void checkAlertText(String text) {
        if(!getAlertText().equals(text))
            try {
                throw new Exception("Text on alert pop up not matched");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /** Method to verify if the particular option is Selected from Dropdown
     * @param accessType : String : Locator type (id, name, class, xpath, css)
     * @param by : String : Select element from dropdown by text or value
     * @param option : String : Element to select from dropdown
     * @param accessName : String : Locator value
     * @param shouldBeSelected : Boolean : test case [true or false]
     */
    public void isOptionFromDropdownSelected(String accessType,String by,String option,String accessName,boolean shouldBeSelected)
    {
        Select selectList=null;
        WebElement dropdown = wait.until(ExpectedConditions.presenceOfElementLocated(getElementByType(accessType, accessName)));
        selectList = new Select(dropdown);

        String actualValue="";
        if(by.equals("text"))
            actualValue = selectList.getFirstSelectedOption().getText();
        else
            actualValue = selectList.getFirstSelectedOption().getAttribute("value");

        if((!actualValue.equals(option)) && shouldBeSelected) {
            LOGGER.error("Option with name: {} is not selected from dropsdown", actualValue);
            throw new NoSuchElementException("Radio Button not selected");
        }

        if((actualValue.equals(option)) && !shouldBeSelected) {
            LOGGER.error("Option with name: {} is selected from dropsdown", actualValue);
            throw new NoSuchElementException("\"Option Selected From Dropsdown");
        }
    }
}
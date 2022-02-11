package com.hsbc.qe.ui.common;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class DriverHelper extends DriverSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverHelper.class);
    private static final int TIME_OUT_IN_SECONDS = 60;

    WebDriverWait wait;

    public DriverHelper(){
       wait = new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
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

            WebDriverWait wait = (WebDriverWait) new WebDriverWait(driver, TIME_OUT_IN_SECONDS);
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
     * @param driver Webdriver object
     * @param element    web element to identify
     */
    public void click(WebDriver driver, WebElement element) {
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


    /**
     * Function to type value
     *
     * @param driver Webdriver object
     * @param ele    By object to identify
     * @param value  Input to be entered
     */

    public void type(WebDriver driver, WebElement ele, String value) {
        try {
            findElementAndHighlight(driver, ele, "red");
            ele.click();
            ele.clear();
            ele.sendKeys(value);
        } catch (NoSuchElementException e) {
            handleException(e);
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
}
package com.hsbc.qe.ui.common.driver_helpers;

import com.hsbc.qe.ui.common.BaseDriverSetup;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.time.Duration;

public class DriverUtils extends BaseDriverSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverHelper.class);
    private static final int TIME_OUT_IN_SECONDS = 60;

    WebDriverWait wait;

    public DriverUtils() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIME_OUT_IN_SECONDS));
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
            LOGGER.info("Element {} clicked successfully.", element.getTagName());
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
     * Function to handle exception
     *
     * @param e       exception
     * @param locator web element to identify
     */
    public void handleException(TimeoutException e, WebElement locator) {
        throw new RuntimeException("Timeout looking for " + locator.toString() + " after " + TIME_OUT_IN_SECONDS + " seconds at page " + driver.getCurrentUrl());
    }

    protected void handleException(NoSuchElementException e) {
        Assert.fail(e.getMessage() + " on page " + driver.getCurrentUrl());
    }

}

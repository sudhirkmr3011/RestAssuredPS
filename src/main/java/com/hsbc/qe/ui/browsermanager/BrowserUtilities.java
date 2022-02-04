package com.hsbc.qe.ui.browsermanager;

import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.Duration;


public class BrowserUtilities {

    private final static Logger LOGGER = LoggerFactory.getLogger(BrowserFactory.class);
    private static int timeoutInSeconds = 30;
    private static int pollingTimeInSeconds = 1;
    private WebDriver webDriver;
    private static WebDriverWait webDriverWait;

    public BrowserUtilities(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriverWait = new WebDriverWait(this.webDriver, timeoutInSeconds);
    }

    public void navigateTo(String url) {
        webDriver.get(url);
    }

    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public void clearAndEnterText(WebElement element, String value) {
        waitForElementToBeVisible(element);
        scrollToViewElement(webDriver, element);
        clearField(element);
        element.sendKeys(value);
    }


    public void waitForElementToBeVisible(WebElement element) {
        try {
            webDriverWait.until(d -> element.isDisplayed());
        } catch (Exception e) {
            LOGGER.error("Element: {} is not found within {} seconds", element.getTagName(), timeoutInSeconds);
            throw new NoSuchElementException("Element not found.");
        }
    }

    public void waitForElementToBeInVisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            LOGGER.error("Element: {} is found within {} seconds", element.getTagName(), timeoutInSeconds);
            throw new NoSuchElementException("Element not found.");
        }
    }
    public void waitForElementsToBeVisible(WebElement element) {
        try {
            webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("input"))).isDisplayed();

        } catch (Exception e) {
            LOGGER.error("Element: {} is not found within {} seconds", element.getTagName(), timeoutInSeconds);
            throw new NoSuchElementException("Element not found.");
        }
    }

    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            LOGGER.error("Error in clearing the text from the element: {}", element.getText());
        }
    }

    public void scrollToViewElement(WebDriver driver, WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    public void selectOptionByText(WebElement element, String value) {
        waitForElementToBeVisible(element);
        Select dropdown = new Select(element);
        dropdown.selectByVisibleText(value);
    }

    public void click(WebElement webElement) throws Exception {
        waitForElementToBeClickable(webElement);
        webElement.click();
    }


    public void waitForElementToBeClickable(WebElement webElement ) throws Exception {
        try {
            webDriverWait.until(ExpectedConditions.elementToBeClickable(webElement));
        } catch (Exception e) {
            LOGGER.error("Element {} is not clickable.", webElement.getTagName());
            throw new Exception("Element is not clickable");
        }
    }

    public boolean isElementInVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            return true;
        } catch (Exception e) {
            LOGGER.error("Element is visible for the element: {}.", element.getTagName());
            return false;
        }
    }

    public void waitForJQueryLoad() {
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;
            ExpectedCondition<Boolean> jQueryLoad = driver -> ((Long) ((JavascriptExecutor) this.webDriver)
                    .executeScript("return jQuery.active") == 0);

            boolean jqueryReady = (Boolean) javascriptExecutor.executeScript("return jQuery.active==0");

            if (!jqueryReady) {
                webDriverWait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitUntilJSReady() {
        try {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) webDriver;

            ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) this.webDriver)
                    .executeScript("return document.readyState").toString().equals("complete");

            boolean jsReady = javascriptExecutor.executeScript("return document.readyState").toString().equals("complete");

            if (!jsReady) {
                webDriverWait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }

    public void waitForElementToPresent(By by) {
        Wait<WebDriver> wait  = new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .pollingEvery(Duration.ofSeconds(pollingTimeInSeconds))
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Exception e) {
            throw new ElementNotVisibleException("Element is not visible.");
        }
    }

    public boolean isElementVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (Exception e) {
            LOGGER.error("Element is not visible for the element: {}.", element.getTagName());
            return false;
        }
    }

    public boolean isElementsVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeoutInSeconds);
        try {
            wait.until(ExpectedConditions.invisibilityOfAllElements(element));
            return true;
        } catch (Exception e) {
            LOGGER.error("Element is visible for the element: {}.", element.getTagName());
            return false;
        }
    }
}

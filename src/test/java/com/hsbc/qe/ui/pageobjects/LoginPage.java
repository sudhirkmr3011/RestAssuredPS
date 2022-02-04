package com.hsbc.qe.ui.pageobjects;

import com.hsbc.qe.ui.browsermanager.BrowserUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginPage {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginPage.class);
    public WebDriver webDriver;
    public BrowserUtilities browser;

    public LoginPage(WebDriver webDriver) throws Exception {
        this.webDriver = webDriver;
        browser = new BrowserUtilities(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "user-name")
    private WebElement userNameTextbox;

    @FindBy(id = "password")
    private WebElement passwordTextbox;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public void navigateToUrl(String url) {
        browser.navigateTo(url);
        LOGGER.info("Navigate to the url: {}", url);
    }

    public void enterUsername(String userName) {
        browser.clearAndEnterText(userNameTextbox, userName);
        LOGGER.info("Enter first name: ", userName);
    }

    public void enterPassword(String password) {
        browser.clearAndEnterText(passwordTextbox, password);
        LOGGER.info("Enter last name: {}", password);
    }

    public void clickLoginButton() throws Exception {
        browser.click(loginButton);
        browser.waitForJQueryLoad();
        LOGGER.info("Login to the app.");
    }
}

package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.common.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends DriverHelper {

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    public WebElement username;

    @FindBy(id = "password")
    public WebElement password;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    public void enterUserName(String email) {
        explicitlyWaitForElement(driver, username);
        type(driver, username, email);
    }

    public void enterPassword(String password) {
        explicitlyWaitForElement(driver, this.password);
        type(driver, this.password, password);
    }

    public void clickLoginButton() {
        click(driver, loginButton);
    }
}

package com.hsbc.qe.ui.pageobjects;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LoginPage extends AbstractPageObject {

    @FindBy(how = How.ID, using = "user-name")
    public static WebElement usernameInput;

//    @FindBy(id = "user-name")
//    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    public void enterUserName(String email) {
        this.usernameInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    public void clickLoginButton() {
        loginButton.click();
    }
}

package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.common.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends DriverHelper {
    LoginPage loginPage;
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        loginPage = new LoginPage(driver);
        PageFactory.initElements(driver, this);
    }

    public final String acceptCookie = "//button[@title='Akkoord']";
    @FindBy(xpath = acceptCookie)
    public WebElement acceptCookieBanner;


    public final String frame = "//iframe[contains(@id,'sp_message_iframe')]";
    @FindBy(xpath = frame)
    public WebElement cookieFrame;

    public final String loginButton = "tm-call2action";
    @FindBy(className = loginButton)
    public WebElement loginButtonElement;

    public void navigateToHomePage() {
        navigate(configuration.baseUrl());
    }

    public void navigateToLoginPage(){
        navigate(configuration.baseUrl());
    }
}
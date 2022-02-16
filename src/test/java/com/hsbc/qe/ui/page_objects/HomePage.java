package com.hsbc.qe.ui.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPageObject {

    public HomePage() {
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
}
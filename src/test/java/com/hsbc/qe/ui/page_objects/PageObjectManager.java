package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.page_objects.LoginPage;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
    private final WebDriver driver;

    private LoginPage loginPage;

    private HomePage homePage;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage() {
        return (loginPage == null) ? loginPage = new LoginPage(driver) : loginPage;
    }

    public HomePage getHomePage() {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }
}

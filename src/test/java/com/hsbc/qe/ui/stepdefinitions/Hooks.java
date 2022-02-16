package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.reporter.AllureManager;
import com.hsbc.qe.ui.webdriver.DriverManager;
import com.hsbc.qe.ui.webdriver.TargetWebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.hsbc.qe.ui.config.ConfigurationManager.getConfiguration;

public class Hooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void preCondition() {
//        AllureManager.setAllureEnvironmentInformation();
        WebDriver driver = new TargetWebDriverFactory().createInstance(getConfiguration().browser());
        DriverManager.addDriver(driver);
        DriverManager.getDriver().get(getConfiguration().url());
    }

    @After
    public void postCondition() {
//        failTest();
//        DriverManager.quitDriver();
        DriverManager.destroyDriver();
    }

    private void failTest() {
        AllureManager.takeScreenshotToAttachOnAllureReport();
    }
}

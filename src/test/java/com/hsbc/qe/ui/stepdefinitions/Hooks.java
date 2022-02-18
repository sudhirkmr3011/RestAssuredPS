package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.context.TestContext;
import com.hsbc.qe.ui.webdriver.TargetWebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.hsbc.qe.ui.config.ConfigurationManager.getConfiguration;

public class Hooks extends InitSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);
    WebDriver driver;
    TestContext testContext;

    public Hooks(TestContext testContext){
        super(testContext);
        this.testContext = testContext;
        driver = testContext.getDriverManager().getDriver();
    }

    @Before
    public void preCondition() {
//        AllureManager.setAllureEnvironmentInformation()
        driver = new TargetWebDriverFactory().createInstance(getConfiguration().browser());
        testContext.getDriverManager().addDriver(driver);
        testContext.getDriverManager().setDriver(driver);
        if(testContext.getDriverManager().getDriver() == null){
            System.out.println("Why this is null");
        }
        testContext.getDriverManager().getDriver().get(getConfiguration().url());
    }

    @After
    public void postCondition() {
//        failTest();
//        DriverManager.quitDriver();
        testContext.getDriverManager().destroyDriver();
    }

//    private void failTest() {
//        AllureManager.takeScreenshotToAttachOnAllureReport();
//    }
}

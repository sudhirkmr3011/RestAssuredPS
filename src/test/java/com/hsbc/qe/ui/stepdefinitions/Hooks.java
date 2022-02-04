package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.browsermanager.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class Hooks {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);
    public static WebDriver webDriver;

    @Before
    public void setup() {
        LOGGER.info("Start scenario....");
        BrowserFactory browserFactory = new BrowserFactory();
        webDriver = browserFactory.getDriver();
        deleteCookies();
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        LOGGER.info("Stop scenario....");
        if (scenario.isFailed()) {
            takeScreenShot();
        }
        quitDriver();
    }

    private void quitDriver() {
        if (null != webDriver) {
            webDriver.quit();
            LOGGER.info("Close the driver session.");
            webDriver = null;
            LOGGER.info("Reset the driver session.");
        }
    }

    private void deleteCookies() {
        if (!webDriver.manage().getCookies().isEmpty()) {
            webDriver.manage().deleteAllCookies();
            LOGGER.info("Deleted the cookies.");
        }
    }

    private void takeScreenShot() throws IOException {
        LOGGER.error("Taking screen shot.");
        File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy-z-HH:mm:ss", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        FileUtils.copyFile(scrFile, new File("screenshots/" + dateFormat.format(cal.getTime()) + ".png"));
    }
}

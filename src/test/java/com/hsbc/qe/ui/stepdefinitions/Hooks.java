package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.common.DriverSetup;
import com.hsbc.qe.ui.page_objects.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks extends DriverSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @Before
    public void hookUp(Scenario scenario) {
        logger.info("***Execution of \"" + scenario.getName() + "\" has started.***");
        initBrowsers();
    }

    @After
    public void teardown(Scenario scenario) {
        logger.info("***Execution of \"" + scenario.getName() + "\" is completed. Result " + scenario.getStatus() + "***");
        quitDriver();
    }

}

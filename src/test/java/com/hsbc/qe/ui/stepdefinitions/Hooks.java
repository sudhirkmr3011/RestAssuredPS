package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.common.BaseDriverSetup;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hooks extends BaseDriverSetup {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hooks.class);

    @Before()
    public void hookUp(Scenario scenario) {
        LOGGER.info("***Execution of \"" + scenario.getName() + "\" has started.***");
        initBrowsers();
    }

    @After
    public void teardown(Scenario scenario) {
        LOGGER.info("***Execution of \"" + scenario.getName() + "\" is completed. Result " + scenario.getStatus() + "***");
        quitDriver();
    }

}

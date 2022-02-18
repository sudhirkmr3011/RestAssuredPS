package com.hsbc.qe.ui.context;

import com.hsbc.qe.ui.webdriver.DriverManager;
import com.hsbc.qe.ui.webdriver.DriverManagerUtils;
import org.testng.annotations.Test;

public class TestContext {

    DriverManager driverManager;

    public TestContext() {
        driverManager = new DriverManager();
    }

    public DriverManager getDriverManager() {
        return driverManager;
    }

}

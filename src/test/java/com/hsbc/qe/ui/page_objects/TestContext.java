package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.common.DriverSetup;

public class TestContext {

    private final DriverSetup driverSetup;
    private final PageObjectManager pageObjectManager;

    public TestContext(){
        driverSetup = new DriverSetup();
        pageObjectManager = new PageObjectManager(driverSetup.getDriver());
    }

    public DriverSetup getDriverSetup() {
        return driverSetup;
    }
    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }
}

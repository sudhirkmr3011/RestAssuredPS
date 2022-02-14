package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import com.hsbc.qe.ui.common.BaseDriverSetup;
import com.hsbc.qe.ui.common.driver_helpers.DriverUtils;
import org.bouncycastle.pqc.crypto.newhope.NHOtherInfoGenerator;

public class TestContext {

    private final BaseDriverSetup driverSetup;
    private final PageObjectManager pageObjectManager;
    private final DriverHelper driverHelper;
    private final DriverUtils driverUtils;

    public TestContext(){
        driverSetup = new BaseDriverSetup();
        driverHelper = new DriverHelper();
        driverUtils = new DriverUtils();
        pageObjectManager = new PageObjectManager(driverSetup.getDriver());
    }

    public BaseDriverSetup getDriverSetup() {
        return driverSetup;
    }

    public PageObjectManager getPageObjectManager() {
        return pageObjectManager;
    }

    public DriverHelper getDriverHelper(){
        return driverHelper;
    }

    public DriverUtils getDriverUtils() { return driverUtils; }
}

package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import com.hsbc.qe.ui.common.driver_helpers.DriverUtils;
import io.cucumber.java8.Te;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;

public class PageObjectLoader {

    DriverUtils driverUtils;
    DriverHelper driverHelper;
    TestContext testContext;

    public PageObjectLoader(TestContext testContext) {
        this.testContext = testContext;
        this.driverHelper = testContext.getDriverHelper();
        this.driverUtils = testContext.getDriverUtils();
    }
    private static final String UI_PAGE_OBJECTS_PACKAGE = "com.hsbc.qe.ui.page_objects";

    public WebElement loadAndFindPageElement(String locatorName, String pageClassName) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
       WebElement element;
        Class<?> loadClass = Class.forName(UI_PAGE_OBJECTS_PACKAGE + pageClassName);
        PageFactory.initElements(driverHelper.getDriver(), loadClass);
        Field field = loadClass.getDeclaredField(locatorName);
         element = (WebElement) field.get(null);
         return element;
    }
}

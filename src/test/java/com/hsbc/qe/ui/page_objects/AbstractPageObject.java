package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject extends DriverHelper {

    WebDriver driver;

    protected AbstractPageObject(WebDriver driver)  {
        this.driver = driver;
        initElements(new AjaxElementLocatorFactory(this.driver, 30), this);
    }
}

package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.webdriver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import static com.hsbc.qe.ui.config.ConfigurationManager.getConfiguration;
import static org.openqa.selenium.support.PageFactory.initElements;

public class AbstractPageObject {

    protected AbstractPageObject() {
        initElements(new AjaxElementLocatorFactory(DriverManager.getDriver(), getConfiguration().timeout()), this);
    }
}

package com.hsbc.qe.ui.page_objects;

import com.hsbc.qe.ui.webdriver.DriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Field;
import java.sql.Driver;

public class PageObjectLoader {

    WebDriver driver;

    public PageObjectLoader() {
        driver = DriverManager.getDriver();
    }
    private static final String UI_PAGE_OBJECTS_PACKAGE = "com.hsbc.qe.ui.page_objects";

    public WebElement loadAndFindPageElement(String locatorName, String pageClassName) {
       WebElement element = null;
        Class<?> loadClass = null;
        try {
            loadClass = Class.forName(UI_PAGE_OBJECTS_PACKAGE + pageClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PageFactory.initElements(driver, loadClass);
        Field field = null;
        try {
            field = loadClass.getDeclaredField(locatorName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        try {
            element = (WebElement) field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return element;
    }
}

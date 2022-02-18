package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.config.Configuration;
import com.hsbc.qe.ui.context.TestContext;
import io.cucumber.java8.En;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.lang.reflect.Field;

public class DebugSteps extends Base implements En {

    public static Configuration configuration;

    TestContext testContext;
    WebDriver driver;

    public DebugSteps(TestContext context){
        super(context);
        this.testContext = context;
        driver = context.getDriverManager().getDriver();
        Given("user enter {string} on {string} on page {string}", (String arg1, String arg2, String arg3) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qe.ui.pageobjects." + arg3);
            if(driver == null){
                System.out.println("Ohh dear. Why this is null");
            }
            PageFactory.initElements(driver, loadClass);
            Field field = loadClass.getDeclaredField(arg2);
            WebElement ele = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele)).sendKeys(arg1);
        });
    }
}

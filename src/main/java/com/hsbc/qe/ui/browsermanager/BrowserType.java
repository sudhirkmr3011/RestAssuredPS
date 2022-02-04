package com.hsbc.qe.ui.browsermanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserType {

    public static WebDriver getDriverType(String browserDriver, DesiredCapabilities desiredCapabilities, boolean isHeadlessEnabled) {
        WebDriver webDriver = null;
        switch (browserDriver){
            case "CHROME":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.merge(desiredCapabilities);
                if (isHeadlessEnabled) {
                    chromeOptions.setHeadless(true);
                }
                chromeOptions.addArguments("--no-default-browser-check");
                webDriver = new ChromeDriver(chromeOptions);
                break;
            case "FIREFOX":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.merge(desiredCapabilities);
                if(isHeadlessEnabled){
                    firefoxOptions.setHeadless(true);
                }
                webDriver = new FirefoxDriver(firefoxOptions);
                break;
        }
        return webDriver;
    }
}

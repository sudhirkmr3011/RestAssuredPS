package com.hsbc.qe.ui.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BaseDriverSetup {
    public final Logger LOGGER = Logger.getLogger(BaseDriverSetup.class);
    public static Configuration configuration = ConfigurationManager.getConfiguration();
    public static  boolean isHeadless;
    public static String browserName;
    public static String gridURL;
    public ChromeOptions chromeOptions;
    public static WebDriver driver;

    public static final String DEFAULT_REMOTE_BROWSER = "chrome";

    public BaseDriverSetup() {
        isHeadless = configuration.isHeadLess();
        browserName = configuration.browserName();
        gridURL = configuration.gridURL();
    }

    public WebDriver getDriver() {
        if (driver == null) driver = initBrowsers();
        return driver;
    }

    /**
     * Function to initialize drivers based on the browser configuration mentioned in config.properties
     */
    public WebDriver initBrowsers() {

        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = initChrome(isHeadless);
                break;
            case "firefox":
                driver = initFirefox(isHeadless);
                break;
            case "safari":
                driver = initSafari();
                break;
            case "edge":
                driver = initEdge();
                break;
            case "ie":
                driver = initIE();
                break;
            case "opera":
                driver = initOpera();
                break;
            case "grid":
                driver = initRemoteDriver();
                break;
        }
        return driver;
    }

    protected WebDriver initChrome(Boolean isHeadless) {
        LOGGER.info("Setting Chrome web driver...");
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.addArguments("--ignore-certificate-errors");

        if (isHeadless) {
            LOGGER.info("Setting up arguments for Chrome driver");
            chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
                    "--ignore-certificate-errors");
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            LOGGER.info("Arguments set up is completed.");
        }
        driver = new ChromeDriver(chromeOptions);
        return driver;
    }

    protected WebDriver initFirefox(Boolean isHeadless) {
        WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();

        if (isHeadless) {
            try {
                firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    protected WebDriver initOpera(){
        WebDriverManager.operadriver().setup();
        OperaOptions operaOptions = new OperaOptions();
        driver =  new OperaDriver(operaOptions);
        return driver;
    }

    protected WebDriver initEdge(){
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        driver =  new EdgeDriver(edgeOptions);
        return driver;
    }

    protected WebDriver initIE(){
        WebDriverManager.iedriver().setup();
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        driver =  new InternetExplorerDriver(ieOptions);
        return driver;
    }
    protected WebDriver initSafari(){
        WebDriverManager.safaridriver().setup();
        SafariOptions ieOptions = new SafariOptions();
        driver =  new SafariDriver(ieOptions);
        return driver;
    }

    protected WebDriver initRemoteDriver(){
        return remoteWebDriver(gridURL, getCapabilityForRemoteBrowser(getRemoteBrowserName().toLowerCase()));
    }

    private RemoteWebDriver remoteWebDriver(String remoteWebDriverURL, DesiredCapabilities desiredCapabilities) {
        try {
            driver = new RemoteWebDriver(new URL(remoteWebDriverURL), desiredCapabilities);
        } catch (MalformedURLException e) {
            LOGGER.error("Failed to launch remote driver!", e);
        }
        return (RemoteWebDriver) driver;
    }

    private String getRemoteBrowserName() {
        String getRemoteBrowserName = System.getProperty("remote.browser");
        if (getRemoteBrowserName == null) {
            getRemoteBrowserName = System.getenv("remote.browser");
            if (getRemoteBrowserName == null) {
                getRemoteBrowserName = DEFAULT_REMOTE_BROWSER;
            }
        }
        return getRemoteBrowserName;
    }

    public DesiredCapabilities getCapabilityForRemoteBrowser(String browserName) {
        DesiredCapabilities desiredCapabilities;
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName("chrome");
                desiredCapabilities.setPlatform(Platform.MAC);
                return desiredCapabilities;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities.setBrowserName("firefox");
                desiredCapabilities.setPlatform(Platform.MAC);
                return desiredCapabilities;
            default:
                throw new IllegalStateException("Selenium grid does not support the given browser: " + browserName);
        }
    }


    private void deleteCookies() {
        if (!driver.manage().getCookies().isEmpty()) {
            driver.manage().deleteAllCookies();
            LOGGER.info("Deleted the cookies.");
        }
    }

    public void quitDriver() {
        if (null != driver) {
            deleteCookies();
            driver.quit();
            LOGGER.info("Close the driver session.");
            driver = null;
            LOGGER.info("Reset the driver session.");
        }
    }

    private void takeScreenShot() throws IOException {
        LOGGER.info("Taking screen shot.");
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("MMMM-dd-yyyy-z-HH:mm:ss", Locale.ENGLISH);
        Calendar cal = Calendar.getInstance();
        FileUtils.copyFile(scrFile, new File("screenshots/" + dateFormat.format(cal.getTime()) + ".png"));
    }
}
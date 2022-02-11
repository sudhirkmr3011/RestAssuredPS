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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;


public class DriverSetup {
    public final Logger logger = Logger.getLogger(DriverSetup.class);
    public static Configuration configuration = ConfigurationManager.getConfiguration();
    public static  boolean isHeadless;
    public static String browserName;
    public static String gridURL;
    public ChromeOptions chromeOptions;
    public static WebDriver driver;

    public static final String DEFAULT_REMOTE_BROWSER = "chrome";


    public DriverSetup() {
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

            case "grid":
                driver = initRemoteDriver();
                break;
        }
        return driver;
    }

    protected WebDriver initChrome(Boolean isHeadless) {
        logger.info("Setting Chrome web driver...");
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        chromeOptions.addArguments("--ignore-certificate-errors");

        if (isHeadless) {
            logger.info("Setting up arguments for Chrome driver");
            chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200",
                    "--ignore-certificate-errors");
            chromeOptions.setExperimentalOption("useAutomationExtension", false);
            chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
            logger.info("Arguments set up is completed.");
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

    protected WebDriver initRemoteDriver(){
        return remoteWebDriver(gridURL, getCapabilityForRemoteBrowser(getRemoteBrowserName().toLowerCase()));
    }

    private RemoteWebDriver remoteWebDriver(String remoteWebDriverURL, DesiredCapabilities desiredCapabilities) {
        try {
            driver = new RemoteWebDriver(new URL(remoteWebDriverURL), desiredCapabilities);
        } catch (MalformedURLException e) {
            logger.error("Failed to launch remote driver!", e);
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
                desiredCapabilities = DesiredCapabilities.chrome();
                desiredCapabilities.setBrowserName("chrome");
                desiredCapabilities.setPlatform(Platform.MAC);
                return DesiredCapabilities.chrome();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                desiredCapabilities = DesiredCapabilities.firefox();
                desiredCapabilities.setBrowserName("firefox");
                desiredCapabilities.setPlatform(Platform.MAC);
                return DesiredCapabilities.firefox();
            default:
                throw new IllegalStateException("This browser isn't supported yet. Sorry...");
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

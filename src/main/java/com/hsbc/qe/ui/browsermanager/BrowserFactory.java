package com.hsbc.qe.ui.browsermanager;

import com.hsbc.qe.ui.environment.ConfigurationManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.openqa.selenium.Proxy.ProxyType.MANUAL;
import static org.openqa.selenium.remote.CapabilityType.PROXY;

public class BrowserFactory {

    private final static Logger LOGGER = LoggerFactory.getLogger(BrowserFactory.class);
    private WebDriver webDriver;
    private String selectedBrowser;
    private boolean headlessEnabled;
    private String operatingSystem;
    private String systemArchitecture;
    private boolean proxyEnabled;
    private String proxyDetails;

    public WebDriver getDriver() {
        if (null == webDriver) {
            loadResources();
            instantiateWebDriver();
        }
        return webDriver;
    }

    private void instantiateWebDriver() {
        LOGGER.info("Selected Browser: " + selectedBrowser);
        LOGGER.info("Headless enabled: " + headlessEnabled);
        LOGGER.info("Local Operating System: {}", operatingSystem);
        LOGGER.info("Local Architecture: {}", systemArchitecture);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (proxyEnabled) {
            Proxy proxy = new Proxy();
            proxy.setProxyType(MANUAL);
            proxy.setHttpProxy(proxyDetails);
            proxy.setSslProxy(proxyDetails);
            desiredCapabilities.setCapability(PROXY, proxy);
        }
        setDriverSystemProperty();

        webDriver = BrowserType.getDriverType(selectedBrowser, desiredCapabilities, headlessEnabled);
    }

    private void setDriverSystemProperty() {

        if (operatingSystem.contains("WINDOWS")) {
            switch (selectedBrowser) {
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();

            }
        } else if (operatingSystem.contains("LINUX")) {
            switch (selectedBrowser) {
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();
            }

        } else if (operatingSystem.contains("MAC")) {
            switch (selectedBrowser) {
                case "CHROME":
                    WebDriverManager.chromedriver().setup();
                case "FIREFOX":
                    WebDriverManager.firefoxdriver().setup();
            }
        }
    }

    private void loadResources() {
        selectedBrowser = ConfigurationManager.getInstance().getBrowserName();
        headlessEnabled = ConfigurationManager.getInstance().getHeadlessMode();
        proxyEnabled = ConfigurationManager.getInstance().getProxyEnabled();
        proxyDetails = ConfigurationManager.getInstance().getProxyDetails();
        operatingSystem = ConfigurationManager.getInstance().getCurrentOS();
        systemArchitecture = ConfigurationManager.getInstance().getSystemArchitecture();
    }
}

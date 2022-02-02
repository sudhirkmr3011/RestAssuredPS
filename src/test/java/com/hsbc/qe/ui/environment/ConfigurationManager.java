package com.hsbc.qe.ui.environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.text.MessageFormat;
import java.util.Properties;

public class ConfigurationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationManager.class);
    private static ConfigurationManager instance;
    private static final Object lock = new Object();
    private static String browserName;
    private static String headlessMode;
    private static String proxyEnabled;
    private static String proxyHost;
    private static String proxyPort;
    private static final String DEFAULT_BROWSER = "chrome";
    private static final String DEFAULT_HEADLESS_MODE = "false";

    public static ConfigurationManager getInstance () {
        if (instance == null) {
            synchronized (lock) {
                instance = new ConfigurationManager();
                String propertyFilePath = "src/test/resources/ui/config.properties";
                try {
                    instance.loadData(propertyFilePath);
                } catch (FileNotFoundException e) {
                    LOGGER.error("Unable to find the file: {}", propertyFilePath);
                }
            }
        }
        return instance;
    }


    private void loadData(String filePath) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(filePath);
        Properties prop = new Properties();

        try {
            prop.load(fileInputStream);
        } catch (IOException e) {
            LOGGER.error("Configuration properties file cannot be found");
        }

        browserName = getProperty(prop,"browser.name");
        headlessMode = getProperty(prop,"headless.mode");
        proxyEnabled = getProperty(prop, "proxy.enabled");
        proxyHost = getProperty(prop, "proxy.host");
        proxyPort = getProperty(prop, "proxy.port");
    }

    public String getBrowserName() {
        if(browserName == null || browserName.isEmpty()){
            browserName = DEFAULT_BROWSER;
            LOGGER.info("Browser name is not specified, setting the default to {}", DEFAULT_BROWSER);
        }
        return browserName.toUpperCase();
    }

    public boolean getHeadlessMode() {
        if(headlessMode == null || headlessMode.isEmpty()){
            headlessMode = DEFAULT_HEADLESS_MODE;
            LOGGER.info("Headless mode is not specified, setting the default to {}", DEFAULT_HEADLESS_MODE);
        }
        return Boolean.valueOf(headlessMode);
    }

    public boolean getProxyEnabled() {
        return Boolean.valueOf(proxyEnabled);
    }

    public String getProxyDetails() {
        String proxyHostname = getProxyHost();
        Integer proxyPort = Integer.getInteger(getProxyPort());
        return String.format("%s:%d", proxyHostname, proxyPort);
    }

    public String getCurrentOS() {
        return System.getProperty("os.name").toUpperCase();
    }

    public String getSystemArchitecture() {
        return System.getProperty("os.arch").toLowerCase();
    }

    private String getProxyHost() {
        return proxyHost;
    }

    private String getProxyPort() {
        return proxyPort;
    }

    private String getProperty(Properties properties, String key) {
        final String property = properties.getProperty(key);
        if(property == null) {
            throw new InvalidParameterException(MessageFormat.format("Missing value for key {0}!", key));
        }
        return property;
    }
}

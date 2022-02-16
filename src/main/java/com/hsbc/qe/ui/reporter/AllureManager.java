package com.hsbc.qe.ui.reporter;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import com.hsbc.qe.ui.webdriver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;

import static com.hsbc.qe.ui.config.ConfigurationManager.getConfiguration;
import static org.openqa.selenium.OutputType.BYTES;

public class AllureManager {


    private AllureManager() {
    }
    public static void setAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Test URL", getConfiguration().url()).
                        put("Target execution", getConfiguration().target()).
                        put("Global timeout", String.valueOf(getConfiguration().timeout())).
                        put("Headless mode", String.valueOf(getConfiguration().headless())).
                        put("Faker locale", getConfiguration().faker()).
                        put("Local browser", getConfiguration().browser()).
                        put("Grid URL", getConfiguration().gridUrl()).
                        put("Grid port", getConfiguration().gridPort()).
                        build());
    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(BYTES);
    }

    @Attachment(value = "Browser information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return DriverManager.getInfo();
    }
}

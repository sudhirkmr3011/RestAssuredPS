package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.common.driver_helpers.DriverHelper;
import com.hsbc.qe.ui.common.driver_helpers.DriverUtils;
import com.hsbc.qe.ui.page_objects.PageObjectLoader;
import com.hsbc.qe.ui.page_objects.TestContext;
//import io.cucumber.java.is.En;
import io.cucumber.java.en.Given;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.Headers;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.*;
import java.util.List;

import io.cucumber.java8.En;
import org.testng.Assert;

import static com.hsbc.qe.ui.common.BaseDriverSetup.configuration;


//import io.cucumber.java.en.*;

public class CommonReflectionSteps extends PageObjectLoader implements En {

    TestContext testContext;
    DriverHelper driverHelper;
    DriverUtils driverUtils;
    public static String status = "passed";

    private static final String UI_PAGE_OBJECTS_PACKAGE = "com.hsbc.qe.ui.page_objects";


    public CommonReflectionSteps(TestContext testContext) {
        super(testContext);
        this.testContext = testContext;
        this.driverHelper = testContext.getDriverHelper();
        this.driverUtils = testContext.getDriverUtils();

        Then("User click on {string} on page {string}", (String locatorName, String pageClassName) -> {
            WebElement ele = loadAndFindPageElement(locatorName, pageClassName);
            driverUtils.click(ele);
        });

        Then("User check whether the {string} is click or not on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), Duration.ofSeconds(20));
            System.out.println(ele.isSelected());
        });

        Then("User context click on {string} on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            Actions a=new Actions(driverHelper.getDriver());
            WebElement ele = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            a.contextClick(ele).build().perform();
            System.out.println("User is able to click on the element  ");
            driverHelper.getDriver().switchTo().alert().accept();
        });
        Then("User select {string} to {string} on page {string}", (String arg1, String arg2, String arg3) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg3);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Field field1= loadClass.getDeclaredField(arg2);
            WebElement ele1 = (WebElement) field1.get(null);
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.visibilityOf(ele));
            action.dragAndDrop(ele,ele1).perform();

        });
        Then("User select {string} on page {string} by index of {int}", (String arg1, String arg2, Integer int1) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            Select select=new Select(ele);
            select.selectByIndex(int1);
            System.out.println("User is able to click on the element  ");
        });
        Then("User Hovers move on {string} on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            action.moveToElement(ele).perform();
        });

        Then("User View on {string} on the {string} on page {string}", (String arg1, String arg2, String arg3) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg3);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Field field1 = loadClass.getDeclaredField(arg2);
            WebElement ele1 = (WebElement) field.get(null);
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            //wait.until(ExpectedConditions.elementToBeClickable(ele));
            //BP.driver.switchTo().frame(ele);
            wait.until(ExpectedConditions.elementToBeClickable(ele1));
            driverHelper.getDriver().switchTo().frame(ele1);
            driverHelper.getDriver().switchTo().defaultContent();
        });
        Then("User {string} Keyword on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
            Robot robot=new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_CONTROL);
        });
        Then("User click on on {string} on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
            Set<String> windows=driverHelper.getDriver().getWindowHandles();
            for (String handle:windows){
                System.out.println("window handle "+handle);
            }
        });

        Then("User verify the Tittle", () -> {
            System.out.println(driverHelper.getDriver().getTitle());
        });

        Then("User Click on Close", () -> {
            driverHelper.getDriver().switchTo().alert().accept();
        });

        Then("User Click on NO", () -> {
            driverHelper.getDriver().switchTo().alert().dismiss();
        });

        Then("User Send a key is {string} and enter ok", (String key) -> {
            driverHelper.getDriver().switchTo().alert().sendKeys(key);
            driverHelper.getDriver().switchTo().alert().accept();
        });
        Then("User move on {string} to Right on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            action.dragAndDropBy(ele,5,0).perform();
        });
        Then("User check the link contains {string}", (String tittle) -> {
            String url=driverHelper.getDriver().getCurrentUrl();
            System.out.println(url);
            Assert.assertTrue(url.contains(tittle));
        });
        Then("User try to {string} on {string} of the page {string}", (String arg1, String arg2, String arg3) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg3);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            Field field1 = loadClass.getDeclaredField(arg2);
            WebElement ele1 = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            JavascriptExecutor js= (JavascriptExecutor) driverHelper.getDriver();
            js.executeScript("window.scrollBy(0,200)","");
            wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
            ((JavascriptExecutor)driverHelper.getDriver()).executeScript("window.scrollTo(0,document.body.scrollHeight)");
            wait.until(ExpectedConditions.elementToBeClickable(ele1)).click();
            boolean isFooter=ele1.isDisplayed();
            int count=0;
            while (isFooter) {
                JavascriptExecutor jse = (JavascriptExecutor) driverHelper.getDriver();
                jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
                count++;
                if(count==100){
                    System.out.println(count);
                    break;
                }
            }

        });
        Then("User enter {string} in {string} and enter {string} in {string} on page {string}", (String value1, String key1, String value2, String key2, String arg1) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg1);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            ChromeDriver driver = (ChromeDriver) driverHelper.getDriver();
            key1=value1;
            key2=value2;
            DevTools devTools=driver.getDevTools();
            devTools.createSession();
            devTools.send(Network.enable(Optional.of(100000),Optional.of(100000),Optional.of(100000)));
            String auth=key1 + ":" + key2;
            String encodeToString= Base64.getEncoder().encodeToString(auth.getBytes());
            Map<String,Object> headers=new HashMap<>();
            headers.put("Authorization","Basic "+encodeToString);
            devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));

        });
        Then("User try to click on {string} and {string} on page {string}", (String arg1, String path, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            path="C:\\Users\\harmoort\\Pictures\\leaf.jfif";
            Actions action=new Actions(driverHelper.getDriver());
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            action.moveToElement(ele).click().perform();
            ele.sendKeys(path);
        });
        Then("get the {string} on page {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            WebElement ele = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            System.out.println(ele.getText());
        });
        Then("And user enter {string} on {string} on page {string}", (String arg1, String arg2, String arg3) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg3);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg2);
            WebElement ele = (WebElement) field.get(null);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            wait.until(ExpectedConditions.elementToBeClickable(ele)).sendKeys(arg1);
        });
        Then("Then get the values of {string} and {string} on page {string}", (String arg1, String arg2, String arg3) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg3);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Field field = loadClass.getDeclaredField(arg1);
            Field field1 = loadClass.getDeclaredField(arg2);
            WebDriverWait wait = new WebDriverWait(driverHelper.getDriver(), 20);
            java.util.List<WebElement> columns= (java.util.List<WebElement>) field.get(null);
            System.out.println("Number Of columns :"+columns.size());
            for (WebElement column:columns){
                System.out.println("Columns are :"+column.getText());
            }
            java.util.List<WebElement> Rows= (java.util.List<WebElement>)field1.get(null);
            System.out.println("Number Of Rows :"+Rows.size());
            for (WebElement row:Rows){
                System.out.println("Rows are :"+row.getText());
            }
        });

        Then("user {string} any broken image on {string}", (String arg1, String arg2) -> {
            Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
            PageFactory.initElements(driverHelper.getDriver(), loadClass);
            Integer iBrokenImageCount = 0;
            Field field = loadClass.getDeclaredField(arg1);
            try
            {
                iBrokenImageCount = 0;  //BP.driver.findElements(By.tagName("img"));
                java.util.List<WebElement> image_list = (List<WebElement>) field.get(null);
                /* Print the total number of images on the page */
                System.out.println("The page under test has " + image_list.size() + " images");
                for (WebElement img : image_list)
                {
                    if (img != null)
                    {
                        HttpClient client = HttpClientBuilder.create().build();
                        HttpGet request = new HttpGet(img.getAttribute("src"));
                        HttpResponse response = client.execute(request);
                        /* For valid images, the HttpStatus will be 200 */
                        if (response.getStatusLine().getStatusCode() != 200)
                        {
                            System.out.println(img.getAttribute("outerHTML") + " is broken.");
                            iBrokenImageCount++;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                status = "failed";
                System.out.println(e.getMessage());
            }
            status = "passed";
            System.out.println("The page " + " has " + iBrokenImageCount + " broken images");
        });

    }
}

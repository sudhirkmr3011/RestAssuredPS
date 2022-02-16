package com.hsbc.qe.ui.stepdefinitions;

import com.hsbc.qe.ui.page_objects.PageObjectLoader;
import com.hsbc.qe.ui.webdriver.DriverManager;
import com.hsbc.qe.ui.webdriver.DriverManagerUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
import org.testng.Assert;

public class CommonSteps extends PageObjectLoader {
    public static String status = "passed";
    WebDriver driver;
    DriverManagerUtils driverManagerUtils;

    public CommonSteps() {
        driver = DriverManager.getDriver();
        driverManagerUtils = new DriverManagerUtils();
    }

    @Given("I navigate to {string}")
    public void navigate (String string) {
        // Write code here that turns the phrase above into concrete actions
    };

    @Then("User check whether the {string} is click or not on page {string}")
    public void veryClick(String locatorName, String pageClassName) {
        WebElement ele = loadAndFindPageElement(locatorName, pageClassName);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
        System.out.println(ele.isSelected());
    };

    @Then("User context click on {string} on page {string}")
    public void contextClick(String arg1, String arg2) {
        Actions a = new Actions(DriverManager.getDriver());
        WebElement ele = loadAndFindPageElement(arg1, arg2);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        a.contextClick(ele).build().perform();
        System.out.println("User is able to click on the element  ");
        DriverManager.getDriver().switchTo().alert().accept();
    };

    @Then("User select {string} on page {string} by index of {int}")
    public void selectByIndex(String locatorName, String pageClassName, Integer indexNumber) {
        WebElement element = loadAndFindPageElement(locatorName, pageClassName);
        Select select=new Select(element);
        select.selectByIndex(indexNumber);
        System.out.println("User is able to click on the element  ");
    };

    @Then("User View on {string} on the {string} on page {string}")
    public void userView(String arg1, String arg2, String arg3) {
        WebElement ele = loadAndFindPageElement(arg1, arg3);
        WebElement ele1 = loadAndFindPageElement(arg2, arg3);
        Actions action=new Actions(DriverManager.getDriver());
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        //wait.until(ExpectedConditions.elementToBeClickable(ele));
        //BP.driver.switchTo().frame(ele);
        wait.until(ExpectedConditions.elementToBeClickable(ele1));
        DriverManager.getDriver().switchTo().frame(ele1);
        DriverManager.getDriver().switchTo().defaultContent();
    };

    @Then("User {string} Keyword on page {string}")
    public void stringKeyboardOnPahe(String arg1, String arg2) throws AWTException {
        WebElement ele = loadAndFindPageElement(arg1, arg2);
        Actions action=new Actions(DriverManager.getDriver());
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_CONTROL);
    };

    @Then("User click on on {string} on page {string}")
    public void clickOnPage(String arg1, String arg2) {
        WebElement ele = loadAndFindPageElement(arg1, arg2);
        Actions action=new Actions(DriverManager.getDriver());
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
        Set<String> windows=DriverManager.getDriver().getWindowHandles();
        for (String handle:windows){
            System.out.println("window handle "+handle);
        }
    };

    @Then("User verify the Tittle")
    public void userVerifyTitle() {
        System.out.println(DriverManager.getDriver().getTitle());
    };

    @Then("User Send a key is {string} and enter ok")
    public void clickEnter(String key) {
            DriverManager.getDriver().switchTo().alert().sendKeys(key);
            DriverManager.getDriver().switchTo().alert().accept();
        };

    @Then("User move on {string} to Right on page {string}")
    public void moveToRight(String arg1, String arg2) {
        WebElement ele = loadAndFindPageElement(arg1, arg2);
        Actions action=new Actions(DriverManager.getDriver());
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        action.dragAndDropBy(ele,5,0).perform();
    };

    @Then("User try to {string} on {string} of the page {string}")
    public void userTryToStringOn(String arg1, String arg2, String arg3) {
        WebElement ele = loadAndFindPageElement(arg1, arg3);
        WebElement ele1 = loadAndFindPageElement(arg2, arg3);

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        JavascriptExecutor js= (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("window.scrollBy(0,200)","");
        wait.until(ExpectedConditions.elementToBeClickable(ele)).click();
        ((JavascriptExecutor)DriverManager.getDriver()).executeScript("window.scrollTo(0,document.body.scrollHeight)");
        wait.until(ExpectedConditions.elementToBeClickable(ele1)).click();
        boolean isFooter=ele1.isDisplayed();
        int count=0;
        while (isFooter) {
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriver();
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            count++;
            if(count==100){
                System.out.println(count);
                break;
            }
        }
    };

    @Then("User Click on Close")
    public void acceptTheAlert() {
        DriverManager.getDriver().switchTo().alert().accept();
    };

    @Then("User Click on NO")
    public void dismissTheAlert() {
        DriverManager.getDriver().switchTo().alert().dismiss();
    };

    @Then("User check the link contains {string}")
    public void clickLinkContainsString(String tittle) {
        String url=DriverManager.getDriver().getCurrentUrl();
        System.out.println(url);
        Assert.assertTrue(url.contains(tittle));
    };



    @Then("User try to click on {string} and {string} on page {string}")
    public void clickOnString(String locatorName, String path, String pageClassName) {
        WebElement ele = loadAndFindPageElement(locatorName, pageClassName);
        path="C:\\Users\\harmoort\\Pictures\\leaf.jfif";
        Actions action=new Actions(DriverManager.getDriver());
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        action.moveToElement(ele).click().perform();
        ele.sendKeys(path);
    };


    @Then("And user enter {string} on {string} on page {string}")
    public void enterData(String arg1, String arg2, String arg3) {
        WebElement ele = loadAndFindPageElement(arg2, arg3);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
        wait.until(ExpectedConditions.elementToBeClickable(ele)).sendKeys(arg1);
    };

    @Then("Then get the values of {string} and {string} on page {string}")
    public void getTheValuesOfStringAndString(String arg1, String arg2, String arg3) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg3);
        PageFactory.initElements(DriverManager.getDriver(), loadClass);
        Field field = loadClass.getDeclaredField(arg1);
        Field field1 = loadClass.getDeclaredField(arg2);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), 20);
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
    };

    @Then("get the {string} on page {string}")
    public void getElementOnPage(String locatorName, String pageClassName) {
        WebElement element = loadAndFindPageElement(locatorName, pageClassName);
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(20));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        System.out.println(element.getText());
    };


    @Then("User click on {string} on page {string}")
    public void clickElementOnPage(String locatorName, String pageClassName) {
        WebElement ele = loadAndFindPageElement(locatorName, pageClassName);
        driverManagerUtils.clickElement(driver, ele);
    };


    @Then("user {string} any broken image on {string}")
    public void brokenImage(String arg1, String arg2) throws ClassNotFoundException, NoSuchFieldException {
        Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg2);
        PageFactory.initElements(DriverManager.getDriver(), loadClass);
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
    };

//    @Then("User enter {string} in {string} and enter {string} in {string} on page {string}")
//    public void devTools(String value1, String key1, String value2, String key2, String arg1) {
//        Class<?> loadClass = Class.forName("com.hsbc.qeautomation.pageobjects." + arg1);
//        PageFactory.initElements(DriverManager.getDriver(), loadClass);
//        ChromeDriver we = (ChromeDriver) DriverManager.getDriver();
//        key1=value1;
//        key2=value2;
//        DevTools devTools=DriverManager.getDriver().getDevTools();
//        devTools.createSession();
//        devTools.send(Network.enable(Optional.of(100000),Optional.of(100000),Optional.of(100000)));
//        String auth=key1 + ":" + key2;
//        String encodeToString= Base64.getEncoder().encodeToString(auth.getBytes());
//        Map<String,Object> headers=new HashMap<>();
//        headers.put("Authorization","Basic "+encodeToString);
//        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
//
//    };
}

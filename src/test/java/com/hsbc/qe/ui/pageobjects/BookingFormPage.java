package com.hsbc.qe.ui.pageobjects;

import com.hsbc.qe.ui.browsermanager.BrowserUtilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class BookingFormPage {

    private final static Logger LOGGER = LoggerFactory.getLogger(BookingFormPage.class);
    public WebDriver webDriver;
    public BrowserUtilities browser;

    public BookingFormPage(WebDriver webDriver) throws Exception {
        this.webDriver = webDriver;
        browser = new BrowserUtilities(webDriver);
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(id = "firstname")
    private WebElement firstNameInputField;

    @FindBy(id = "lastname")
    private WebElement lastNameInputField;

    @FindBy(id = "totalprice")
    private WebElement totalPriceTextBox;

    @FindBy(id = "depositpaid")
    private WebElement depositPaidOption;

    @FindBy(id = "checkin")
    private WebElement checkinDateInput;

    @FindBy(id = "checkout")
    private WebElement checkoutDateInput;

    @FindBy(css = "input[value=' Save ']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@id]//div[1]/p[1]")
    private List<WebElement> firstNamesInBookings;

    public void navigateToUrl(String url) {
        browser.navigateTo(url);
        LOGGER.info("Navigate to the url: {}", url);
    }

    public void enterFirstName(String firstName) {
        browser.clearAndEnterText(firstNameInputField, firstName);
        LOGGER.info("Enter first name: ", firstName);
    }

    public void enterLastName(String lastName) {
        browser.clearAndEnterText(lastNameInputField, lastName);
        LOGGER.info("Enter last name: {}",lastName);
    }

    public void enterDeposit(int price) {
        browser.clearAndEnterText(totalPriceTextBox, String.valueOf(price));
        LOGGER.info("Enter booking amount: {}", price);
    }

    public void selectDepositPaidOption(String optionValue) {
        browser.selectOptionByText(depositPaidOption, optionValue);
        LOGGER.info("Select the deposit option: {}", optionValue);

    }

    public void selectCheckinDate(String checkinDate) {
        browser.clearAndEnterText(checkinDateInput, checkinDate);
        LOGGER.info("Enter the check-in date: {}", checkinDate);
    }

    public void selectCheckoutDate(String checkoutDate) {
        browser.clearAndEnterText(checkoutDateInput, checkoutDate);
        LOGGER.info("Enter the check-out date: {}", checkoutDate);
    }

    public void clickSaveButton() throws Exception {
        browser.click(saveButton);
        browser.waitForJQueryLoad();
        browser.waitUntilJSReady();
        LOGGER.info("Save the booking record.");
    }

    public String clickDeleteButton(String bookingFirstName) throws Exception {
        String bookingRecordId = getBookingRecordId(bookingFirstName);
        String deleteButtonSelector = String.format("//*[@id='%s']//input", bookingRecordId);
        WebElement deleteButton = webDriver.findElement(By.xpath(deleteButtonSelector));
        browser.waitForElementToBeVisible(deleteButton);
        browser.click(deleteButton);
        LOGGER.info("Deleted the booking record for the user: {}", bookingFirstName);
        return bookingRecordId;
    }

    public List<String> getHotelBookings(String searchName) throws Exception {
        List<String> hotelBookingsList = new ArrayList<>();
        String bookingRecordId = getBookingRecordId(searchName);
        String recordLocatorValue = String.format("//*[@id='%s']/div/p", bookingRecordId);
        List<WebElement> recordLocator = webDriver.findElements(By.xpath(recordLocatorValue));
        for(WebElement record : recordLocator){
            browser.waitForElementToBeVisible(record);
            hotelBookingsList.add(record.getText());
        }
        return hotelBookingsList;
    }

    private String getBookingRecordId(String firstName) throws Exception {
        String recLocator = String.format("//p[contains(text(),'%s')]", firstName);
        browser.waitForElementToPresent(By.xpath(recLocator));
        List<WebElement> bookingRecordsList = firstNamesInBookings;
        String bookingRecordId = null;
        for (WebElement userNameInRecord : bookingRecordsList) {
            browser.waitForElementsToBeVisible(userNameInRecord);
            String name = userNameInRecord.getText();
            if (firstName.equals(name)) {
                String loc = String.format("//p[contains(text(),'%s')]/ancestor::div[@class='row']", firstName);
                WebElement element = webDriver.findElement(By.xpath(loc));
                browser.waitForElementToBeVisible(element);
                bookingRecordId = element.getAttribute("id");
                break;
            }
        }
        if(bookingRecordId == null){
            throw new Exception("Booking record Id is null");
        }
        return bookingRecordId;
    }

    public WebElement getBookingRecord (String bookingRecordId){
        String recordSelector = String.format("[id='%s']", bookingRecordId);
        WebElement record = webDriver.findElement(By.cssSelector(recordSelector));
        browser.waitForElementToBeInVisible(record);
        return record;
    }
}
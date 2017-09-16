package com.parkingcalculator.parkingcalculator;

import com.parkingcalculator.driver.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ParkingCalculatorPage extends Driver {

    public static final By LOT_COMBO_BOX = By.id("Lot");
    public static final By ENTRY_TIME = By.id("EntryTime");
    public static final By LEAVING_TIME = By.id("ExitTime");
    public static final By ENTRY_DATE = By.id("EntryDate");
    public static final By LEAVING_DATE = By.id("ExitDate");
    public static final By ENTRY_RADIO_BUTTON_AM = By.xpath(".//*[@name='EntryTimeAMPM' and @value='AM']");
    public static final By ENTRY_RADIO_BUTTON_PM = By.xpath(".//*[@name='EntryTimeAMPM' and @value='PM']");
    public static final By LEAVING_RADIO_BUTTON_AM = By.xpath(".//*[@name='ExitTimeAMPM' and @value='AM']");
    public static final By LEAVING_RADIO_BUTTON_PM = By.xpath(".//*[@name='ExitTimeAMPM' and @value='PM']");
    public static final By CALCULATE_BUTTON = By.name("Submit");
    public static final By COST_VALUE = By.cssSelector("span.SubHead b");
    public static final By DAYS_HOURS_MINUTES = By.cssSelector("span.BodyCopy b");
    public static final By MESSAGE = By.cssSelector("span.SubHead b");

    public List<String> assertsError = new ArrayList<String>();

    public String logError;

    public void waitForElement (By elementId) throws Exception {
        WebDriverWait wait = new WebDriverWait (driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(elementId));
    }

    public void checkScreenElements() throws Exception {
        logError = "\nCombobox não está na tela\n";
        tryCatchFindElement(LOT_COMBO_BOX, logError, assertsError);

        logError = "\nO campo Entry Time não está na tela\n";
        tryCatchFindElement(ENTRY_TIME, logError, assertsError);

        logError = "\nO campo Leaving Time não está na tela\n";
        tryCatchFindElement(LEAVING_TIME, logError, assertsError);

        logError = "\nO campo Entry Date Time não está na tela\n";
        tryCatchFindElement(ENTRY_DATE, logError, assertsError);

        logError = "\nO campo Leaving Date Time não está na tela\n";
        tryCatchFindElement(LEAVING_DATE, logError, assertsError);

        logError = "\nO campo Entry Time AM Button não está na tela\n";
        tryCatchFindElement(ENTRY_RADIO_BUTTON_AM, logError, assertsError);

        logError = "\nO campo Entry Time PM Button não está na tela\n";
        tryCatchFindElement(ENTRY_RADIO_BUTTON_PM, logError, assertsError);

        logError = "\nO campo Leaving Time AM Button não está na tela\n";
        tryCatchFindElement(LEAVING_RADIO_BUTTON_AM, logError, assertsError);

        logError = "\nO campo Leaving Time PM Button não está na tela\n";
        tryCatchFindElement(LEAVING_RADIO_BUTTON_PM, logError, assertsError);

        logError = "\nO campo Calculate Button não está na tela\n";
        tryCatchFindElement(CALCULATE_BUTTON, logError, assertsError);

        verificationListAsserts();

    }

    public void tryCatchFindElement(By by, String logError, List<String> assertsError) {
        try {
            driver.findElement(by);
        } catch (Exception e) {
            assertsError.add(logError);
        }
    }

    public void verificationListAsserts() {
        if (assertsError.size() > 0) {
            Assert.fail(String.valueOf(assertsError));
        }
    }

    public void setEntryTime(String time, String ampm) throws Exception{
        setElement(ENTRY_TIME, time);
        if (ampm.equals("PM")) {
            selectEntryTimeRadioPm();
        } else {
            selectEntryTimeRadioAm();
        }
    }

    public void setLeavingTime(String time, String ampm) throws Exception{
        setElement(LEAVING_TIME, time);
        if (ampm.equals("PM")) {
            selectLeavingTimeRadioPm();
        } else {
            selectLeavingTimeRadioAm();
        }
    }

    public void setEntryDate(String date) throws Exception{
        setElement(ENTRY_DATE, date);
    }

    public void setLeavingDate(String date) throws Exception{
        setElement(LEAVING_DATE, date);
    }

    public void selectEntryTimeRadioAm() throws Exception {
        WebElement element = driver.findElement(ENTRY_RADIO_BUTTON_AM);
        element.click();
    }

    public void selectEntryTimeRadioPm() throws Exception {
        WebElement element = driver.findElement(ENTRY_RADIO_BUTTON_PM);
        element.click();
    }

    public String getEntryTime() throws Exception {
        WebElement element = driver.findElement(ENTRY_TIME);
        return element.getAttribute("value");
    }

    public String getLeavingTime() throws Exception {
        WebElement element = driver.findElement(LEAVING_TIME);
        return element.getAttribute("value");
    }

    public String getCost() throws Exception {
        WebElement element = driver.findElement(COST_VALUE);
        return element.getText().trim();
    }

    public String getDaysHoursMinutes() throws Exception {
        WebElement element = driver.findElement(DAYS_HOURS_MINUTES);
        return element.getText().trim();
    }

    public String getMessage() throws Exception {
        WebElement element = driver.findElement(MESSAGE);
        return element.getText().trim();
    }

    public void selectLeavingTimeRadioAm() throws Exception {
        WebElement element = driver.findElement(LEAVING_RADIO_BUTTON_AM);
        element.click();
    }

    public void selectLeavingTimeRadioPm() throws Exception {
        WebElement element = driver.findElement(LEAVING_RADIO_BUTTON_PM);
        element.click();
    }

    public void calculate() throws Exception {
        clickElement(CALCULATE_BUTTON);
    }

    public void clickElement (By elementId) throws Exception {
        WebElement element = driver.findElement(elementId);
        element.click();
    }

    public void setElement(By elementId, String text) throws Exception {
        WebElement element = driver.findElement(elementId);
        element.clear();
        element.sendKeys(text);
    }

    public void setLot(String value) throws Exception {
        WebElement combo = driver.findElement(LOT_COMBO_BOX);
        Select lot = new Select(combo);
        lot.selectByVisibleText(value);
    }

    public String currentDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        String dateFormatted = dateFormat.format(date);
        return dateFormatted;
    }

    public String futureDate() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date((new Date()).getTime() + (10 * 86400000));
        String dateFormatted = dateFormat.format(date);
        return dateFormatted;
    }

    public void calculateParking(String lot, String entryTime, String entryAmpm, String exitTime, String exitAmpm, String entryDate, String exitDate) throws Exception {
        setLot(lot);
        setEntryTime(entryTime, entryAmpm);
        setLeavingTime(exitTime, exitAmpm);
        setEntryDate(entryDate);
        setLeavingDate(exitDate);
        calculate();
    }


}
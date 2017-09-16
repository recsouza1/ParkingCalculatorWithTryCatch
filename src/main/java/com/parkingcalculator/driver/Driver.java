package com.parkingcalculator.driver;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    public static WebDriver driver;

    public static final String URL = "http://adam.goucher.ca/parkcalc/";

    @Before
    public void initiateDriver() {
        System.setProperty("webdriver.chrome.driver", "binaryDriver/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

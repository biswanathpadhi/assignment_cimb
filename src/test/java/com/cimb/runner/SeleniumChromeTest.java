package com.cimb.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumChromeTest {

    private static Logger logger = LogManager.getLogger();

    static WebDriver driver;

    public static void main(String[] args) {

        logTest();

        seleniumTest();

    }

    private static void seleniumTest() {
//        driver = loadDriverUsingWebDriverManager();
//        driver = loadDriverUsingChromeDriverFile();


        String url = "https://www.w3schools.com/";

        // Launch Website
        driver.get(url);

        //Maximize the browser
        driver.manage().window().maximize();

        // Click on the Search button
        driver.findElement(By.cssSelector("a[href*='/html/default.asp'][class=\"w3-button w3-dark-grey\"]")).click();

        try {
            Thread.sleep(2000);
            driver.navigate().to("https://www.google.co.in");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.close();
        }

    }

    private static void logTest() {

        logger.info("info here");
        logger.warn("warn here");
        logger.debug("debug here");
        logger.error("error here");
    }

    private static WebDriver loadDriverUsingChromeDriverFile() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();

        return driver;
    }

    private static WebDriver loadDriverUsingWebDriverManager() {

        // System Property for Chrome Driver
        WebDriverManager.chromedriver().setup();

        // Instantiate a ChromeDriver class.
        driver = new ChromeDriver();

        return driver;
    }
}

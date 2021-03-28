package com.cimb.runner;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumChromeTest {

    private static Logger logger = LogManager.getLogger();

    static WebDriver driver;

    public static void main(String[] args) {

        logTest();

//        seleniumTest();

        driver = loadDriverUsingChromeDriverFile();
    }

    private static void seleniumTest() {
//        driver = loadDriverUsingWebDriverManager();
        driver = loadDriverUsingChromeDriverFile();


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

//    @Test(enabled=false)
    public void testScroll() throws InterruptedException{

        //initializer driver
        driver = loadDriverUsingChromeDriverFile();

        //Launch flipkart
        driver.get("http://www.flipkart.com");

        //Dismiss the login modal/dialog
        driver.findElement(By.xpath("//button[text()='✕']")).click();

        //Write the search term - Buddha in search box
        WebElement searchBox = driver.findElement(By.cssSelector("input[name='q']"));
        searchBox.sendKeys("Buddha");

        //Click on searchButton
        WebElement searchButton = driver.findElement(By.cssSelector("button[type='submit']"));
        searchButton.click();

        //Inserting an optional wait of 3 seconds just to notice scroll down event
        Thread.sleep(3000);

        //Scroll down the webpage by 2500 pixels
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("scrollBy(0, 2500)");

        //Waiting till page:2 text is visible
        WebElement pageNumberdisplayer = (new WebDriverWait(driver, 10)).until
                (ExpectedConditions.presenceOfElementLocated(By.cssSelector("//span[text()='Page 1 of 647']")));

        //Verifying that page got scrolled  and "page-2" text is visible now
        //and more products become visible
        Assert.assertEquals(pageNumberdisplayer.getText(), "Page 1 of 647");
    }
}

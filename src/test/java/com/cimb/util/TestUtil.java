package com.cimb.util;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

/**
 * @author biswanath.padhi
 */

public class TestUtil {

//    public static final String TESTDATA_SHEET_PATH = testDataDir + "TestData.xlsx";
    private long IMPLICIT_WAIT;
    private long PAGELOAD_TIMEOUT;
    public JavascriptExecutor js;
    private Logger logger;
    private FluentWait<WebDriver> wait;
    private WebDriver driver;


    public TestUtil(WebDriver driver) {
        logger = LogManager.getLogger();
        this.driver = driver;
        Properties properties = new ConfigReader().init_prop();
        IMPLICIT_WAIT = Long.parseLong(properties.getProperty("IMPLICIT_WAIT"));
        PAGELOAD_TIMEOUT = Long.parseLong(properties.getProperty("PAGELOAD_TIMEOUT"));
    }

    /***
     * Check if the JQuery is the page is completely loaded
     *
     */
    public static void isjQueryLoaded(WebDriver driver) {

        System.out.println("Waiting for ready state complete");

        (new WebDriverWait(driver, 30)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                System.out.println("Ready State: " + readyState);
                return (Boolean) js.executeScript("return !!window.jQuery && window.jQuery.active == 0");
            }
        });
    }


    public void selectDropdownValueByText(WebElement selectWebElement, String selectValue) {

        Select select = new Select(selectWebElement);

        select.selectByVisibleText(selectValue);

    }

    public void takeScreenshotAtEndOfTest() throws IOException {

        File scrFile = ((TakesScreenshot) this.driver).getScreenshotAs(OutputType.FILE);

        String userDir = System.getProperty("user.dir");

        FileUtils.copyFile(scrFile, new File(userDir + "/screenshots/" + "FAILED_" + getDateTime() + System.currentTimeMillis() + ".png"));

    }

    public String getDateTime() {

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy H-mm-ss");

        Date date = new Date();

        return dateFormat.format(date);

    }

    public void clickOnElement(WebDriver driver, WebElement element) {

        int attempts = 0;
        while (attempts < 2) {
            try {
                logger.info("Trying to click on element " + element);
                element.click();
                break;
            } catch (StaleElementReferenceException e) {
                logger.error("**** Stale Element Exception ****" + " attempt = " + attempts + element + driver);
            } catch (NoSuchElementException e) {
                logger.error("**** Unable to click element ****" + element);
            }
            attempts++;
        }
    }

    public void clickOnElement(WebElement element) {

        int attempts = 0;
        boolean shouldBreak = false;
        while (!shouldBreak && attempts < 2) {
            try {
                logger.info("Trying to click on element " + element);
                if (element.isEnabled() && element.isDisplayed()) {
                    moveToElementAndClick(driver, element);
                    shouldBreak = true;
                    break;
                }
            } catch (StaleElementReferenceException e) {
                logger.error("**** Stale Element Exception ****" + " attempt = " + attempts + element + driver);
            } catch (NoSuchElementException e) {
                logger.error("**** Unable to click element ****" + element);
            }
            attempts++;
        }
    }

    public void enterTextinElement(WebElement element, String textToEnter) {
        try {
            logger.info("Trying to enter text in element " + element);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({" +
                    "  behavior: 'smooth' , " +
                    "  block: 'center' , " +
                    "  inline: 'center' });", element);

            Actions actions = new Actions(driver).moveToElement(element);

            element.clear();
            element.click();
            element.sendKeys(Keys.CONTROL, "a");
            element.sendKeys(Keys.BACK_SPACE);
            element.sendKeys(textToEnter);
        } catch (StaleElementReferenceException e) {
            logger.error("**** Stale Element Exception ****" + element);
        } catch (NoSuchElementException e) {
            logger.error("**** Unable to enter text in element **** " + element);
        }
    }

    public void moveToElementAndClick(WebDriver driver, WebElement element) {

        try {
            org.openqa.selenium.interactions.Actions actions = new Actions(driver);

            actions.moveToElement(element).build().perform();

            element.click();
        } catch (Exception e) {
            logger.error("******* Error Message: " + e.getMessage());
        }
    }


    public WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {

        final long startTime = System.currentTimeMillis();
        int tries = 0;
        boolean found = false;

        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
                .ignoring(StaleElementReferenceException.class);

        while ((System.currentTimeMillis() - startTime) < 91000) {
            logger.info("Searching for element " + element.toString() + ". Try number " + (tries++));
            try {
                element = wait.until(ExpectedConditions.visibilityOf(element));
                found = true;
                break;
            } catch (StaleElementReferenceException e) {
                logger.error("Stale element: \n" + e.getMessage() + "\n");
            }
        }

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        if (found) {
            logger.info("Found element " + element.toString() + " after waiting for " + totalTime + " milliseconds.");
        } else {
            logger.error("Failed to find element " + element.toString() + " after " + totalTime + " milliseconds.");
        }
        return element;
    }

    public WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
        int attempts = 0;

        while (attempts < 3) {
            try {
                wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(5))
                        .ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.visibilityOf(element));
//                waitForLoad(driver);
//                new WebDriverWait(driver, 20).until(
//                        webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));


            } catch (StaleElementReferenceException e) {
                logger.error("**** Stale Element Exception ****" + " attempt = " + attempts + element + driver);
            } catch (NoSuchElementException e) {
                logger.error("**** Unable to click element ****" + element);
            }

            attempts++;
        }
        return element;
    }

    public void waitForLoad(WebDriver driver) {
        try{

            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            WebDriverWait wait = new WebDriverWait(driver, 60);
            wait.until(pageLoadCondition);
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.debug(e.getMessage());
        }
    }

    // Alert implementation
    public String getAlertMessage() {
        return driver.switchTo().alert().getText();
    }

    public void accept() {

        try {

            driver.switchTo().alert().accept();

        } catch (Exception e) {

            logger.debug("Trying to click Ok in alert: ");

            logger.error("Exception occured while clicking on OK in alert ");
        }
    }

    public void dismiss() {

        try {

            driver.switchTo().alert().dismiss();

        } catch (Exception e) {

            logger.debug("Trying to click Ok Cancel in alert: ");

            logger.error("Exception occured while clicking on CANCEL in alert ");
        }
    }

    public void sendKeysToAlert(String message) {

        try {

            driver.switchTo().alert().sendKeys(message);

        } catch (Exception e) {

            logger.debug("Trying to send message " + message + " to alert: ");

            logger.error("Exception occured while entering text in alert ");
        }
    }

    public WebDriver switchToWindowById(int index){
        ArrayList<String> tabList = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        return driver.switchTo().window(tabList.get((1)));
    }
    /***
     * Switch to a frame by providing either frame name or frame ID
     * @param frameNameOrId
     */
    public void switchToFrameByNameOrId(String frameNameOrId) {

        short retryAttempts = 0;
        while (retryAttempts < 2) {
            try {

                driver.switchTo().frame(frameNameOrId);
                logger.info("Successfully Switched to the frame: " + frameNameOrId);
                break;

            } catch (Exception e) {

                logger.debug("Trying to switchToFrameByNameOrId: " + frameNameOrId);

                logger.error("Exception occured while switching to frame");
            }

            retryAttempts++;
        }
    }

    /***
     * Switch to a the default frame
     *
     */
    public void switchToDefaultFrame() {
        driver.switchTo().defaultContent();
    }

    /**
     * Get the text of webelement
     *
     * @param driver
     * @param element
     * @return
     */
    public String getText(WebDriver driver, WebElement element) {

        String message = null;

        try {

            message = element.getText();

        } catch (Exception e) {

            logger.debug("Trying to get text from element: " + element);

            logger.error("Exception occured while getting text from element");
        }

        return message;
    }


    public void visitMe(String url){
        this.driver.navigate().to(url);
        waitForLoad(this.driver);
    }

    public void waitUntilElementDisappeared(WebDriver driver, WebElement element){
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfAllElements(element));
    }
}

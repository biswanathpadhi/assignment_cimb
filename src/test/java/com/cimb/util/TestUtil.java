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
import java.util.List;
import java.util.Properties;

/**
 * @author biswanath.padhi
 */

public class TestUtil {

    public JavascriptExecutor js;
    //    public static final String TESTDATA_SHEET_PATH = testDataDir + "TestData.xlsx";
    private long IMPLICIT_WAIT;
    private long PAGELOAD_TIMEOUT;
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
    public void isjQueryLoaded(WebDriver driver) {

        logger.info("Waiting for ready state complete");

        (new WebDriverWait(driver,PAGELOAD_TIMEOUT )).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                JavascriptExecutor js = (JavascriptExecutor) d;
                String readyState = js.executeScript("return document.readyState").toString();
                logger.info("Ready State: " + readyState);
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

        int attempts = 1;
        boolean shouldBreak = false;
        while (!shouldBreak && attempts < 4) {
            try {
                logger.info("Trying to click on element " + element);
                element = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(IMPLICIT_WAIT)).pollingEvery(Duration.ofSeconds(IMPLICIT_WAIT/5))
                        .ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class)
                        .until(ExpectedConditions.visibilityOf(element));

                moveToElementAndClick(driver, element);
                shouldBreak = true;
                break;
            } catch (StaleElementReferenceException e) {
                logger.error("**** Stale Element Exception ****" + " attempt = " + attempts + element + driver);
            } catch (NoSuchElementException e) {
                logger.error("**** Unable to click element ****" + element);
            }
            attempts++;
        }
    }

    public void waitAndDismissAppearedAlertsModals(WebElement element) {

        int attempts = 1;
        boolean shouldBreak = false;
        boolean elementDisplayed = false;
        while (!shouldBreak && attempts < 4) {
            try {
                logger.info("Trying to see if element appeared " + element);
                wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(2))
                        .ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class);
                if (element.isDisplayed()) {
                    elementDisplayed = true;
                    shouldBreak = true;
                    break;
                }
            } catch (StaleElementReferenceException e) {
                logger.error("**** Stale Element Exception ****" + " attempt = " + attempts + element + driver);
            } catch (NoSuchElementException e) {
                logger.error("**** Unable to click element ****" + element);
            }
            attempts++;
            break;
        }

        if (elementDisplayed) element.click();
    }

    public void enterTextInElement(WebElement element, String textToEnter) {
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

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoViewIfNeeded();", element);

            element.click();
        } catch (Exception e) {
            logger.error("******* Error Message: " + e.getMessage());
        }
    }


    public WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {

        final long startTime = System.currentTimeMillis();
        int tries = 0;
        boolean found = false;

        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(IMPLICIT_WAIT)).pollingEvery(Duration.ofSeconds(5))
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
        int attempts = 1;

        while (attempts < 4) {
            try {
                wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(IMPLICIT_WAIT)).pollingEvery(Duration.ofSeconds(5))
                        .ignoring(StaleElementReferenceException.class)
                        .ignoring(NoSuchElementException.class);
                wait.until(ExpectedConditions.visibilityOf(element));
                new Actions(driver).moveToElement(element);
            } catch (StaleElementReferenceException e) {
                logger.error("**** Stale Element Exception ****" + " attempt = " + attempts + element + driver);
            } catch (NoSuchElementException e) {
                logger.error("**** Unable to click element ****" + element);
            }

            attempts++;
        }
        return element;
    }

    public List<WebElement> waitForElementsToBeVisible(WebDriver driver, List<WebElement> similarDeals) {
        similarDeals.forEach(deal -> waitForElementToBeClickable(driver, deal));
        return similarDeals;
    }

    public void waitForLoad(WebDriver driver) {
        ExpectedCondition<Boolean> pageLoadCondition = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                    }
                };
        WebDriverWait wait = new WebDriverWait(driver, PAGELOAD_TIMEOUT);
        wait.until(pageLoadCondition);
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

    public WebDriver switchToWindowById(int index) {
        ArrayList<String> tabList = new ArrayList<>(driver.getWindowHandles());
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


    public void visitMe(String url) {
        this.driver.navigate().to(url);
        waitForLoad(this.driver);
    }

    public void waitUntilElementDisappeared(WebDriver driver, WebElement element) {
        new WebDriverWait(driver, 20).until(ExpectedConditions.invisibilityOfAllElements(element));
    }

    public void clickOnElementByText(String text) {
        By by = By.xpath("//*[contains(text(),'" + text + "')]");
        clickOnElement(driver.findElement(by));
    }

    public void clickOnElementTypeByText(String elementType, String text) {
        By by = By.xpath("//" + elementType + "[contains(text(),'" + text + "')]");
        clickOnElement(driver.findElement(by));
    }

    public void moveToElementByText(String text) {
        logger.info("start moveToElementByText()");
        WebElement element = findElementByText(text);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoViewIfNeeded();", element);
        logger.info("end moveToElementByText");
    }

    /**
     * Finds an element by text of any type
     * @param text
     * @return
     */
    public WebElement findElementByText(String text){
        By by = By.xpath("//*[contains(text(),'" + text + "')]");
        WebElement element = driver.findElement(by);
        return element;
    }

    /**
     * Finds a list of elements using text provided
     * @param text
     * @return
     */
    public List<WebElement> findElementsByText(String text){
        By by = By.xpath("//*[contains(text(),'" + text + "')]");
        List<WebElement> elementList = driver.findElements(by);
        return elementList;
    }


    /**
     * Finds an element using element type and text provided
     * @param text
     * @return returns the web element
     */
    public WebElement findElementByElementTypeUsingText(String elementType, String text){
        By by = By.xpath("//"+elementType+"*[contains(text(),'" + text + "')]");
        WebElement element = driver.findElement(by);
        return element;
    }

    /**
     * Finds a list of elements using element type and text provided
     * @param text
     * @return  web element containint text
     */
    public List<WebElement> findElementsByElementTypeUsingText(String elementType, String text){
        By by = By.xpath("//"+elementType+"*[contains(text(),'" + text + "')]");
        return driver.findElements(by);
    }

    public WebElement getElementByText(String dealTextFirst) {
         return findElementByText(dealTextFirst);
    }
}
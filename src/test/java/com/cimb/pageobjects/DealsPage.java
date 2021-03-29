package com.cimb.pageobjects;

import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * @author biswanath.padhi
 */
public class DealsPage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;

    // Page Objects
    @FindBy(css = ".modal-close-button")
    private WebElement overlayCountryClose;

    @FindBy(css = "footer.alp-cimbd-footer")
    private WebElement footer;

    @FindBy(css = "p.detail-text-first")
    private WebElement detailText;

    // Constructor to initialize page objects
    public DealsPage(WebDriver driver) {
        this.logger = LogManager.getLogger();
        this.driver = driver;
        this.util = new TestUtil(driver);
        PageFactory.initElements(driver, this);
    }

    // Getters of Page Objects
    public WebElement getOverlayCountryClose() {
        return util.waitForElementToBeClickable(driver, this.overlayCountryClose);
    }

    public WebElement getFooter() {
        return util.waitForElementToBeVisible(driver, this.footer);
    }

    public WebElement getDetailText() {
        return util.waitForElementToBeVisible(driver, this.detailText);
    }

    // Page Actions
    public void closeDefaultLandingDialog() {
        if (getOverlayCountryClose().isDisplayed()) {

            util.clickOnElement(getOverlayCountryClose());
        }
    }

    public void clickOnSection(String sectionName) {
        WebElement section = driver.findElement(By.xpath("//p[text()='" + sectionName.trim().toUpperCase() + "']//parent::li"));
        util.clickOnElement(util.waitForElementToBeClickable(driver, section));
    }

    public DealDetailsPage clickOnDeal(String dealText) {

        List<WebElement> dealTextFirstList = driver.findElements(By.cssSelector("div.card-body p.card-text.deal-text-first"));
        boolean dealFound = false;
        boolean endOfPageReached = false;
        Actions act = new Actions(driver);

        // find deal until found it or reached footer
        do {

            for (WebElement dealTextFirst : dealTextFirstList) {
                dealFound = dealTextFirst.getText().split("•")[1].trim().toLowerCase().contains(dealText.trim().toLowerCase());
                if (dealFound) {
                    util.clickOnElement(dealTextFirst);
                    util.waitForLoad(driver);
                    dealFound = true;
                    return new DealDetailsPage(driver);
                }
            }

            // scroll by one page

//JavascriptExecutor js = (JavascriptExecutor) driver;
//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
//
//            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("Value')]")));
//
//            JavascriptExecutor jse = (JavascriptExecutor) driver;
//            jse.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            act.sendKeys(Keys.PAGE_DOWN).build().perform();

            // get the deals from the page
            dealTextFirstList = driver.findElements(By.cssSelector("div.card-body p.card-text.deal-text-first"));
//        } while (!dealFound && !endOfPageReached);
        } while (!dealFound);

        return new DealDetailsPage(driver);
    }
}
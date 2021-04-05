package com.cimb.pageobjects.my;

import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DealDetailsPage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;

    // Page Objects
    @FindBy(css = "p.detail-text-first")
    private WebElement detailTextFirst;

    @FindBy(css= "p.detail-text-second")
    private WebElement detailTextSecond;

    @FindBy(css = "div[class*='similar-section'] p[class*='section-title']")
    private WebElement similarSectionTitle;

    @FindBy(css = "div[class='section-body'] deal-item")
    private List<WebElement> similarDeals;

    // Constructor to initialize page objects
    public DealDetailsPage(WebDriver driver) {
        this.logger = LogManager.getLogger();
        this.driver = driver;
        this.util = new TestUtil(driver);
        PageFactory.initElements(driver, this);
    }

    // Getters of Page Objects
    public WebElement getDetailTextFirst() {
        return util.waitForElementToBeVisible(driver, this.detailTextFirst);
    }

    public WebElement getDetailTextSecond() {
        return util.waitForElementToBeVisible(driver, this.detailTextSecond);
    }

    public WebElement getSimilarSectionTitle() {
        return util.waitForElementToBeVisible(driver, this.similarSectionTitle);
    }

    public List<WebElement> getSimilarDeals() {
        return util.waitForElementsToBeVisible(driver, this.similarDeals);
    }

    public boolean isSimilarSectionTitleDisplayed(){
                           return getSimilarSectionTitle().isDisplayed();
    }


    public int noOfSimilarDealsDisplayed(){
         return getSimilarDeals().size();
    }
}

package com.cimb.pageobjects;

import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DealDetailsPage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;

    // Page Objects
    @FindBy(css = "p.detail-text-first")
    private WebElement detailTextFirst;

    @FindBy(css= "p.detail-text-second")
    private WebElement detailTextSecond;

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
}

package com.cimb.pageobjects;

import com.cimb.base.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DealDetailsPage extends TestBase {

    // Page Objects
    @FindBy(css = "p.detail-text-first")
    private WebElement detailTextFirst;

    @FindBy(css= "p.detail-text-second")
    private WebElement detailTextSecond;

    // Constructor to initialize page objects
    public DealDetailsPage(WebDriver driver) {
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

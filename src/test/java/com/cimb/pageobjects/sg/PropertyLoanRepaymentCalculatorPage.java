package com.cimb.pageobjects.sg;

import com.cimb.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * @author biswanath.padhi
 */

public class PropertyLoanRepaymentCalculatorPage extends TestBase {

    private Logger logger;

    //Constructors
    public PropertyLoanRepaymentCalculatorPage(WebDriver driver) {
        this.logger = LogManager.getLogger();

    }

    //Page Objects
    @FindBy(xpath = "//span[text()='Property Loan Amount']//..//input")
    private WebElement propertyLoanAmount;

    @FindBy(xpath = "//span[text()='Property Loan Tenure']//..//input[@type='tel']")
    private WebElement propertyLoanTenure;

    //Getters

    public WebElement getPropertyLoanAmount() {
        return util.waitForElementToBeClickable(driver, this.propertyLoanAmount);
    }

    public WebElement getPropertyLoanTenure() {
        return util.waitForElementToBeClickable(driver, this.propertyLoanTenure);
    }

    //Page Actions
    public void enterPropertyLoanAmount(long loanAmount) {
        util.enterTextinElement(getPropertyLoanAmount(), String.valueOf(loanAmount));
    }

    public void enterYearNInterestRate(byte year, float interestRate) {
        By interestRateFieldXpath = By.xpath("//span[text()='Year " + year + " Interest (% p.a.)']//..//input[@type='tel']");
        WebElement interestRateField = util.waitForElementToBeClickable(driver, driver.findElement(interestRateFieldXpath));
        util.enterTextinElement(interestRateField, String.valueOf(interestRate));
    }

    public void enterYear1Interest(float year1InterestRate) {
    }

    public void enterYear2Interest(float year2InterestRate) {
    }

    public void enterYear3Interest(float year3InterestRate) {
    }

    public void enterYear4Interest(float year4InterestRate) {
    }

    public void enterYear5Interest(float year5InterestRate) {
    }

    public void enterPropertyLoanTenure(long tenure) {
        util.enterTextinElement(getPropertyLoanTenure(), String.valueOf(tenure));
    }
}

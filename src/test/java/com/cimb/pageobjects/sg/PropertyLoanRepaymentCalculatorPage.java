package com.cimb.pageobjects.sg;

import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author biswanath.padhi
 */

public class PropertyLoanRepaymentCalculatorPage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;

    //Constructors
    public PropertyLoanRepaymentCalculatorPage(WebDriver driver) {
        this.logger = LogManager.getLogger();
        this.driver = driver;
        this.util = new TestUtil(driver);
        PageFactory.initElements(driver, this);
    }

    //Page Objects
    @FindBy(xpath = "//span[text()='Property Loan Amount']//..//input")
    private WebElement propertyLoanAmount;

    @FindBy(xpath = "//span[text()='Property Loan Tenure']//..//input[@type='tel']")
    private WebElement propertyLoanTenure;

    @FindBy(xpath = "//table//th[text()='Interest Rates (p.a.)']")
    private WebElement interestRatesTableColumnHeader;

    @FindBy(xpath = "//table//th[text()='Monthly Repayment']")
    private WebElement monthlyRepaymentTableColumnHeader;

    @FindBy(xpath = "//table//th[text()='Monthly Repayment']")
    private WebElement interestPayableTableColumnHeader;

    @FindBy(xpath = "//table//th[text()='Principal Payable (p.a.)']")
    private WebElement principalPayableTableColumnHeader;

    @FindBy(xpath = "//table//th[text()='Outstanding Principal at Year End (p.a.)']")
    private WebElement outstandingPrincipalTableColumnHeader;

    @FindBy(xpath = "//span[text()='Effective Interest Rate']")
    private WebElement effectiveInterestRate;

    @FindBy(xpath = "//span[text()='Total Interest Payable']")
    private WebElement totalInterestPayable;

    @FindBy(xpath = "//span[text()='Total Amount Payable']")
    private WebElement totalAmountPayable;

    @FindBy(css = "div[data-component='table']")
    private WebElement loanRepaymentTable;

    @FindBy(xpath = "//div[@data-component='table']//../h2")
    private WebElement loanRepaymentTableName;



    //Getters

    public WebElement getPropertyLoanAmount() {
        return util.waitForElementToBeClickable(driver, this.propertyLoanAmount);
    }

    public WebElement getPropertyLoanTenure() {
        return util.waitForElementToBeClickable(driver, this.propertyLoanTenure);
    }

    public WebElement getLoanRepaymentTable() {
        return util.waitForElementToBeVisible(driver, this.loanRepaymentTable);
    }



    //Page Actions
    public void enterPropertyLoanAmount(long loanAmount) {
        util.enterTextinElement(getPropertyLoanAmount(), String.valueOf(loanAmount));
    }

    public void enterYearNInterestRate(byte year, float interestRate) {
        By interestRateFieldXpath = null;
        if (year != 5) {
            interestRateFieldXpath = By.xpath("//span[text()='Year " + year + " Interest (% p.a.)']//..//input[@type='tel']");
        } else if (year == 5) {
            interestRateFieldXpath = By.xpath("//span[text()='Year " + year + " Interest (% p.a.) & Thereafter']//..//input[@type='tel']");
        } else {
            try {
                throw new Exception("Invalid year to enter inteest rate");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }

        WebElement interestRateField = util.waitForElementToBeClickable(driver, driver.findElement(interestRateFieldXpath));

        // Formatting the interest rate into 2 decimal places and making it as a string
        String value = String.format("%.2f", interestRate).replace(",", "");
        util.enterTextinElement(interestRateField, value);
    }

    public void enterPropertyLoanTenure(long tenure) {
        util.enterTextinElement(getPropertyLoanTenure(), String.valueOf(tenure));
    }

    public boolean isPropertyLoanAmountVisible(){
        return this.propertyLoanAmount.isDisplayed();
    }

    public boolean isPropertyLoanTenureVisible(){
        return this.propertyLoanTenure.isDisplayed();
    }

    public boolean isInterestRatesTableColumnHeaderVisible(){
        return this.interestRatesTableColumnHeader.isDisplayed();
    }

    public boolean isMonthlyRepaymentTableColumnHeaderVisible(){
        return this.monthlyRepaymentTableColumnHeader.isDisplayed();
    }

    public boolean isInterestPayableTableColumnHeaderVisible(){
        return this.interestPayableTableColumnHeader.isDisplayed();
    }

    public boolean isPrincipalPayableTableColumnHeaderVisible(){
        return this.principalPayableTableColumnHeader.isDisplayed();
    }

    public boolean isOutstandingPrincipalTableColumnHeaderVisible(){
        return this.outstandingPrincipalTableColumnHeader.isDisplayed();
    }

    public boolean isEffectiveInterestRateVisible(){
        return this.effectiveInterestRate.isDisplayed();
    }

    public boolean isTotalInterestPayable(){
        return this.totalInterestPayable.isDisplayed();
    }

    public boolean isTotalAmountPayable(){
        return this.totalAmountPayable.isDisplayed();
    }

    public boolean isLoanRepaymentTableVisible() {
        return this.loanRepaymentTable.isDisplayed();
    }

    public boolean isLoanRepaymentTableHeaderVisible() {
        return this.loanRepaymentTableName.isDisplayed();
    }

    public int getNumberOfRowsInLoanRepaymentTable() {

        // doing -1 to exclude table header row
        int noOfRows = getLoanRepaymentTable().findElements(By.cssSelector("tr")).size() - 1;

        return noOfRows;
    }
}

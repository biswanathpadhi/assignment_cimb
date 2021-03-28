package com.cimb.pageobjects.sg;

import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author biswanath.padhi
 */

public class ToolsPage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;

    //Page Objects

    //Getters

    //Page Actions
    public ToolsPage(WebDriver driver) {
        this.logger = LogManager.getLogger();
        this.driver = driver;
        this.util = new TestUtil(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectTool(String toolName) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement tool = driver.findElement(By.xpath("//h3[text()='" + toolName.trim().toUpperCase() + "']"));
        util.waitForElementToBeClickable(driver, tool);
        util.clickOnElement(tool);
        util.waitForLoad(driver);
        driver = util.switchToWindowById(1);
    }

    public PropertyLoanRepaymentCalculatorPage selectPropertyLoanRepaymentCalculator() throws InterruptedException {
        selectTool("PROPERTY LOAN REPAYMENT CALCULATOR");
        return new PropertyLoanRepaymentCalculatorPage(driver);
    }
}

package com.cimb.pageobjects.sg;

import com.cimb.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * @author biswanath.padhi
 */

public class ToolsPage extends TestBase {

    private Logger logger;
    //Page Objects


    //Getters

    //Page Actions
    public ToolsPage(WebDriver driver) {
        logger = LogManager.getLogger();
        PageFactory.initElements(driver, this);
    }

    public void selectTool(String toolName){
        WebElement tool = driver.findElement(By.xpath("//h3[text()='"+ toolName.trim().toUpperCase() +"']"));
        util.clickOnElement(tool);
        util.waitForLoad(driver);
    }

    public PropertyLoanRepaymentCalculatorPage selectPropertyLoanRepaymentCalculator(){
        selectTool("PROPERTY LOAN REPAYMENT CALCULATOR");
        return new PropertyLoanRepaymentCalculatorPage(driver);
    }
}

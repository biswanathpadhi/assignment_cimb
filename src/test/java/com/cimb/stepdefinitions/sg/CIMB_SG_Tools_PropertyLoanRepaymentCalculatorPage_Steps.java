package com.cimb.stepdefinitions.sg;


import com.cimb.factory.DriverFactory;
import com.cimb.pageobjects.sg.CIMB_SG_HomePage;
import com.cimb.pageobjects.sg.PropertyLoanRepaymentCalculatorPage;
import com.cimb.pageobjects.sg.ToolsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biswanath.padhi
 */

public class CIMB_SG_Tools_PropertyLoanRepaymentCalculatorPage_Steps {
    private final Logger logger = LogManager.getLogger();
    //Custom objects
    String[] expectedYearInterestRates;
    private CIMB_SG_HomePage cimbSingaporeHomePage;
    private ToolsPage toolsPage;
    private PropertyLoanRepaymentCalculatorPage propertyLoanRepaymentCalculatorPage;

    @Given("I’m on CIMB Singapore {string} page")
    public void iMOnCIMBSingaporePage(String countryName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        cimbSingaporeHomePage = new CIMB_SG_HomePage(DriverFactory.getDriver());
        cimbSingaporeHomePage.visitMe();
//        cimbSingaporeHomePage.closeDefaultLandingDialog();
    }

    @Given("^I navigate to Tools page from menu$")
    public void i_navigate_to_Tools_page_from_menu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        cimbSingaporeHomePage.clickBurgerMenu();
        toolsPage = cimbSingaporeHomePage.clickOnTools();
    }

    @When("^I access to Property Loan Repayment Calculator from menu$")
    public void i_access_to_Property_Loan_Repayment_Calculator_from_menu() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        propertyLoanRepaymentCalculatorPage = toolsPage.selectPropertyLoanRepaymentCalculator();
    }

    @And("I have inputted {string}, {string}, {string}, {string}, {string}, {string}, {string} values")
    public void iHaveInputtedValues(String propertyLoanAmount, String propertyLoanTenure, String year1Interest, String year2Interest, String year3Interest, String year4Interest, String year5Interest) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        propertyLoanRepaymentCalculatorPage.enterPropertyLoanAmount(Long.parseLong(propertyLoanAmount));
        propertyLoanRepaymentCalculatorPage.enterPropertyLoanTenure(Long.parseLong(propertyLoanTenure));


        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 1, Float.parseFloat(year1Interest));
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 2, Float.parseFloat(year2Interest));
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 3, Float.parseFloat(year3Interest));
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 4, Float.parseFloat(year4Interest));
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 5, Float.parseFloat(year5Interest));

        //used for verification later
        expectedYearInterestRates = new String[]{year1Interest, year2Interest, year3Interest, year4Interest, year5Interest};
    }

    @Then("^I will be able to see the Effective Interest Rate, Total Interest Payable and Total Amount Payable$")
    public void i_will_be_able_to_see_the_Effective_Interest_Rate_Total_Interest_Payable_and_Total_Amount_Payable() throws Throwable {

        Assert.assertTrue("Effective Interest Rate is not visible", propertyLoanRepaymentCalculatorPage.isEffectiveInterestRateVisible());
        Assert.assertTrue("Total Interest Payable is not visible", propertyLoanRepaymentCalculatorPage.isTotalInterestPayable());
        Assert.assertTrue("Total Amount Payable is not visible", propertyLoanRepaymentCalculatorPage.isTotalAmountPayable());
    }

    @And("I will be able to see the loan repayment table with {string} that I’ve entered")
    public void iWillBeAbleToSeeTheLoanRepaymentTableWithThatIVeEntered(String propertyLoanTenure) throws Throwable {

        //verify table and tablename are displayed
        Assert.assertTrue("Loan Repayment Table is not visible", propertyLoanRepaymentCalculatorPage.isLoanRepaymentTableVisible());
        Assert.assertTrue("Loan Repayment Table Name is not visible", propertyLoanRepaymentCalculatorPage.isLoanRepaymentTableHeaderVisible());


        //verify total number of rows as per tenure entered
        Assert.assertEquals(propertyLoanRepaymentCalculatorPage.getNumberOfRowsInLoanRepaymentTable(), Integer.parseInt(propertyLoanTenure));
    }

    @Then("^different interest rates for different year based on my input$")
    public void different_interest_rates_for_different_year_based_on_my_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        // Verify that % symbol is present for everyvalue

        List<String> actualListWithPercentage = propertyLoanRepaymentCalculatorPage.getInterestRateColumnValuesFromLoanRepaymentTable();
        List<String> actualListWithOutPercentage = new ArrayList<>();

        System.out.println("actualListwithpercentage: " + actualListWithPercentage);
        
        actualListWithPercentage.forEach(value -> {
            Assert.assertTrue("Percentage (%) not displayed for interest value: " + value , value.contains("%"));
            actualListWithOutPercentage.add(value.replace("%", ""));
        });

        for (int i = 0; i < actualListWithOutPercentage.size(); i++) {
            if (i < 4) {
                Assert.assertEquals("Interest rates does not match for year "+ i + "in the table:", expectedYearInterestRates[i], actualListWithOutPercentage.get(i));
            } else {
                Assert.assertEquals("Interest rates does not match for year "+ i + "in the table:", expectedYearInterestRates[4], actualListWithOutPercentage.get(i));
            }

        }
    }

}

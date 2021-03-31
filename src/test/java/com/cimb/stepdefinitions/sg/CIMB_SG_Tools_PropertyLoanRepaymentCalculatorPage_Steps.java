package com.cimb.stepdefinitions.sg;


import com.cimb.factory.DriverFactory;
import com.cimb.pageobjects.sg.CIMB_SG_HomePage;
import com.cimb.pageobjects.sg.PropertyLoanRepaymentCalculatorPage;
import com.cimb.pageobjects.sg.ToolsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * @author biswanath.padhi
 */

public class CIMB_SG_Tools_PropertyLoanRepaymentCalculatorPage_Steps {
    private final Logger logger = LogManager.getLogger();
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

    @When("^I have inputted all necessary values$")
    public void i_have_inputted_all_necessary_values() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        propertyLoanRepaymentCalculatorPage.enterPropertyLoanAmount(10000l);
        propertyLoanRepaymentCalculatorPage.enterPropertyLoanTenure(5l);
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 1, 1.5f);
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 2, 2.5f);
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 3, 3.5f);
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 4, 4.5f);
        propertyLoanRepaymentCalculatorPage.enterYearNInterestRate((byte) 5, 5.1f);
    }

    @Then("^I will be able to see the Effective Interest Rate, Total Interest Payable and Total Amount Payable$")
    public void i_will_be_able_to_see_the_Effective_Interest_Rate_Total_Interest_Payable_and_Total_Amount_Payable() throws Throwable {

        Assert.assertTrue("Effective Interest Rate is not visible",propertyLoanRepaymentCalculatorPage.isEffectiveInterestRateVisible());
        Assert.assertTrue("Total Interest Payable is not visible", propertyLoanRepaymentCalculatorPage.isTotalInterestPayable());
        Assert.assertTrue("Total Amount Payable is not visible", propertyLoanRepaymentCalculatorPage.isTotalAmountPayable());
    }

    @Then("^I will be able to see the loan repayment table with total loan tenure that I’ve entered$")
    public void i_will_be_able_to_see_the_loan_repayment_table_with_total_loan_tenure_that_I_ve_entered() throws Throwable {

        //verify table and tablename are displayed
        Assert.assertTrue("Loan Repayment Table is not visible", propertyLoanRepaymentCalculatorPage.isLoanRepaymentTableVisible());
        Assert.assertTrue("Loan Repayment Table Name is not visible", propertyLoanRepaymentCalculatorPage.isLoanRepaymentTableHeaderVisible());


        //verify total number of rows as per tenure entered
        Assert.assertEquals(propertyLoanRepaymentCalculatorPage.getNumberOfRowsInLoanRepaymentTable(), 5);
    }

    @Then("^different interest rates for different year based on my input$")
    public void different_interest_rates_for_different_year_based_on_my_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }
}

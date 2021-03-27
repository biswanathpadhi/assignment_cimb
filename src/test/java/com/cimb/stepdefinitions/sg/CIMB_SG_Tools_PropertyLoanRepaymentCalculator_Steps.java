package com.cimb.stepdefinitions.sg;


import com.cimb.base.TestBase;
import com.cimb.pageobjects.sg.CIMB_SG_HomePage;
import com.cimb.pageobjects.sg.PropertyLoanRepaymentCalculatorPage;
import com.cimb.pageobjects.sg.ToolsPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author biswanath.padhi
 */

public class CIMB_SG_Tools_PropertyLoanRepaymentCalculator_Steps extends TestBase {
    private final Logger logger;
    private final CIMB_SG_HomePage cimbSingaporeHomePage;
    private ToolsPage toolsPage;
    private PropertyLoanRepaymentCalculatorPage propertyLoanRepaymentCalculatorPage;

    public CIMB_SG_Tools_PropertyLoanRepaymentCalculator_Steps() {
        this.logger = LogManager.getLogger();
        this.cimbSingaporeHomePage = new CIMB_SG_HomePage(driver);
    }

//    @Given("I’m on CIMB SG page")
//    public void iMOnCIMBSGPage() {
//        try {
//            String url = properties.getProperty("cimb_" + countryName.trim().toLowerCase() + "_url");
//            driver.get(url);
//            cimbMyHomePage.closeDefaultLandingDialog();
//        } catch (Exception e) {
//            logger.debug("Failed to execute iMOnCIMBPage step");
//            logger.error("Exception occured: " + e.getMessage());
//        }
//    }
//
//    @And("I navigate to Tools page from menu")
//    public void iNavigateToToolsPageFromMenu() {
//    }
//
//    @When("I access to Property Loan Repayment Calculator from menu")
//    public void iAccessToPropertyLoanRepaymentCalculatorFromMenu() {
//    }
//
//    @And("I have inputted all necessary values")
//    public void iHaveInputtedAllNecessaryValues() {
//    }
//
//    @Then("I will be able to see the Effective Interest Rate, Total Interest Payable and Total Amount Payable")
//    public void iWillBeAbleToSeeTheEffectiveInterestRateTotalInterestPayableAndTotalAmountPayable() {
//    }
//
//    @And("I will be able to see the loan repayment table with total loan tenure that I’ve entered")
//    public void iWillBeAbleToSeeTheLoanRepaymentTableWithTotalLoanTenureThatIVeEntered() {
//    }
//
//    @And("different interest rates for different year based on my input")
//    public void differentInterestRatesForDifferentYearBasedOnMyInput() {
//    }


    @Given("^I’m on CIMB \"([^\"]*)\" page$")
    public void i_m_on_CIMB_page(String countryName) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        try {
            String url = properties.getProperty("cimb_" + countryName.trim().toLowerCase() + "_url");
            driver.get(url);
            cimbSingaporeHomePage.closeDefaultLandingDialog();
        } catch (Exception e) {
            logger.debug("Failed to execute iMOnCIMBPage step");
            logger.error("Exception occured: " + e.getMessage());
        }
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
    public void i_will_be_able_to_see_the_Effective_Interest_Rate_Total_Interest_Payable_and_Total_Amount_Payable() throws
            Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^I will be able to see the loan repayment table with total loan tenure that I’ve entered$")
    public void i_will_be_able_to_see_the_loan_repayment_table_with_total_loan_tenure_that_I_ve_entered() throws
            Throwable {
        // Write code here that turns the phrase above into concrete actions
    }

    @Then("^different interest rates for different year based on my input$")
    public void different_interest_rates_for_different_year_based_on_my_input() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
    }


}

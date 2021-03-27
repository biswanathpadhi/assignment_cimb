package com.cimb.stepdefinitions.my;

import com.cimb.base.TestBase;
import com.cimb.pageobjects.CIMB_MY_HomePage;
import com.cimb.pageobjects.DealDetailsPage;
import com.cimb.pageobjects.DealsPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CIMB_MY_Deals_Steps extends TestBase {

    private String countryName = "my";
    private Logger logger;
    private CIMB_MY_HomePage cimbMalaysiaHomePage;
    private DealsPage dealsPage;
    private DealDetailsPage dealDetailsPage;

    public CIMB_MY_Deals_Steps() {
        this.logger = LogManager.getLogger();
        this.cimbMalaysiaHomePage = new CIMB_MY_HomePage(driver);
    }

    @Given("^I’m on CIMB \"([^\"]*)\" page$")
    public void iMOnCIMBPage(String countryName) throws Throwable {
        try {
            String url = properties.getProperty("cimb_" + countryName.trim().toLowerCase() + "_url");
            driver.get(url);
            cimbMalaysiaHomePage.closeDefaultLandingDialog();
        } catch (Exception e) {
            logger.debug("Failed to execute iMOnCIMBPage step");
            logger.error("Exception occured: " + e.getMessage());
        }
    }

    @When("I select CIMB Deals")
    public void iSelectCIMBDeals() {
        cimbMalaysiaHomePage.clickBurgerMenu();
        dealsPage = cimbMalaysiaHomePage.clickOnCIMBDeals();
        dealsPage.closeDefaultLandingDialog();
    }

    @And("^I would like to explore more for \"([^\"]*)\" under \"([^\"]*)\" section$")
    public void iWouldLikeToExploreMoreForUnderSection(String dealTextFirst, String sectionName) throws Throwable {

        // select the section
        dealsPage.clickOnSection(sectionName);

        // select the deal to explore
        dealDetailsPage = dealsPage.clickOnDeal(dealTextFirst);
    }

    @Then("I will be able to see its details and other similar deals")
    public void iWillBeAbleToSeeItsDetailsAndOtherSimilarDeals() throws Throwable {
        Assert.assertEquals(dealDetailsPage.getDetailTextSecond().getText(), "March Regional 50% Bonus BIG Points Campaign");
    }
}

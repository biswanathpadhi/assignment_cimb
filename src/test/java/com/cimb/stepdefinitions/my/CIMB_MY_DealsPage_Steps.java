package com.cimb.stepdefinitions.my;

import com.cimb.factory.DriverFactory;
import com.cimb.pageobjects.my.CIMB_MY_HomePage;
import com.cimb.pageobjects.my.DealDetailsPage;
import com.cimb.pageobjects.my.DealsPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class CIMB_MY_DealsPage_Steps {

    private String countryName = "my";
    private Logger logger = LogManager.getLogger();
    private CIMB_MY_HomePage cimbMalaysiaHomePage;
    private DealsPage dealsPage;
    private DealDetailsPage dealDetailsPage;
    private String deatailTextSecodForTheDeal;


    @Given("^I’m on CIMB \"([^\"]*)\" page$")
    public void iMOnCIMBPage(String countryName) throws Throwable {
        cimbMalaysiaHomePage = new CIMB_MY_HomePage(DriverFactory.getDriver());
        cimbMalaysiaHomePage.visitMe();
        cimbMalaysiaHomePage.closeDefaultLandingDialog();

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
        deatailTextSecodForTheDeal = dealsPage.getDetailTextSecondForDeal(dealTextFirst);

        // select the deal to explore
        dealDetailsPage = dealsPage.clickOnDeal(dealTextFirst);

    }

    @Then("I will be able to see its details and other similar deals")
    public void iWillBeAbleToSeeItsDetailsAndOtherSimilarDeals() throws Throwable {
        Assert.assertEquals(dealDetailsPage.getDetailTextSecond().getText(), deatailTextSecodForTheDeal);
        Assert.assertTrue(dealDetailsPage.isSimilarSectionTitleDisplayed(), "Similar Deal Section title is not displayed");
        Assert.assertTrue(dealDetailsPage.noOfSimilarDealsDisplayed() > 0, "No similar deals displayed");

    }
}

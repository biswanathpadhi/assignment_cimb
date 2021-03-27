package com.cimb.stepdefinitions;

import com.cimb.base.TestBase;
import com.cimb.pageobjects.CIMB_MY_HomePage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CIMB_MY_Deals_Steps extends TestBase {

    private Logger logger;
    private CIMB_MY_HomePage cimbMyHomePage;

    public CIMB_MY_Deals_Steps() {
        this.logger = LogManager.getLogger();
        logger.info("Inside CIMB_MY_Deals_Steps constructor");
        this.cimbMyHomePage = new CIMB_MY_HomePage(driver);
    }

    @Given("I’m on CIMB MY page")
    public void iMOnCIMBMYPage() {
        System.out.println("Given");
        String url = properties.getProperty("cimb_my_url");
        driver.get(url);
    }

    @When("I select CIMB Deals")
    public void iSelectCIMBDeals() {
        System.out.println("2");
    }

    @And("I would like to explore more for Rentalcars.com under Travel & Fun")
    public void iWouldLikeToExploreMoreForRentalcarsComUnderTravelFun() {
        System.out.println("3");
    }

    @Then("I will be able to see its details and other similar deals")
    public void iWillBeAbleToSeeItsDetailsAndOtherSimilarDeals() {
        System.out.println("4");
    }

}

package com.cimb.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CIMB_MY_Deals_Steps {

    @Given("I’m on CIMB MY page")
    public void iMOnCIMBMYPage() {
        System.out.println("1");
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

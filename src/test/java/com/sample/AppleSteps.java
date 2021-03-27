package com.sample;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppleSteps {

    Logger log = LogManager.getLogger();

    @Given("^I am on apple page$")
    public void iAmOnApplePage() {
        log.info("g");
    }

    @When("^i select i(\\d+)$")
    public void iSelectI(int arg0) {
        log.info("w");
    }

    @And("^i liked it$")
    public void iLikedIt() {
        log.info("a");
    }

    @Then("^i should be able to buy it$")
    public void iShouldBeAbleToBuyIt() {
        log.info("t");
    }
}

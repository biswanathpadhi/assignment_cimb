package com.sample;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
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

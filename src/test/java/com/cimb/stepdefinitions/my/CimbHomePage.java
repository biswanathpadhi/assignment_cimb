package com.cimb.stepdefinitions.my;

import com.cimb.base.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CimbHomePage extends TestBase {


    private static final Logger logger = LogManager.getLogger();

    public CimbHomePage() {
    }

    public void visitHomePage(String countryName) {
        try {
            String url = properties.getProperty("cimb_" + countryName.trim().toLowerCase() + "_url");
            driver.get(url);

        } catch (Exception e) {
            logger.debug("Failed to execute iMOnCIMBPage step");
            logger.error("Exception occured: " + e.getMessage());
        }
    }
}

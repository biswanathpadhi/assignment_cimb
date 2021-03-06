package com.cimb.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author biswanath.padhi
 */


@RunWith(Cucumber.class)
@CucumberOptions(features =
        {
                "src/test/resources/features/CIMB"
        }, // the path of the feature files

        glue = {"com/cimb/stepdefinitions", "com/cimb/util"}, // the path of the step definition files from test java folder

//        tags = {"@my", "@sg", "@deals", "tools"},

        // plugin to generate report in different formats
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        },
        monochrome = true, // display the console output in a proper readable format
        stepNotifications = true, // it will check if any step is not defined in step definition file
        dryRun = false // to check the mapping is proper between feature file and step def file
)

public class TestRunner {

}
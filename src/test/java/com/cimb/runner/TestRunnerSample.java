package com.cimb.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author biswanath.padhi
 */


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features/Sample", // the path of the feature files
        glue = {"com/sample"}, // the path of the step definition files
        // plugin to generate report in different formats
        plugin = {"pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "timeline:test-output-thread/"},
        monochrome = true, // display the console output in a proper readable format
        stepNotifications = true, // it will check if any step is not defined in step definition file
        dryRun = false // to check the mapping is proper between feature file and step def file
)


public class TestRunnerSample {


}

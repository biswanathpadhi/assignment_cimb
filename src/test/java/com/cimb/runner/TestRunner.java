package com.cimb.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * @author biswanath.padhi
 */

@RunWith(Cucumber.class)
@CucumberOptions(features = {
//		"src/test/java/com/cimb/features/CIMB_SG_Tools_PropertyLoanRepaymentCalculator.feature",
		"src/test/java/com/cimb/features/CIMB_MY_Deals.feature"},// the path of the feature files
        glue = {"com/cimb/stepdefinitions"}, // the path of the step definition files

        // plugin to generate report in different formats
        plugin = {"pretty",
                "html:target/cucumber_reports",
                "json:target/cucumber_reports/CucumberTestReport.json",
                "junit:target/junit_reports/cucumber.xml",
                "com.cucumber.listener.ExtentCucumberFormatter:target/TestResultReport.html"}, // to generate different types of
        // reporting
        monochrome = true, // display the console output in a proper readable format
        strict = true, // it will check if any step is not defined in step definition file
        dryRun = false // to check the mapping is proper between feature file and step def file
)

public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }
}

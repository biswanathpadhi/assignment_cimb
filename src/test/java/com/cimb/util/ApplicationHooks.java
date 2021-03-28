package com.cimb.util;

import com.cimb.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class ApplicationHooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private final Logger logger = LogManager.getLogger();
    Properties prop;

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prop();
    }

    @Before(order = 1)
    public void launchBrowser() {
        String browserName = prop.getProperty("browserType");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);

    }



    @After(order = 0)
    public void tearDown(Scenario scenario) {
        try {
            logger.info(" ****************************   TEARDOWN   **********************************");
            if (scenario.isFailed()) {
                // take screenshot:
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                byte[] sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(sourcePath, "image/png", screenshotName);
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }finally {
            try {
                if (driver != null)
                    driver.quit();
            } catch (Exception e) {
                logger.debug("Could not quit session");
            }
        }
    }

}
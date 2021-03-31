package com.cimb.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private final Logger logger = LogManager.getLogger();

    public WebDriver driver;

    public static ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();

    /**
     * This method is used to initialize the thradlocal driver on the basis of given
     * browser
     *
     * @param browser
     * @return this will return tldriver.
     */
    public WebDriver init_driver(String browser) {

        logger.info("browser:" + browser);

        browser = browser.toLowerCase();

        if (browser.equals("chrome")) {

            /**
             * Create webdriver using webdrivermanager ----- CURRENTLY HAVING ISSUE with as reported in GITHUBISSUE 442
             */
            System.setProperty("webdriver.chrome.silentOutput", "true");
            WebDriverManager.chromedriver().setup();
            threadLocal.set(new ChromeDriver());

            /**
             * Create webdriver using local driver file
             */

            /*
            String userDir = System.getProperty("user.dir");
             Load using chromeDriver
            System.setProperty("webdriver.chrome.driver", "//src//test//resources//drivers/chromedriver.exe");
            System.setProperty("webdriver.chrome.driver", "src//test//resources//drivers//chromedriver.exe");
            threadLocal.set(new ChromeDriver());

             */
        } else if (browser.equals("firefox")) {

            WebDriverManager.firefoxdriver().setup();
            threadLocal.set(new FirefoxDriver());

        } else if (browser.equals("edge")) {

            WebDriverManager.edgedriver().setup();
            threadLocal.set(new EdgeDriver());

        } else if (browser.equals("safari")) {

            threadLocal.set(new SafariDriver());

        } else {
            logger.error("Invalid browser: " + browser);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        return getDriver();

    }

    /**
     * this is used to get the driver with ThreadLocal
     *
     * @return
     */
    public static synchronized WebDriver getDriver() {
        return threadLocal.get();
    }
}

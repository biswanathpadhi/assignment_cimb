package com.cimb.base;

import com.cimb.util.Actions;
import com.cimb.util.TestUtil;
import com.cimb.util.WebEventListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author biswanath.padhi
 */
public class TestBase {

    public static final String userDir = System.getProperty("user.dir");
    public static final String resourcesDir = userDir + "//src//test//java//com//cimb//resources//";
    public static final String configDir = userDir + "//src//test//resources//config";
    public static final String testDataDir = userDir + "//src//test//java//com//imdpeach//testdata//";
    public static final String propertiesFileName = "config.properties";
    public static final String propertiesFilePath = configDir + propertiesFileName;
    public static Properties properties;
    public static WebDriver driver;
    public static TestUtil util;
    public static Actions actions;
    protected static short scenarioCount = 0;
    private static String browserName;
    private static Logger logger = LogManager.getLogger();

    public TestBase() {
        
        // To initiate the prop
        try (InputStream in = TestBase.class.getClassLoader().getResourceAsStream("config/configResourceTest.properties")) {
            properties = new Properties();
            properties.load(in);
            System.out.println(properties.getProperty("location"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void initializeDriver() {

        browserName = properties.getProperty("browserType");
        EventFiringWebDriver e_driver;
        WebEventListener eventListener;

        if (browserName.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", resourcesDir + "drivers/chromedriver.exe");

            driver = new ChromeDriver();

        } else if (browserName.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", resourcesDir + "geckodriver.exe");

            driver = new FirefoxDriver();
        }

        e_driver = new EventFiringWebDriver(driver);
        // Now create object of EventListerHandler to register it with EventFiringWebDriver
        eventListener = new WebEventListener();
        e_driver.register(eventListener);
        driver = e_driver;

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);


    }
}

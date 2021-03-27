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


/**
 * @author biswanath.padhi
 */
public class TestBase {

    public static final String userDir = System.getProperty("user.dir");
    public static final String testResourcesDir = userDir + "//src//test//resources//";
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
    private static Logger logger;

    public TestBase() {

        // To initiate the prop
        try (InputStream in = TestBase.class.getClassLoader().getResourceAsStream("config/" + propertiesFileName)) {
            logger = LogManager.getLogger();
            properties = new Properties();
            properties.load(in);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    public static void initializeDriver() {
        logger.info("inside initializeDriver()");

        browserName = properties.getProperty("browserType");
        EventFiringWebDriver e_driver;
        WebEventListener eventListener;

        if (browserName.equalsIgnoreCase("chrome")) {

            System.setProperty("webdriver.chrome.driver", testResourcesDir + "drivers/chromedriver.exe");
//            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
//
            driver = new ChromeDriver();

//            WebDriverManager.chromedriver().setup();
//            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
//            driver = new ChromeDriver();


        } else if (browserName.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", testResourcesDir + "geckodriver.exe");

            driver = new FirefoxDriver();
        }

//        e_driver = new EventFiringWebDriver(drisver);
//        // Now create object of EventListerHandler to register it with EventFiringWebDriver
//        eventListener = new WebEventListener();
//        e_driver.register(eventListener);
//        driver = e_driver;

        driver.manage().window().maximize();

        driver.manage().deleteAllCookies();

//        driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
//
//        driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
        logger.info("end initializeDriver()");

    }
}

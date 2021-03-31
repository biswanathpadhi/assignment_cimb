package com.cimb.pageobjects.sg;

import com.cimb.util.ConfigReader;
import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author biswanath.padhi
 */
public class CIMB_SG_HomePage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;
    private final Properties properties;

    // Page Objects
    @FindBy(css = "svg[class*='overlay-close']")
    private WebElement overlayClose;

    @FindBy(css = "div[class*='btn-burger-menu']")
    private WebElement burgerMenu;

    @FindBy(css = "nav.nav-pillar a[href*='cimb-deals']")
    private WebElement cimbDeals;

    @FindBy(css = "footer.alp-cimbd-footer")
    private WebElement footer;

    // Constructor to initialize page objects
    public CIMB_SG_HomePage(WebDriver driver) {
        this.logger = LogManager.getLogger();
        this.driver = driver;
        this.util = new TestUtil(driver);
        this.properties = new ConfigReader().init_prop();
        PageFactory.initElements(driver, this);
    }

    // Getters of Page Objects
    public WebElement getOverlayClose() {
        return util.waitForElementToBeClickable(driver, this.overlayClose);
    }

    public WebElement getBurgerMenu() {
        return util.waitForElementToBeVisible(driver, this.burgerMenu);
    }

    public WebElement getCimbDeals() {
        return util.waitForElementToBeClickable(driver, this.cimbDeals);
    }


    // Page Actions
    public void closeDefaultLandingDialog() {
        util.waitForLoad(driver);
        if(getOverlayClose().isDisplayed())   util.clickOnElement(getOverlayClose());
    }

    public void clickBurgerMenu() {
        util.clickOnElement(getBurgerMenu());
    }

    public void clickOnQuickLinksByQuickLinkText(String quickLinkText) {
        WebElement quickLink = driver.findElement(By.xpath("//nav[@class='nav-pillar']/a[text()='" + quickLinkText + "']"));
        quickLink = util.waitForElementToBeClickable(driver, quickLink);
        util.clickOnElement(quickLink);
    }

    public ToolsPage clickOnTools() {
        this.clickOnQuickLinksByQuickLinkText("Tools");
        return new ToolsPage(driver);
    }

    public void visitMe(){
        try {
            String url = properties.getProperty("cimb_sg_url");
            driver.get(url);
            util.waitForLoad(driver);
            driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        } catch (Exception e) {
            logger.debug("Failed to execute iMOnCIMBPage step");
            logger.error("Exception occured: " + e.getMessage());
        }
    }
}
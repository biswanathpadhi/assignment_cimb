package com.cimb.pageobjects.sg;

import com.cimb.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author biswanath.padhi
 */
public class CIMB_SG_HomePage extends TestBase {

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
        util.clickOnElement(getOverlayClose());
    }

    public void clickBurgerMenu() {
        util.clickOnElement(getBurgerMenu());
    }

    public void clickOnQuickLinksByQuickLinkText(String quickLinkText) {
        WebElement quickLink = driver.findElement(By.xpath("//nav[@class='nav-pillar']/a[text()='" + quickLinkText + "']"));
        util.clickOnElement(quickLink);
    }


    public ToolsPage clickOnTools() {
        this.clickOnQuickLinksByQuickLinkText("Tools");
        return new ToolsPage(driver);
    }
}
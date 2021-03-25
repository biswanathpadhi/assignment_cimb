package com.cimb.pageobjects;

import com.cimb.base.TestBase;
import com.cimb.util.Actions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author biswanath.padhi
 */
public class CIMB_MY_HomePage extends TestBase {

    // Page Objects
    @FindBy(css = "svg[class*='overlay-close']")
    private WebElement overlayClose;

    @FindBy(css = "div[class*='btn-burger-menu']")
    private WebElement burgerMenu;

    @FindBy(css = "nav.nav-pillar a[href*='cimb-deals']")
    private WebElement cimbDeals;

    // Constructor to initialize page objects
    public CIMB_MY_HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    // Getters of Page Objects
    public WebElement getOverlayClose() {
        return util.waitForElementToBeClickable(driver, overlayClose);
    }

    public WebElement getBurgerMenu() {
        return util.waitForElementToBeVisible(driver, burgerMenu);
    }

    public WebElement getCimbDeals() {
        return util.waitForElementToBeClickable(driver, cimbDeals);
    }


    // Actions
    public SecureAreaPage clickLogin() {
//        util.clickOnElement(driver, getLoginButton());
        return new SecureAreaPage(driver);
    }

    public void closeDefaultLandingDialog() {
        Actions.dismissDialog();
    }

    public void clickBurgerMenu(){
        util.clickOnElement(driver, getBurgerMenu());
    }





//    public String getLoginErrorMessageText() {
//        return util.getMessageByExcludingChild(getLoginErrorMessage());
//    }
//
//    public void enterUsername(String username) {
//        util.enterTextinElement(getUsername(), username);
//    }
//
//    public void enterPassword(String password) {
//        util.enterTextinElement(getPassword(), password);
//    }

}
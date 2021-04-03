package com.cimb.pageobjects;

import com.cimb.util.TestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author biswanath.padhi
 */
public class DealsPage {

    private final TestUtil util;
    private Logger logger;
    private WebDriver driver;

    // Page Objects
    @FindBy(css = ".modal-close-button")
    private WebElement overlayCountryClose;

    By byDealItem =  By.cssSelector("deal-item");
    By dealSecondText = By.cssSelector("p[class='card-text deal-text-second'");

    // Constructor to initialize page objects
    public DealsPage(WebDriver driver) {
        this.logger = LogManager.getLogger();
        this.driver = driver;
        this.util = new TestUtil(driver);
        PageFactory.initElements(driver, this);
    }

    // Getters of Page Objects
    public WebElement getOverlayCountryClose() {
        return util.waitForElementToBeClickable(driver, this.overlayCountryClose);
    }

    // Page Actions
    public void closeDefaultLandingDialog() {
//        if (getOverlayCountryClose().isDisplayed()) {
//            util.waitAndDismissAppearedAlertsModals(getOverlayCountryClose());
//        }
        util.waitAndDismissAppearedAlertsModals(getOverlayCountryClose());
    }

    public void clickOnSection(String sectionName) {
        WebElement section = driver.findElement(By.xpath("//p[text()='" + sectionName.trim().toUpperCase() + "']//parent::li"));
        util.clickOnElement(util.waitForElementToBeClickable(driver, section));
    }

    public DealDetailsPage clickOnDeal(String dealText) throws InterruptedException {
        boolean dealFound = false;
        List<String> values = new ArrayList<>();
        By footerBy = By.cssSelector("footer");
        int dealItemCountBeforeScroll = 0;
        int dealItemCountAfterScroll = 0;
        String text = "";

        // find deal until found it or reached footer
        do {
            List<WebElement> dealTextFirstList = driver.findElements(By.cssSelector("deal-item p[class='card-text deal-text-first']"));
            dealText = new StringBuilder().append(Character.toUpperCase(dealText.charAt(0))).append(dealText.toLowerCase().substring(1)).toString();
            dealTextFirstList.stream().forEach(deal -> {
                if (!values.contains(deal.getText().split("•")[1].trim()))
                    values.add(deal.getText().split("•")[1].trim());
            });

            dealItemCountBeforeScroll = driver.findElements(byDealItem).size();

            System.out.println("values" + values);
            boolean expectedDealExistsOnPage = values.contains(dealText.toUpperCase());
            System.out.println("expectedDealExistsOnPage" + expectedDealExistsOnPage);
            if (expectedDealExistsOnPage) {
                dealFound = true;
                util.moveToElementByText(dealText);
                util.clickOnElementByText(dealText);
                util.waitForLoad(driver);
                return new DealDetailsPage(driver);
            }
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,screen.availHeight); ");
            Thread.sleep(5000);
            dealItemCountAfterScroll = driver.findElements(byDealItem).size();
            System.out.println("dealItemCountAfterScroll > dealItemCountBeforeScroll = " + dealItemCountAfterScroll + dealItemCountBeforeScroll);

        } while (!dealFound && (dealItemCountAfterScroll > dealItemCountBeforeScroll));

        return new DealDetailsPage(driver);
    }


    public String getDetailTextSecondForDeal(String dealTextFirst) {
        return findTheDealByDealTextFirst(dealTextFirst).getText();
    }

    private WebElement findTheDealByDealTextFirst(String dealTextFirst) {

        boolean dealFound = false;
        List<String> values = new ArrayList<>();
        WebElement targetElement = null;
        int dealItemCountBeforeScroll = 0;
        int dealItemCountAfterScroll = 0;


        // find deal until found it or reached footer
        do {
            List<WebElement> dealTextFirstList = driver.findElements(By.cssSelector("deal-item p[class='card-text deal-text-first']"));

            dealTextFirstList.stream().forEach(deal -> {
                if (!values.contains(deal.getText().split("•")[1].trim()))
                    values.add(deal.getText().split("•")[1].trim());
            });

            dealItemCountBeforeScroll = driver.findElements(By.cssSelector("deal-item")).size();

            boolean expectedDealExistsOnPage = values.contains(dealTextFirst.toUpperCase());

            if (expectedDealExistsOnPage) {
                dealFound = true;
                dealTextFirst = new StringBuilder().append(Character.toUpperCase(dealTextFirst.charAt(0))).append(dealTextFirst.toLowerCase().substring(1)).toString();
                util.moveToElementByText(dealTextFirst);
                targetElement = util.getElementByText(dealTextFirst);
                targetElement = targetElement.findElement(By.xpath("../p[@class='card-text deal-text-second']"));

            }

            //scroll
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,screen.availHeight); ");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dealItemCountAfterScroll = driver.findElements((byDealItem)).size();

        } while (!dealFound && (dealItemCountAfterScroll > dealItemCountBeforeScroll));

        return targetElement;
    }
}
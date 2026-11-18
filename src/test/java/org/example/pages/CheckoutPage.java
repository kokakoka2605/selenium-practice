package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    private By firstName = By.id("first-name");
    private By lastName = By.id("last-name");
    private By postalCode = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By errorMessage = By.cssSelector("[data-test='error']");
    private By subtotalLabel =By.className("summary_subtotal_label");
    private By totalLabel =By.className("summary_total_label");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver){
        super(driver);
    }

    public void fillInfo(String first, String last, String zip){
        type(firstName,first);
        type(lastName,last);
        type(postalCode,zip);
    }

    public void clickContinue(){
        click(continueButton);
    }

    public void clickFinish(){
        click(finishButton);
    }

    public String getErrorText(){
        return waitVisible(errorMessage).getText();
    }

    public String getSubtotalText(){
        return waitVisible(subtotalLabel).getText();
    }

    public String getTotalText(){
        return waitVisible(totalLabel).getText();
    }

    public String getCompleteText(){
        return waitVisible(completeHeader).getText();
    }
}

package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;


import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {

    private By cartItems  = By.className("cart_item");
    private By itemNames = By.className("inventory_item_name");
    private By checkoutButton = By.id("checkout");
    private By continueShoppongButton = By.id("continue-shopping");


    public CartPage(WebDriver driver){
        super(driver);
    }

    public int getItemsCount() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(cartItems));
        return driver.findElements(cartItems).size();
    }

    public List<String> getItemNames(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(itemNames));
        List<String> names = new ArrayList<>();
        for(WebElement e: driver.findElements(itemNames)) {
            names.add(e.getText());
        }
        return names;
    }

    public void removeItem(String itemId){
        click(By.id("remove-"+itemId));
    }

    public void clickCheckout(){
        click(checkoutButton);
    }

    public void clickContinueShopping(){
        click(continueShoppongButton);
    }
}

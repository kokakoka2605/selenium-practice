package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage extends BasePage {

    private By cartIcon = By.id("shopping_cart_container");
    private By cartBadge = By.className("shopping_cart_badge");
    private By productNames = By.className("inventory_item_name");
    private By productPrices = By.className("inventory_item_price");
    private By productImages = By.cssSelector("img.inventory_item_img");
    private By sortDropdown = By.cssSelector("[data-test='product-sort-container']");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");



    public InventoryPage (WebDriver driver){
      super(driver);
    }

    public void addItemToCart(String itemId){
        click(By.id("add-to-cart-" + itemId));
    }

    public void removeItemFromCart(String itemId){
        click(By.id("remove-"+ itemId));
    }

    public void openCart(){
        click(cartIcon);
    }

    public boolean isCartVisible() {
        return waitVisible(cartIcon).isDisplayed();
    }

    //число на счетчике корзины. Если значка нет(корзина пуста) - 0.
    public int getCartBadgeCount(){
        List<WebElement> badge = driver.findElements(cartBadge);
        return badge.isEmpty() ? 0 : Integer.parseInt(badge.get(0).getText());
    }

    public int getProductsCount(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
        return driver.findElements(productNames).size();
    }

    public List<String> getProductNames(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productNames));
        List<String> names = new ArrayList<>();
        for(WebElement e: driver.findElements(productNames)){
            names.add(e.getText());
        }
        return names;
    }

    public List<Double> getProductPrices(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productPrices));
        List<Double> prices = new ArrayList<>();
        for(WebElement e : driver.findElements(productPrices)){
            prices.add(Double.parseDouble(e.getText().replace("$","")));
        }
        return prices;
    }
    /** value: az, za, lohi(от низкой цены), hilo */
    public void sortBy(String value){
        new Select(waitVisible(sortDropdown)).selectByValue(value);
    }

    public List<String> getProductImageSources(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(productImages));
        List<String> sources = new ArrayList<>();
        for(WebElement image: driver.findElements(productImages)){
            sources.add(image.getAttribute("src"));
        }
        return sources;
    }

    public void logout(){
        click(menuButton);
        click(logoutLink);
    }
}

package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class InventoryPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By cartIcon = By.id("shopping_cart_container");

    public InventoryPage (WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void addItemToCart(String itemId){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-" + itemId))).click();
    }

    public void openCart(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).click();
    }

    public boolean isCartVisible() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cartIcon)).isDisplayed();
    }

    public List<String> getProductImageSources(){
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("img.inventory_item_img")));
        List<WebElement> images = driver.findElements(By.cssSelector("img.inventory_item_img"));

        List<String> sources= new ArrayList<>();
        for(WebElement image: images){
            sources.add(image.getAttribute("src"));
        }
        return sources;
    }
}

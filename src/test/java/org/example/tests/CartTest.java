package org.example.tests;
import org.example.factory.DriverFactory;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class CartTest {

    WebDriver driver;
    LoginPage loginPage ;
    InventoryPage inventoryPage;
    CartPage cartPage;
    @BeforeEach
    public void setUp()  {

        driver = DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        loginPage.open();

    }
    @Test
    public void testAddSixItemsToCart() {
        loginPage.login("standard_user", "secret_sauce");
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.addItemToCart("sauce-labs-bike-light");
        inventoryPage.addItemToCart("sauce-labs-bolt-t-shirt");
        inventoryPage.addItemToCart("sauce-labs-fleece-jacket");
        inventoryPage.addItemToCart("sauce-labs-onesie");
        inventoryPage.addItemToCart("test.allthethings()-t-shirt-(red)");
        inventoryPage.openCart();

        assertEquals(6, cartPage.getItemsCount(), "В корзине должно быть 6 товаров");
        System.out.println("Тест выполнен");

    }

    @Test
    public void testProductImagesAreUniqueForProblemUser(){
        loginPage.login("problem_user","secret_sauce");

        List<String> images = inventoryPage.getProductImageSources();
        Set<String> uniqueImages = new HashSet<>(images);
        assertEquals(images.size(),uniqueImages.size(), "У каждого товара должна быть своя картинка");
    }

       @AfterEach
       public void tearDown(){
        driver.quit();
       }

}
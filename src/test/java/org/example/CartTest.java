package org.example;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;

public class CartTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage ;
    InventoryPage inventoryPage;
    CartPage cartPage;
    @BeforeEach
    public void setUp()  {
        ChromeOptions options = new ChromeOptions();


        options.addArguments("--incognito"); // режим инкогнито — password manager не активен
        options.addArguments("--disable-save-password-bubble");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck,SafeBrowsingEnhanced");

        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
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
           @AfterEach
           public void tearDown(){
           driver.quit();
           }


}
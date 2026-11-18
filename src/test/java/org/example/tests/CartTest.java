package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.BaseTest;
import org.example.data.Users;
import org.junit.jupiter.api.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@Epic("saucedemo")
@Feature("Корзина")
public class CartTest extends BaseTest {

    private static final String BACKPACK =  "sauce-labs-backpack";
    private static final String BIKE_LIGHT =  "sauce-labs-bike-light";

    @BeforeEach
    public void loginAsStandardUser(){
        loginPage.login(Users.STANDARD,Users.PASSWORD);
    }

    @Test
    @Tag("smoke")
    @DisplayName("Добавление всех 6 товаров в корзину")
    public void testAddSixItemsToCart() {
        inventoryPage.addItemToCart("sauce-labs-backpack");
        inventoryPage.addItemToCart("sauce-labs-bike-light");
        inventoryPage.addItemToCart("sauce-labs-bolt-t-shirt");
        inventoryPage.addItemToCart("sauce-labs-fleece-jacket");
        inventoryPage.addItemToCart("sauce-labs-onesie");
        inventoryPage.addItemToCart("test.allthethings()-t-shirt-(red)");
        inventoryPage.openCart();

        assertEquals(6, cartPage.getItemsCount(), "В корзине должно быть 6 товаров");
    }

    @Test
    @Tag("regression")
    @DisplayName("Значок корзины показывает количество товаров")
    public void testCartBadgeCounter(){
        assertEquals(0, inventoryPage.getCartBadgeCount(),"Сначала корзина пуста");
        inventoryPage.addItemToCart(BACKPACK);
        assertEquals(1, inventoryPage.getCartBadgeCount());
        inventoryPage.addItemToCart(BIKE_LIGHT);
        assertEquals(2, inventoryPage.getCartBadgeCount());
    }

    @Test
    @Tag("regression")
    @DisplayName("Удаление товара прямо из каталога")
    public void testRemoveItemFromInventory(){
        inventoryPage.addItemToCart(BACKPACK);
        inventoryPage.removeItemFromCart(BACKPACK);
        assertEquals(0,inventoryPage.getCartBadgeCount(),"После удаления значок должен пропасть");
    }

    @Test
    @Tag("regression")
    @DisplayName("Удаление товаров из самой корзины")
    public void testRemoveItemFromCartPage(){
        inventoryPage.addItemToCart(BACKPACK);
        inventoryPage.addItemToCart(BIKE_LIGHT);
        inventoryPage.openCart();
        cartPage.removeItem(BACKPACK);

        assertEquals(1,inventoryPage.getCartBadgeCount(),"Должен остаться 1 товар");
        assertEquals(List.of("Sauce Labs Bike Light"), cartPage.getItemNames());
    }

    @Test
    @Tag("regression")
    @DisplayName("В корзине именно те товары, что добавили")
    public void testCartContainsAddedItems() {
        inventoryPage.addItemToCart(BACKPACK);
        inventoryPage.openCart();
        assertEquals(List.of("Sauce Labs Backpack"), cartPage.getItemNames());
    }

    @Test
    @Tag("regression")
    @DisplayName("Continue Shopping возвращает в каталог")
    public void testContinueShopping() {
        inventoryPage.openCart();
        cartPage.clickContinueShopping();
        assertTrue(cartPage.getCurrentUrl().contains("inventory.html"));
    }
}
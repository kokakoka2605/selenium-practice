package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.BaseTest;
import org.example.data.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("saucedemo")
@Feature("Оформление заказа")
public class CheckoutTest extends BaseTest {

    @BeforeEach
    public void addItemsAndGoToCheckout(){
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        inventoryPage.addItemToCart("sauce-labs-backpack");   // $29.99
        inventoryPage.addItemToCart("sauce-labs-bike-light"); // $9.99
        inventoryPage.openCart();
        cartPage.clickCheckout();
    }

    @Test
    @Tag("smoke")
    @DisplayName("Полный заказ: от корзины до Thank you")
    public void estSuccessfulCheckout(){
        checkoutPage.fillInfo("Katia","Tester","01001");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        assertEquals("Thank you for your order!", checkoutPage.getCompleteText());
    }

    @Test
    @Tag("regression")
    @DisplayName("Сумма заказа считается правильно")
    public void testOrderSubtotal(){
        checkoutPage.fillInfo("Katia","Tester","01001");
        checkoutPage.clickContinue();

        assertEquals("Item total: $39.98", checkoutPage.getSubtotalText());
        assertTrue(checkoutPage.getTotalText().startsWith("Total: $"));
    }

    @Test
    @Tag("regression")
    @DisplayName("Без имени оформить нельзя")
    public void testCheckoutWithoutFirstName() {
        checkoutPage.fillInfo("","Tester", "01001");
        checkoutPage.clickContinue();
        assertEquals("Error: First Name is required", checkoutPage.getErrorText());
    }

    @Test
    @Tag("regression")
    @DisplayName("Без фамилии оформить нельзя")
    public void testCheckoutWithoutLastName() {
        checkoutPage.fillInfo("Katia", "", "01001");
        checkoutPage.clickContinue();
        assertEquals("Error: Last Name is required", checkoutPage.getErrorText());
    }

    @Test
    @Tag("regression")
    @DisplayName("Без индекса оформить нельзя")
    public void testCheckoutWithoutPostalCode() {
        checkoutPage.fillInfo("Katia", "Tester", "");
        checkoutPage.clickContinue();
        assertEquals("Error: Postal Code is required", checkoutPage.getErrorText());
    }
}

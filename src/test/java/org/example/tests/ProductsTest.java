package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.BaseTest;
import org.example.data.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("saucedemo")
@Feature("Каталог товаров")
public class ProductsTest extends BaseTest {

    @Test
    @Tag("smoke")
    @DisplayName("В каталоге 6 товаров")
    public void testCatalogHasSixProducts() {
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        assertEquals(6, inventoryPage.getProductsCount());
    }

    @Test
    @Tag("regression")
    @DisplayName("Сортировка по названию A -> Z")
    public void testSortByNameAscending() {
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        inventoryPage.sortBy("az");

        List<String> actual = inventoryPage.getProductNames();
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        assertEquals(expected, actual);
    }

    @Test
    @Tag("regression")
    @DisplayName("Сортировка по названию Z -> A")
    public void testSortByNameDescending() {
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        inventoryPage.sortBy("za");

        List<String> actual = inventoryPage.getProductNames();
        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        assertEquals(expected, actual);
    }

    @Test
    @Tag("regression")
    @DisplayName("Сортировка по цене: от дешёвых к дорогим")
    public void testSortByPriceLowToHigh() {
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        inventoryPage.sortBy("lohi");

        List<Double> actual = inventoryPage.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        assertEquals(expected, actual);
    }

    @Test
    @Tag("regression")
    @DisplayName("Сортировка по цене: от дорогих к дешёвым")
    public void testSortByPriceHighToLow() {
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        inventoryPage.sortBy("hilo");

        List<Double> actual = inventoryPage.getProductPrices();
        List<Double> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());
        assertEquals(expected, actual);
    }

    /** Известный баг BUG-001: у problem_user картинки одинаковые. Тест падает специально. */
    @Test
    @Tag("known-bug")
    @DisplayName("BUG-001: у problem_user у каждого товара своя картинка")
    public void testProductImagesAreUniqueForProblemUser() {
        loginPage.login(Users.PROBLEM, Users.PASSWORD);

        List<String> images = inventoryPage.getProductImageSources();
        Set<String> uniqueImages = new HashSet<>(images);
        assertEquals(images.size(), uniqueImages.size(), "У каждого товара должна быть своя картинка");
    }
}

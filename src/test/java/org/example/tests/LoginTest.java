package org.example.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.example.base.BaseTest;
import org.example.data.Users;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@Epic("saucedemo")
@Feature("Вход в систему")
public class LoginTest extends BaseTest {

    @Test
    @Tag("smoke")
    @DisplayName("Вход с верными данными")
    public void testWithCorrectData() {
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        assertTrue(inventoryPage.isCartVisible(), "После входа должен открыться каталог");
    }

    @Test
    @Tag("regression")
    @DisplayName("Вход без пароля")
    public void testWithoutPassword() {
        loginPage.login(Users.STANDARD, "");
        assertEquals("Epic sadface: Password is required", loginPage.getErrorText());
    }

    @Test
    @Tag("regression")
    @DisplayName("Вход с пустыми полями")
    public void testWithEmptyFields() {
        loginPage.login("", "");
        assertEquals("Epic sadface: Username is required", loginPage.getErrorText());
    }

   @Test
   @Tag("regression")
   @DisplayName("Вход с заблокированным пользователем")
   public void testLoginWithLockedUser(){
        loginPage.login(Users.LOCKED, Users.PASSWORD);
        String errorText = loginPage.getErrorText();
        assertTrue(errorText.contains("locked out"),"Ожидали сообщение про блокировку, а получили: " + errorText);
   }

    @Test
    @Tag("regression")
    @DisplayName("Вход с неверным паролем")
    public void testWithUncorrectPassword(){
        loginPage.login(Users.STANDARD,"wrong_password");
        String errorText = loginPage.getErrorText();
        assertTrue(errorText.contains("do not match"), "Ожидали сообщение про ошибку пароля, получили:"+errorText);
    }

    @ParameterizedTest(name="Вход под {0} -> каталог открывается")
    @CsvSource({"standard_user", "problem_user", "performance_glitch_user", "visual_user"})
    @Tag("regression")
    @DisplayName("Разные валидные пользователи могут войти")
    public void testValidUsersCanLogin(String username) {
        loginPage.login(username, Users.PASSWORD);
        assertTrue(inventoryPage.isCartVisible(), "Каталог должен открыться для " + username);
    }

    @Test
    @Tag("regression")
    @DisplayName("Выход из системы возвращает на страницу логина")
    public void testLogout(){
        loginPage.login(Users.STANDARD, Users.PASSWORD);
        inventoryPage.logout();
        assertTrue(loginPage.isLoginButtonVisible(),"После выхода должна быть страница логина");
    }

    @Test
    @Tag ("regression")
    @DisplayName("Каталог недоступен без входа")
    public void testInventoryNotAvailableWithoutLogin(){
        loginPage.openPath("inventory.html");
        String errorText = loginPage.getErrorText();
        assertTrue(errorText.contains("only access '/inventory.html' when you are logged in"), "Получили:"+errorText);
    }
}

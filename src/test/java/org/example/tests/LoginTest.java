package org.example.tests;
import org.example.factory.DriverFactory;
import org.example.pages.CartPage;
import org.example.pages.InventoryPage;
import org.example.pages.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;
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
    public void testWithCorrectData(){
        loginPage.login("standard_user", "secret_sauce");
        assertTrue(inventoryPage.isCartVisible(), "После входа должен открыться каталог");
    }

    @Test
    public void testWithoutPassword(){
        loginPage.login("standard_user","");

        String expectedError = "Epic sadface: Password is required";
        String actualError = loginPage.getErrorText();
        assertEquals(expectedError, actualError);
    }

    @Test
    public void testWithEmptyFields(){
        loginPage.login("","");

        String expectedError = "Epic sadface: Username is required";
        String actualError = loginPage.getErrorText();
        assertEquals(expectedError, actualError);
    }

    @Test
    public void testLoginWithLockedUser(){
        loginPage.login("locked_out_user","secret_sauce");

        String errorText = loginPage.getErrorText();
        assertTrue(errorText.contains("locked out"),"Ожидали сообщение про блокировку, а получили:" + errorText);
    }

    @Test
    public void testWithUncorrectPassword(){
        loginPage.login("standard_user","wrong_password");

        String errorText = loginPage.getErrorText();
        assertTrue(errorText.contains("do not match"), "ожидали сообщение про ошибку пароля, получили:"+errorText);
    }

    @AfterEach
    public void tearDown(){driver.quit();
    }
}

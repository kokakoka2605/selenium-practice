package org.example.base;

import org.example.config.Config;
import org.example.factory.DriverFactory;
import org.example.pages.*;
import org.example.utils.ScreenshotExtension;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

/** Общий родитель для всех тестов: запускает и закрывает браузер, создаёт страницы. */
@ExtendWith(ScreenshotExtension.class)
public abstract class BaseTest {

    public WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;
    protected CartPage cartPage;
    protected CheckoutPage checkoutPage;

    @BeforeEach
    public void setUp(){
        Config config = Config.getInstance();
        driver = DriverFactory.createDriver(config.getBrowser(), config.isHeadless());
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.open();
    }

    @AfterEach
    public void tearDown(){
        if(driver!= null){
            driver.quit();
        }
    }
}

package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SecondTest {
    public static void main(String[] args) throws InterruptedException{


        WebDriver driver = new ChromeDriver();

        driver.get("https://moodle1.ceiti.md/login/index.php?loginredirect=1");
        driver.manage().window().maximize();

        driver.findElement(By.id("username")).sendKeys("katia");
        Thread.sleep(1000);

        driver.findElement(By.id("password")).sendKeys("Katia");
        Thread.sleep(1000);

        driver.findElement(By.id("loginbtn")).click();
        Thread.sleep(1000);

        System.out.println("Тест выполнен");

        Thread.sleep(2000);
        driver.quit();

    }
}

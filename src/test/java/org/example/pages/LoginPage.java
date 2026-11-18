package org.example.pages;

import org.example.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver){
    super(driver);
    }

    public void open(){
        driver.get(Config.getInstance().getBaseUrl());
    }

    public void openPath(String path){
        driver.get(Config.getInstance().getBaseUrl()+path);
    }

    public void login (String username, String password){
        type(usernameField,username);
        type(passwordField, password);
        click(loginButton);
    }

    public String getErrorText(){
        return waitVisible(errorMessage).getText();
    }

    public boolean isLoginButtonVisible(){
        return waitVisible(loginButton).isDisplayed();
    }
}

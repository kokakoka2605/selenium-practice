package org.example.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

    public static WebDriver createDriver(String browser,boolean headless) {

        switch (browser.toLowerCase()) {
            case "chrome":
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito"); // режим инкогнито — password manager не активен
                options.addArguments("--disable-save-password-bubble");
                options.addArguments("--disable-features=PasswordLeakDetection,PasswordCheck,SafeBrowsingEnhanced");

                if(headless){
                    options.addArguments("--headless=new", "window-size=1920,1080",
                            "--no-sandbox", "--disable-dev-shm-usage");
                }

                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);
                options.setExperimentalOption("prefs", prefs);

                return new ChromeDriver(options);

            case "firefox":
                FirefoxOptions ffOptions = new FirefoxOptions();
                if(headless){
                    ffOptions.addArguments("-headless");
                }
                return new FirefoxDriver(ffOptions);
            default:
                throw new IllegalArgumentException("Unknown browser: " + browser);
        }
    }
}

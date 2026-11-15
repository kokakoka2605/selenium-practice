package org.example.config;

public class Config {
    private static Config instance;

    private final String baseUrl = "https://www.saucedemo.com/";
    private final int timeoutSeconds = 10;

    private Config(){
    }

    public static Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }

    public String getBaseUrl(){return baseUrl;}
    public int getTimeoutSeconds(){return timeoutSeconds;}
}

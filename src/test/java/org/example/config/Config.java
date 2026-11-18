package org.example.config;

/**
 * Настройки проекта. Можно менять из командной строки:
 * mvn test -Dbrowser=chrome -Dheadless=true -DbaseUrl=https://www.saucedemo.com/
 */
public class Config {
    private static Config instance;

    private final String baseUrl = System.getProperty("baseUrl","https://www.saucedemo.com/");
    private final String browser = System.getProperty("browser","chrome");
    private final boolean headless = Boolean.parseBoolean(System.getProperty("headless","false"));
    private final int timeoutSeconds = Integer.parseInt(System.getProperty("timeout","10"));

    private Config(){
    }

    public static Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }

    public String getBaseUrl(){return baseUrl;}
    public String getBrowser(){return browser;}
    public boolean isHeadless(){return headless;}
    public int getTimeoutSeconds(){return timeoutSeconds;}
}

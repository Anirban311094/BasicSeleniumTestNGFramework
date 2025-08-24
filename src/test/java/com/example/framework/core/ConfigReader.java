package com.example.framework.core;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConfigReader {
    private static final Properties props = new Properties();


    static {
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is != null) props.load(is);

        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties", e);
        }
    }


    public static Env get() {
        String baseUrl = System.getProperty("baseUrl", props.getProperty("baseUrl", "https://www.google.com"));
        String browser = System.getProperty("browser", props.getProperty("browser", "chrome"));
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", props.getProperty("headless", "true")));
        return new Env(baseUrl, browser, headless);
    }

}
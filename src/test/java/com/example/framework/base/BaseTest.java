package com.example.framework.base;


import com.example.framework.core.ConfigReader;
import com.example.framework.core.DriverFactory;
import com.example.framework.core.Env;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public abstract class BaseTest {
    protected Env env;
//    public String browser = ConfigReader.get().browser();

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        env = ConfigReader.get();
        DriverFactory.initDriver(env.browser());
        DriverFactory.getDriver().get(env.baseUrl());
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
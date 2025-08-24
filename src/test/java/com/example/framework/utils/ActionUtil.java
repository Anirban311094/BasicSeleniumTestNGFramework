package com.example.framework.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ActionUtil {
    private WebDriver driver;
    public ActionUtil(WebDriver driver){
        this.driver = driver;
    }

    public void selectByVisibleText(WebElement element, String visibleTextOption){
        Select select = new Select(element);
        select.selectByVisibleText(visibleTextOption);
    }
    public void selectByValue(WebElement element, String valueOption){
        Select select = new Select(element);
        select.selectByValue(valueOption);
    }
    public void selectByIndex(WebElement element, int index){
        Select select = new Select(element);
        select.selectByIndex(index);
    }
}

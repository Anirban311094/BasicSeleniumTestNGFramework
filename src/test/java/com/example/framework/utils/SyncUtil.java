package com.example.framework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

/**
 * Utility class for higher-level synchronization
 * like waiting for page loads, JS, jQuery, Angular etc.
 */
public class SyncUtil {

    private WebDriver driver;
    private static final int TIMEOUT = 5; // seconds

    public SyncUtil(WebDriver driver) {
        this.driver = driver;
    }

    /** Wait until document.readyState is 'complete' */
    public void waitForPageLoadComplete() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver -> ((JavascriptExecutor) driver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    /** Wait for jQuery active requests to finish */
    public void waitForJQueryLoad() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return window.jQuery != null && jQuery.active === 0"));
    }

    /** Wait for Angular HTTP requests to finish */
    public void waitForAngularLoad() {
        new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(20))
                .until(webDriver -> (Boolean) ((JavascriptExecutor) driver)
                        .executeScript("return (window.angular !== undefined) "
                                + "&& (angular.element(document).injector() !== undefined) "
                                + "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)"));
    }

    /** Custom sleep (only when absolutely needed) */
    public void hardWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Wait for document.readyState with FluentWait */
    public void fluentWaitForPageLoad(int timeoutSec, int pollingMillis) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver)
                                .executeScript("return document.readyState").equals("complete");
                    }
                });
    }

    /** Wait for jQuery to finish with FluentWait */
    public void fluentWaitForJQueryLoad(int timeoutSec, int pollingMillis) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (Boolean) ((JavascriptExecutor) driver)
                                .executeScript("return window.jQuery != null && jQuery.active === 0");
                    }
                });
    }

    /** Wait for Angular to finish with FluentWait */
    public void fluentWaitForAngularLoad(int timeoutSec, int pollingMillis) {
        new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSec))
                .pollingEvery(Duration.ofMillis(pollingMillis))
                .until(new Function<WebDriver, Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return (Boolean) ((JavascriptExecutor) driver)
                                .executeScript("return (window.angular !== undefined) "
                                        + "&& (angular.element(document).injector() !== undefined) "
                                        + "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)");
                    }
                });
    }


    /**
     * Check if element is displayed within 5 seconds
     */
    public boolean isElementDisplayed(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.visibilityOf(element));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if element is present in DOM within 5 seconds
     */
    public boolean isElementPresent(By locator) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return driver.findElement(locator) != null;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Check if element is clickable within 5 seconds
     */
    public boolean isElementClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

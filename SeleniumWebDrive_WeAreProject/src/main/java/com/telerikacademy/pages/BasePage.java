package com.telerikacademy.pages;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;

public abstract class BasePage {

    protected String url;
    protected static WebDriver driver;
    public static UserActions actions;
    protected static WebDriverWait wait;

    public BasePage(WebDriver driver, String urlKey) {
        String pageUrl = getConfigPropertyByKey(urlKey);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.url = pageUrl;
        actions = new UserActions();
    }

    public BasePage() {

    }

    public String getUrl() {
        return url;
    }

    public void navigateToPage() {
        this.driver.get(url);
    }

    public void navigateToPage(String url) {
        this.driver.get(url);
    }

    public void assertNavigatedUrl() {
        String currentUrl = driver.getCurrentUrl();
        LOGGER.info(format("Landed URL: %s", currentUrl));

        Assertions.assertTrue(currentUrl.contains(url), "Landed URL is not as expected. Actual URL: " + currentUrl + ". Expected URL: " + url);
    }

    public static void assertElementPresent(String locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(getUIMappingByKey(locator))));
        Assertions.assertNotNull(driver.findElement(By.xpath(getUIMappingByKey(locator))),
                format("Element with %s doesn't present.", locator));
    }


    public static void assertErrorPresent(String xpath, String message) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
        } catch (TimeoutException e) {
            Assertions.fail(message);
        }
    }

    public static WebElement[] findElements(String locator) {
        return driver.findElements(By.xpath(getUIMappingByKey(locator))).toArray(new WebElement[0]);
    }
}

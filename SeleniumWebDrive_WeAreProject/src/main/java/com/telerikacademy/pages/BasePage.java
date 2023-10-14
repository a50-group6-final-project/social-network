package com.telerikacademy.pages;

import com.telerikacademy.testframework.UserActions;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.telerikacademy.testframework.Utils.*;
import static java.lang.String.format;

public abstract class BasePage {

    protected String url;
    protected static WebDriver driver;
    public static UserActions actions;

    public BasePage(WebDriver driver, String urlKey) {
        String pageUrl = getConfigPropertyByKey(urlKey);
        this.driver = driver;
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
        Assertions.assertNotNull(driver.findElement(By.xpath(getUIMappingByKey(locator))),
                format("Element with %s doesn't present.", locator));
    }
}
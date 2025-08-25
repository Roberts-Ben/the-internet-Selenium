package com.br.theinternet.pages;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Pattern pattern;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        pattern = Pattern.compile("\\.(jpg|png|txt|json|xlsx|pdf|mp4|zip|py|exe|docx|jpeg|csv|sol|tmp|java|doc|class|bin|mov|xml)$", Pattern.CASE_INSENSITIVE);
    }

    public void navigateTo(String url)
    {
        driver.get(url);
    }

    // Find element(s)
    protected WebElement find(By locator)
    {
        return driver.findElement(locator);
    }

    protected List<WebElement> findAll(By locator)
    {
        return driver.findElements(locator);
    }

    // Click element
    protected void click(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Type text into input
    protected void type(By locator, String text)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    // Get element text
    protected String getText(By locator)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Check if element is visible
    protected boolean isDisplayed(By locator)
    {
        return driver.findElement(locator).isDisplayed();
    }

    // Check if element is enabled
    protected boolean isEnabled(By locator)
    {
        return driver.findElement(locator).isEnabled();
    }

    // Wait checks
    protected boolean waitForClickable(By locator)
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForNotClickable(By locator)
    {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForEnabled(By locator)
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForVisible(By locator)
    {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForInvisible(By locator)
    {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForStaleness(WebElement element)
    {
        try {
            wait.until(ExpectedConditions.stalenessOf(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check status code of target URL
    protected int getStatusCode(String URL)
    {
        return RestAssured.given().when().get(URL).statusCode();
    }

    public void refreshPage()
    {
        driver.navigate().refresh();
    }
}
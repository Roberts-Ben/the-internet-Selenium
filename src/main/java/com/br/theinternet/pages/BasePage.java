package com.br.theinternet.pages;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Check if element is enabled
    protected boolean isEnabled(WebElement element)
    {
        return element.isEnabled();
    }

    // Check if element is clickable
    protected WebElement waitForClickable(By locator)
    {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    protected void waitForNotClickable(By locator)
    {
        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
    }

    // Check status code of target URL
    protected int getStatusCode(String URL)
    {
        return RestAssured.given().when().get(URL).statusCode();
    }
}
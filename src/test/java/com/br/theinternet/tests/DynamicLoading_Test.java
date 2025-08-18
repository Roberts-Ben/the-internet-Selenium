package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicLoading_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/dynamic_loading", URL);
    }

    @Test
    public void verifyHiddenElement()
    {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");

        WebElement startButton = driver.findElement(By.xpath("//button"));
        WebElement hiddenElement = driver.findElement(By.id("finish"));

        assertFalse(hiddenElement.isDisplayed());

        startButton.click();

        wait.until(ExpectedConditions.visibilityOf(hiddenElement));
        assertTrue(hiddenElement.isDisplayed());
    }

    @Test
    public void verifyElementExistsAfterLoad()
    {
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");

        WebElement startButton = driver.findElement(By.xpath("//button"));
        List<WebElement> hiddenElement = driver.findElements(By.id("finish"));

        assertEquals(0, hiddenElement.size());

        startButton.click();

        WebElement finalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        assertTrue(finalElement.isDisplayed());
    }
}
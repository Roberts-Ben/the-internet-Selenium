package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class Inputs_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/inputs");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/inputs", URL);
    }

    @Test
    public void verifyValidInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        assertEquals("", inputField.getAttribute("value"));

        inputField.sendKeys("55");

        assertEquals("55", inputField.getAttribute("value"));
    }

    @Test
    public void verifyValidNegativeInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        assertEquals("", inputField.getAttribute("value"));

        inputField.sendKeys("-91");

        assertEquals("-91", inputField.getAttribute("value"));
    }

    @Test
    public void verifyInvalidInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        assertEquals("", inputField.getAttribute("value"));

        inputField.sendKeys("abc");

        assertEquals("", inputField.getAttribute("value"));
    }
}
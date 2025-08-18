package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShadowDOM_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/shadowdom");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/shadowdom", URL);
    }

    @Test
    public void verifyShadowDOM()
    {
        List<WebElement> shadowHosts = driver.findElements(By.cssSelector("my-paragraph"));

        // First root is a single element
        SearchContext firstShadowRoot = shadowHosts.getFirst().getShadowRoot();

        // Find child element inside the shadow DOM
        WebElement slotInsideFirstShadow = firstShadowRoot.findElement(By.name("my-text"));
        assertEquals("My default text", slotInsideFirstShadow.getText());

        // Find projected light DOM element
        WebElement slotInsideFirstLight = driver.findElement(By.cssSelector("my-paragraph > span[slot='my-text']"));
        assertEquals("Let's have some different text!", slotInsideFirstLight.getText());

        // Second root is a list of elements
        SearchContext secondShadowRoot = shadowHosts.getLast().getShadowRoot();

        // Find child element inside the shadow DOM
        WebElement slotInsideSecondShadow = secondShadowRoot.findElement(By.name("my-text"));
        assertEquals("My default text", slotInsideSecondShadow.getText());

        // Find projected light DOM elements
        List<WebElement> listInsideSecondLight = driver.findElements(By.cssSelector("my-paragraph > ul[slot='my-text'] > li"));
        assertEquals("Let's have some different text!", listInsideSecondLight.getFirst().getText());
        assertEquals("In a list!", listInsideSecondLight.getLast().getText());
    }
}
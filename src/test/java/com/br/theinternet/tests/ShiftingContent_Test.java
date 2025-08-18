package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftingContent_Test extends BaseTest
{
    JavascriptExecutor js;
    String baseURL;

    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/shifting_content");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/shifting_content", URL);

        baseURL = "https://the-internet.herokuapp.com";
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void verifyMenuElement()
    {
        WebElement menuLink = driver.findElement(By.xpath("//a[@href='/shifting_content/menu']"));

        menuLink.click();
        assertEquals("https://the-internet.herokuapp.com/shifting_content/menu", driver.getCurrentUrl());

        ConfirmButtonElements();

        WebElement shiftElementButton = driver.findElement(By.xpath("//a[@href='/shifting_content/menu?pixel_shift=100']"));

        shiftElementButton.click();

        ConfirmButtonElements();
    }

    @Test
    public void verifyImageElement()
    {
        WebElement imageLink = driver.findElement(By.xpath("//a[@href='/shifting_content/image']"));

        imageLink.click();
        assertEquals("https://the-internet.herokuapp.com/shifting_content/image", driver.getCurrentUrl());

        ConfirmImageElement();

        WebElement shiftElementButton = driver.findElement(By.xpath("//a[@href='/shifting_content/image?pixel_shift=100']"));

        shiftElementButton.click();

        ConfirmImageElement();
    }

    @Test
    public void verifyListElement()
    {
        WebElement listLink = driver.findElement(By.xpath("//a[@href='/shifting_content/list']"));

        listLink.click();
        assertEquals("https://the-internet.herokuapp.com/shifting_content/list", driver.getCurrentUrl());

        ConfirmListElement();

        driver.navigate().refresh();

        ConfirmListElement();
    }

    private void ConfirmButtonElements()
    {
        WebElement listRoot = driver.findElement(By.tagName("ul"));
        List<WebElement> buttons = listRoot.findElements(By.xpath(".//a[@href]"));

        for(WebElement button : buttons)
        {
            String newURL = button.getAttribute("href");
            js.executeScript("window.open()");

            Object[] windowHandles = driver.getWindowHandles().toArray();
            driver.switchTo().window((String) windowHandles[1]);

            driver.get(newURL);
            assertEquals(newURL, driver.getCurrentUrl());

            driver.close();

            driver.switchTo().window((String) windowHandles[0]);
        }
    }

    private void ConfirmImageElement()
    {
        WebElement image = driver.findElement(By.className("shift"));

        assertTrue(image.isDisplayed());
    }

    private void ConfirmListElement()
    {
        WebElement contentDiv = driver.findElement(By.cssSelector("div.large-6.columns.large-centered"));
        String innerHTML = contentDiv.getAttribute("innerHTML");

        String[] parts = innerHTML.split("<br\\s*/?>\\s*");
        List<String> lines = Arrays.stream(parts).map(String::trim).filter(s -> !s.isEmpty()).toList();
        String expectedString = "Important Information You're Looking For";

        boolean stringFound = false;

        for (String line : lines)
        {
            if(line.equals(expectedString))
            {
                stringFound = true;
                break;
            }
        }

        assertTrue(stringFound);
    }
}
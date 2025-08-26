package com.br.theinternet.tests;

import com.br.theinternet.pages.ShiftingContentPage;
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
    private ShiftingContentPage page;

    private static final String URL = "https://the-internet.herokuapp.com/shifting_content";
    private static final String baseURL = "https://the-internet.herokuapp.com";

    JavascriptExecutor js;


    @BeforeEach
    public void setup() throws Exception
    {
        page = new ShiftingContentPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());

        js = (JavascriptExecutor) driver;
    }

    @Test
    public void verifyMenuElement()
    {
        WebElement menuLink = driver.findElement(By.xpath("//a[@href='/shifting_content/menu']"));

        menuLink.click();
        assertEquals("https://the-internet.herokuapp.com/shifting_content/menu", page.getCurrentURL());

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
        assertEquals("https://the-internet.herokuapp.com/shifting_content/image", page.getCurrentURL());

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
        assertEquals("https://the-internet.herokuapp.com/shifting_content/list", page.getCurrentURL());

        ConfirmListElement();

        page.refreshPage();

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

            page.navigateTo(newURL);
            assertEquals(newURL, page.getCurrentURL());

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
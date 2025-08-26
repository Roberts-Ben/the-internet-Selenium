package com.br.theinternet.tests;

import com.br.theinternet.pages.JQueryUIMenuPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JQueryUIMenu_Test extends BaseTest
{
    private JQueryUIMenuPage page;

    private static final String URL = "https://the-internet.herokuapp.com/jqueryui/menu";

    List<WebElement> menuItems;
    WebElement menuRoot;
    Actions hoverAction;

    @BeforeEach
    public void setup() throws Exception
    {
        page = new JQueryUIMenuPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyJQueryUIMenu() throws InterruptedException
    {
        menuRoot = driver.findElement(By.id("ui-id-1"));
        menuItems = PopulateListElements();
        hoverAction = new Actions(driver);

        for (int i = 0; i < 8; i++)
        {
            ConfirmMenuState(i);
        }
    }

    private List<WebElement> PopulateListElements()
    {
        List<WebElement> listItems = new ArrayList<>();

        for(int i = 1; i < 9; i++)
        {
            WebElement item = driver.findElement(By.id("ui-id-" + i));
            listItems.add(item);
        }
        return  listItems;
    }

    private void ConfirmMenuState(int index) throws InterruptedException
    {
        switch (index) {
            case 0: // Disabled element
                assertTrue(Boolean.parseBoolean(menuItems.get(index).getAttribute("aria-disabled")));
                break;
            case 1: // Hidden element
                assertFalse(menuItems.get(index).isDisplayed());
                break;
            case 2: // Enabled element
                hoverMenuPath(2);
                assertTrue(menuItems.get(3).isDisplayed());
                ResetMenuState();
                break;
            case 3: // Downloads element
                hoverMenuPath(2, 3);
                assertTrue(menuItems.get(4).isDisplayed());
                ResetMenuState();
                break;
            case 4: case 5: case 6:
                hoverMenuPath(2, 3, index);
                verifyDownload(menuItems.get(index));
                ResetMenuState();
                break;
            case 7:
                hoverMenuPath(2, 7);
                menuItems.get(index).click();
                wait.until(ExpectedConditions.stalenessOf(menuRoot));
                assertEquals("https://the-internet.herokuapp.com/jqueryui", page.getCurrentURL());
                break;
        }
    }

    private void hoverMenuPath(int... indices) throws InterruptedException
    {
        for (int index : indices)
        {
            WebElement menuItem = menuItems.get(index);
            hoverAction.moveToElement(menuItem).pause(500);
            hoverAction.build().perform();
        }
    }

    private void ResetMenuState()
    {
        hoverAction.moveToElement(menuRoot).pause(500);
        hoverAction.build().perform();
    }

    private void verifyDownload(WebElement downloadItem) {
        WebElement downloadLinkElement = downloadItem.findElement(By.tagName("a"));
        String downloadLink = downloadLinkElement.getAttribute("href");
        String encodedFileName = downloadLink.substring(downloadLink.lastIndexOf('/') + 1);
        String fileName = URLDecoder.decode(encodedFileName, StandardCharsets.UTF_8);

        downloadLinkElement.click();

        assertTrue(isFileDownloaded(fileName, true));
    }
}
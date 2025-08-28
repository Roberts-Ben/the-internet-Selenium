package com.br.theinternet.tests;

import com.br.theinternet.pages.AddRemoveElementsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class AddRemoveElements_Test extends BaseTest
{
    private AddRemoveElementsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/add_remove_elements/";

    @ParameterizedTest(name = "verifyAddElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAddElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, AddRemoveElementsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.addElement();

        assertTrue(page.getDeleteButtons().getFirst().isDisplayed());
    }

    @ParameterizedTest(name = "verifyAddMultipleElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAddMultipleElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, AddRemoveElementsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        int elementsToAdd = 10;

        for(int i = 0; i < elementsToAdd; i++)
        {
            page.addElement();
        }

        assertEquals(elementsToAdd, page.getDeleteButtons().size());
    }

    @ParameterizedTest(name = "verifyDeleteElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDeleteElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, AddRemoveElementsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.addElement();

        List<WebElement> deleteButtons = page.getDeleteButtons();

        assertEquals(1, deleteButtons.size());

        page.deleteElement(deleteButtons.getFirst());

        assertEquals(0, page.getDeleteButtons().size());
    }

    @ParameterizedTest(name = "verifyDeleteAllElements: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDeleteAllElements(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, AddRemoveElementsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        int elementsToAdd = 10;

        for(int i = 0; i < elementsToAdd; i++)
        {
            page.addElement();
        }

        List<WebElement> deleteButtons = page.getDeleteButtons();

        assertEquals(elementsToAdd, deleteButtons.size());

        for(WebElement button : deleteButtons)
        {
            page.deleteElement(button);
        }

        assertEquals(0, page.getDeleteButtons().size());
    }

    @ParameterizedTest(name = "verifyDeleteRandomElements: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDeleteRandomElements(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, AddRemoveElementsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        int elementsToAdd = 10;
        int elementsToDelete = 3;

        for(int i = 0; i < elementsToAdd; i++)
        {
            page.addElement();
        }

        List<WebElement> deleteButtons = page.getDeleteButtons();

        assertEquals(elementsToAdd, deleteButtons.size());

        for(int i = 0; i < elementsToDelete; i++)
        {
            deleteButtons = page.getDeleteButtons();

            int randomIndex = ThreadLocalRandom.current().nextInt(0, deleteButtons.size());

            if(page.isElementStale(deleteButtons.get(randomIndex)))
            {
                i--; // Attempt another removal as this element was already removed
            }
            else
            {
                page.deleteElement(deleteButtons.get(randomIndex));
            }
        }

        assertEquals(elementsToAdd - elementsToDelete, page.getDeleteButtons().size());
    }
}
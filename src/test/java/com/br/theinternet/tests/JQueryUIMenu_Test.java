package com.br.theinternet.tests;

import com.br.theinternet.pages.JQueryUIMenuPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JQueryUIMenu_Test extends BaseTest
{
    private JQueryUIMenuPage page;

    private static final String URL = "https://the-internet.herokuapp.com/jqueryui/menu";
    private static final String baseURL = "https://the-internet.herokuapp.com/jqueryui";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, JQueryUIMenuPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyJQueryUIMenu() throws InterruptedException
    {
        // Disabled element
        assertTrue(page.isDisabled(0));

        // Hidden element
        assertFalse(page.isDisplayed(1));

        // Enabled element (shows next submenu)
        page.hoverMenuPath(2);
        assertTrue(page.isDisplayed(3));
        page.resetMenuState();

        // Downloads element (shows submenu)
        page.hoverMenuPath(2, 3);
        assertTrue(page.isDisplayed(4));
        page.resetMenuState();

        // Verify downloads work
        for (int i : new int[]{4, 5, 6})
        {
            page.hoverMenuPath(2, 3, i);
            assertTrue(isFileDownloaded(page.triggerDownload(i), true));
            page.resetMenuState();
        }

        // Navigate to "Back to JQuery UI"
        page.hoverMenuPath(2, 7);
        page.clickMenuItem(7);

        assertEquals(baseURL, page.getCurrentURL());
    }
}
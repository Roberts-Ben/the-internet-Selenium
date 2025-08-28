package com.br.theinternet.tests;

import com.br.theinternet.pages.FloatingMenuPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class FloatingMenu_Test extends BaseTest
{
    private FloatingMenuPage page;

    private static final String URL = "https://the-internet.herokuapp.com/floating_menu";

    @ParameterizedTest(name = "verifyFloatingHeader: {0}")
    @EnumSource(BrowserType.class)
    public void verifyFloatingHeader(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FloatingMenuPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("top: 0px;", page.getMenuPosition());

        page.scrollWindow();

        page.delay(100);

        assertNotEquals("top: 0px;", page.getMenuPosition());
    }
}
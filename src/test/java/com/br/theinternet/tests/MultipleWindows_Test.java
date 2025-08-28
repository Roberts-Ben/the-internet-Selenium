package com.br.theinternet.tests;

import com.br.theinternet.pages.MultipleWindowsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleWindows_Test extends BaseTest
{
    private MultipleWindowsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/windows";

    @ParameterizedTest(name = "verifyNewWindow: {0}")
    @EnumSource(BrowserType.class)
    public void verifyNewWindow(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, MultipleWindowsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickNewTabButton();

        page.switchWindow(1);

        assertEquals("New Window",page.getHeaderText());

        page.closeWindow();
        page.switchWindow(0);

        assertEquals(URL, page.getCurrentURL());
    }
}
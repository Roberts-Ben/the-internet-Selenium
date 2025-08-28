package com.br.theinternet.tests;

import com.br.theinternet.pages.ContextMenuPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class ContextMenu_Test extends BaseTest
{
    private ContextMenuPage page;

    private static final String URL = "https://the-internet.herokuapp.com/context_menu";

    @ParameterizedTest(name = "verifyContextMenuAppears: {0}")
    @EnumSource(BrowserType.class)
    public void verifyContextMenuAppears(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ContextMenuPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.rightClickHotSpot();

        page.acceptAlert();
    }
}
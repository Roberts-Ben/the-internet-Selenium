package com.br.theinternet.tests;

import com.br.theinternet.pages.ContextMenuPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContextMenu_Test extends BaseTest
{
    private ContextMenuPage page;

    private static final String URL = "https://the-internet.herokuapp.com/context_menu";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new ContextMenuPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyContextMenuAppears()
    {
        page.rightClickHotSpot();

        page.acceptAlert();
    }
}
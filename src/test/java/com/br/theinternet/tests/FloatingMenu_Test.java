package com.br.theinternet.tests;

import com.br.theinternet.pages.FloatingMenuPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FloatingMenu_Test extends BaseTest
{
    private FloatingMenuPage page;

    private static final String URL = "https://the-internet.herokuapp.com/floating_menu";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, FloatingMenuPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyFloatingHeader() throws InterruptedException
    {
        assertEquals("top: 0px;", page.getMenuPosition());

        page.scrollWindow();

        page.delay(100);

        assertNotEquals("top: 0px;", page.getMenuPosition());
    }
}
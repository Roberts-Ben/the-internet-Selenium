package com.br.theinternet.tests;

import com.br.theinternet.pages.MultipleWindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleWindows_Test extends BaseTest
{
    private MultipleWindowsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/windows";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, MultipleWindowsPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyNewWindow()
    {
        page.clickNewTabButton();

        page.switchWindow(1);

        assertEquals("New Window",page.getHeaderText());

        page.closeWindow();
        page.switchWindow(0);

        assertEquals(URL, page.getCurrentURL());
    }
}
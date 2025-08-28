package com.br.theinternet.tests;

import com.br.theinternet.pages.FramesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WYSIWYGEditor_Test extends BaseTest
{
    private FramesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/tinymce";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, FramesPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyEditor()
    {
        // Need to close the TinyMCE read-only warning before interacting with other elements
        page.clickCloseButton();

        assertEquals("Your content goes here.", page.getFrameContent());
    }
}
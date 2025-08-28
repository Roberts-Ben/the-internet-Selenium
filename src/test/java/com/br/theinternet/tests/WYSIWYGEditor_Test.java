package com.br.theinternet.tests;

import com.br.theinternet.pages.FramesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class WYSIWYGEditor_Test extends BaseTest
{
    private FramesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/tinymce";

    @ParameterizedTest(name = "verifyEditor: {0}")
    @EnumSource(BrowserType.class)
    public void verifyEditor(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FramesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        // Need to close the TinyMCE read-only warning before interacting with other elements
        page.clickCloseButton();

        assertEquals("Your content goes here.", page.getFrameContent());
    }
}
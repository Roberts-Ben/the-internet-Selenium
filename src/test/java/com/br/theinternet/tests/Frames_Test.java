package com.br.theinternet.tests;

import com.br.theinternet.pages.FramesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Frames_Test extends BaseTest
{
    private FramesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/frames";
    private static final String iFrameURL = "https://the-internet.herokuapp.com/iframe";
    private static final String nestedFrameURL = "https://the-internet.herokuapp.com/nested_frames";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, FramesPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyNestedFramesLink()
    {
        page.clickNestedFramesLink();

        assertEquals(nestedFrameURL, page.getCurrentURL());
    }

    @Test
    public void verifyiFrame()
    {
        page.navigateTo(iFrameURL);

        // Need to close the TinyMCE read-only warning before interacting with other elements
        page.clickCloseButton();

        assertEquals("Your content goes here.", page.getFrameContent());
    }
}
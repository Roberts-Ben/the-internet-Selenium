package com.br.theinternet.tests;

import com.br.theinternet.pages.FramesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Frames_Test extends BaseTest
{
    private FramesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/frames";
    private static final String iFrameURL = "https://the-internet.herokuapp.com/iframe";
    private static final String nestedFrameURL = "https://the-internet.herokuapp.com/nested_frames";

    @ParameterizedTest(name = "verifyNestedFramesLink: {0}")
    @EnumSource(BrowserType.class)
    public void verifyNestedFramesLink(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FramesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickNestedFramesLink();

        assertEquals(nestedFrameURL, page.getCurrentURL());
    }

    @ParameterizedTest(name = "verifyiFrame: {0}")
    @EnumSource(BrowserType.class)
    public void verifyiFrame(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FramesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.navigateTo(iFrameURL);

        // Need to close the TinyMCE read-only warning before interacting with other elements
        page.clickCloseButton();

        assertEquals("Your content goes here.", page.getFrameContent());
    }
}
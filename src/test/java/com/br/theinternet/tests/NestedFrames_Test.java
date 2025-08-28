package com.br.theinternet.tests;

import com.br.theinternet.pages.NestedFramesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class NestedFrames_Test extends BaseTest
{
    private NestedFramesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/nested_frames";

    @ParameterizedTest(name = "verifyNestedFrames: {0}")
    @EnumSource(BrowserType.class)
    public void verifyNestedFrames(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, NestedFramesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertTrue(page.verifyFrameText("MIDDLE", "frame-top", "frame-middle"));
        assertTrue(page.verifyFrameText("LEFT", "frame-top", "frame-left"));
        assertTrue(page.verifyFrameText("RIGHT", "frame-top", "frame-right"));
        assertTrue(page.verifyFrameText("BOTTOM", "frame-bottom"));
    }
}
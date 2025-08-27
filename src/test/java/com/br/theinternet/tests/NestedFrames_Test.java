package com.br.theinternet.tests;

import com.br.theinternet.pages.NestedFramesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NestedFrames_Test extends BaseTest
{
    private NestedFramesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/nested_frames";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new NestedFramesPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyNestedFrames()
    {
        assertTrue(page.verifyFrameText("MIDDLE", "frame-top", "frame-middle"));
        assertTrue(page.verifyFrameText("LEFT", "frame-top", "frame-left"));
        assertTrue(page.verifyFrameText("RIGHT", "frame-top", "frame-right"));
        assertTrue(page.verifyFrameText("BOTTOM", "frame-bottom"));
    }
}
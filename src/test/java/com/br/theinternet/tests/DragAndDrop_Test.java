package com.br.theinternet.tests;

import com.br.theinternet.pages.DragAndDropPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class DragAndDrop_Test extends BaseTest
{
    private DragAndDropPage page;

    private static final String URL = "https://the-internet.herokuapp.com/drag_and_drop";

    @ParameterizedTest(name = "verifyDragDropSwap: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDragDropSwap(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DragAndDropPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("A",page.getHeaderAText());
        assertEquals("B",page.getHeaderBText());

        page.dragAndDrop();

        // Re-assess which content is in each drag/rop space
        assertEquals("B",page.getHeaderAText());
        assertEquals("A",page.getHeaderBText());
    }
}



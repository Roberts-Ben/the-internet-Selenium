package com.br.theinternet.tests;

import com.br.theinternet.pages.DragAndDropPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class DragAndDrop_Test extends BaseTest
{
    private DragAndDropPage page;

    private static final String URL = "https://the-internet.herokuapp.com/drag_and_drop";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, DragAndDropPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyDragDropSwap() throws IOException
    {
        assertEquals("A",page.getHeaderAText());
        assertEquals("B",page.getHeaderBText());

        page.dragAndDrop();

        // Re-assess which content is in each drag/drop space
        assertEquals("B",page.getHeaderAText());
        assertEquals("A",page.getHeaderBText());
    }
}



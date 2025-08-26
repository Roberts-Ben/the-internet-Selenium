package com.br.theinternet.tests;

import com.br.theinternet.pages.DragAndDropPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DragAndDrop_Test extends BaseTest
{
    private DragAndDropPage page;

    private static final String URL = "https://the-internet.herokuapp.com/drag_and_drop";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new DragAndDropPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyDragDropSwap()
    {
        assertEquals("A",page.getHeaderAText());
        assertEquals("B",page.getHeaderBText());

        page.dragAndDrop();

        // Re-assess which content is in each drag/rop space
        assertEquals("B",page.getHeaderAText());
        assertEquals("A",page.getHeaderBText());
    }
}



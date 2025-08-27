package com.br.theinternet.tests;

import com.br.theinternet.pages.ExitIntentPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExitIntent_Test extends BaseTest
{
    private ExitIntentPage page;

    private static final String URL = "https://the-internet.herokuapp.com/exit_intent";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new ExitIntentPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyModal() throws InterruptedException
    {
        assertFalse(page.isModalVisible());

        // Force the modal as if we moved cursor outside the window
        page.forceModal();

        assertTrue(page.isModalInteractive());

        page.delay(100);

        page.closeModal();

        assertFalse(page.isModalVisible());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.ExitIntentPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class ExitIntent_Test extends BaseTest
{
    private ExitIntentPage page;

    private static final String URL = "https://the-internet.herokuapp.com/exit_intent";

    @ParameterizedTest(name = "verifyModal: {0}")
    @EnumSource(BrowserType.class)
    public void verifyModal(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ExitIntentPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertFalse(page.isModalVisible());

        // Force the modal as if we moved cursor outside the window
        page.forceModal();

        assertTrue(page.isModalInteractive());

        page.delay(100);

        page.closeModal();

        assertFalse(page.isModalVisible());
    }
}
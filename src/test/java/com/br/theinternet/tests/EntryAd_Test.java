package com.br.theinternet.tests;

import com.br.theinternet.pages.EntryAdPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EntryAd_Test extends BaseTest
{
    private EntryAdPage page;

    private static final String URL = "https://the-internet.herokuapp.com/entry_ad";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new EntryAdPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyAdOnFirstLoad() throws InterruptedException
    {
        assertFalse(page.isModalVisible());

        // Wait for modal to appear
        assertTrue(page.waitForModalVisible());

        Thread.sleep(2000);
        page.clickModal();

        assertTrue(page.waitForModalHidden());
    }

    @Test
    public void verifyAdOnlyAppearsOnce() throws InterruptedException
    {
        assertFalse(page.isModalVisible());

        // Wait for modal to appear
        assertTrue(page.waitForModalVisible());

        Thread.sleep(2000);
        page.clickModal();

        assertTrue(page.waitForModalHidden());

        Thread.sleep(2000); // Force a wait here otherwise we refresh too quickly and the modal triggers again
        page.refreshPage();

        assertFalse(page.waitForModalVisible());
    }

    @Test
    public void verifyAdAppearsAfterRefresh() throws InterruptedException
    {
        assertFalse(page.isModalVisible());

        // Wait for modal to appear
        assertTrue(page.waitForModalVisible());

        Thread.sleep(2000);
        page.clickModal();

        assertTrue(page.waitForModalHidden());

        Thread.sleep(2000);
        // Re-enable the popup
        page.clickResetButton();

        Thread.sleep(2000);
        page.refreshPage();

        assertFalse(page.isModalVisible());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.EntryAdPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class EntryAd_Test extends BaseTest
{
    private EntryAdPage page;

    private static final String URL = "https://the-internet.herokuapp.com/entry_ad";

    @ParameterizedTest(name = "verifyAdOnFirstLoad: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAdOnFirstLoad(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, EntryAdPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertFalse(page.isModalVisible());

        // Wait for modal to appear
        assertTrue(page.waitForModalVisible());

        Thread.sleep(2000);
        page.clickModal();

        assertTrue(page.waitForModalHidden());
    }

    @ParameterizedTest(name = "verifyAdOnlyAppearsOnce: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAdOnlyAppearsOnce(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, EntryAdPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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

    @ParameterizedTest(name = "verifyAdAppearsAfterRefresh: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAdAppearsAfterRefresh(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, EntryAdPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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
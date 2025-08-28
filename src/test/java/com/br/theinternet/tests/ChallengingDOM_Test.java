package com.br.theinternet.tests;

import com.br.theinternet.pages.ChallengingDOMPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengingDOM_Test extends BaseTest
{
    private ChallengingDOMPage page;

    private static final String URL = "https://the-internet.herokuapp.com/challenging_dom";

    @ParameterizedTest(name = "verifyButton: {0}")
    @EnumSource(BrowserType.class)
    public void verifyButton(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ChallengingDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertTrue(page.isButtonVisible());
        page.clickButton();
    }

    @ParameterizedTest(name = "verifyAlertButton: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAlertButton(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ChallengingDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertTrue(page.isAlertButtonVisible());
        page.clickAlertButton();
    }

    @ParameterizedTest(name = "verifySuccessButton: {0}")
    @EnumSource(BrowserType.class)
    public void verifySuccessButton(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ChallengingDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertTrue(page.isSuccessButtonVisible());
        page.clickSuccessButton();
    }

    @ParameterizedTest(name = "verifyTable: {0}")
    @EnumSource(BrowserType.class)
    public void verifyTable(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ChallengingDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        int tableSize = 10;

        assertTrue(page.isLoremHeaderVisible());

        page.clickFirstEdit();
        assertEquals(URL + "#edit", page.getCurrentURL());

        page.clickFirstDelete();
        assertEquals(URL + "#delete", page.getCurrentURL());

        for (int i = 0; i < tableSize; i++) {
            assertEquals("Iuvaret" + i, page.getLoremCellText(i));
        }
    }

    @ParameterizedTest(name = "verifyCanvas: {0}")
    @EnumSource(BrowserType.class)
    public void verifyCanvas(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ChallengingDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertTrue(page.isCanvasVisible());
    }
}
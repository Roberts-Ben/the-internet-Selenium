package com.br.theinternet.tests;

import com.br.theinternet.pages.ChallengingDOMPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengingDOM_Test extends BaseTest
{
    private ChallengingDOMPage page;

    private static final String URL = "https://the-internet.herokuapp.com/challenging_dom";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new ChallengingDOMPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyButton()
    {
        assertTrue(page.isButtonVisible());
        page.clickButton();
    }

    @Test
    public void verifyAlertButton()
    {
        assertTrue(page.isAlertButtonVisible());
        page.clickAlertButton();
    }

    @Test
    public void verifySuccessButton()
    {
        assertTrue(page.isSuccessButtonVisible());
        page.clickSuccessButton();
    }

    @Test
    public void verifyTable()
    {
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

    @Test
    public void verifyCanvas()
    {
        assertTrue(page.isCanvasVisible());
    }
}
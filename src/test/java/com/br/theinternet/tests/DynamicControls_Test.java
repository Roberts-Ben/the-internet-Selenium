package com.br.theinternet.tests;

import com.br.theinternet.pages.DynamicControlsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicControls_Test extends BaseTest
{
    private DynamicControlsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_controls";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new DynamicControlsPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyDynamicCheckbox()
    {
        assertEquals(1, page.getCheckboxSize());

        page.clickSwapCheckboxButton();

        assertTrue(page.isSuccessMessageVisible());
        assertEquals("It's gone!", page.getSuccessText());

        assertEquals(0, page.getCheckboxSize());

        page.clickSwapCheckboxButton();

        assertTrue(page.isSuccessMessageVisible());
        assertEquals("It's back!", page.getSuccessText());

        assertEquals(1, page.getCheckboxSize());
    }

    @Test
    public void verifyDynamicInput()
    {
        assertTrue(page.isInputFieldDisabled());

        page.clickSwapInputButton();

        assertTrue(page.isInputFieldEnabled());

        page.clickSwapInputButton();

        assertTrue(page.isInputFieldDisabled());
    }
}
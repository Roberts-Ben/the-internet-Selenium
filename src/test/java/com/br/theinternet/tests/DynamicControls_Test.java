package com.br.theinternet.tests;

import com.br.theinternet.pages.DynamicControlsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicControls_Test extends BaseTest
{
    private DynamicControlsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_controls";

    @ParameterizedTest(name = "verifyDynamicCheckbox: {0}")
@EnumSource(BrowserType.class)
    public void verifyDynamicCheckbox(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DynamicControlsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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

    @ParameterizedTest(name = "verifyDynamicInput: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDynamicInput(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DynamicControlsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertTrue(page.isInputFieldDisabled());

        page.clickSwapInputButton();

        assertTrue(page.isInputFieldEnabled());

        page.clickSwapInputButton();

        assertTrue(page.isInputFieldDisabled());
    }
}
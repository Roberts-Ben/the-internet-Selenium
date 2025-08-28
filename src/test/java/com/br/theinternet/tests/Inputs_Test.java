package com.br.theinternet.tests;

import com.br.theinternet.pages.InputsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Inputs_Test extends BaseTest
{
    private InputsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/inputs";

    @ParameterizedTest(name = "verifyValidInput: {0}")
    @EnumSource(BrowserType.class)
    public void verifyValidInput(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, InputsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("", page.getInputValue());

        page.inputValue("55");

        assertEquals("55", page.getInputValue());
    }

    @ParameterizedTest(name = "verifyValidNegativeInput: {0}")
    @EnumSource(BrowserType.class)
    public void verifyValidNegativeInput(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, InputsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("", page.getInputValue());

        page.inputValue("-91");

        assertEquals("-91", page.getInputValue());
    }

    @ParameterizedTest(name = ": {0}")
@EnumSource(BrowserType.class)
    public void verifyInvalidInput(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, InputsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("", page.getInputValue());

        page.inputValue("abc");

        assertEquals("", page.getInputValue());
    }
}
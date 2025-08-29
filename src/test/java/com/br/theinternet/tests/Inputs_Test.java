package com.br.theinternet.tests;

import com.br.theinternet.pages.InputsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Inputs_Test extends BaseTest
{
    private InputsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/inputs";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, InputsPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyValidInput()
    {
        assertEquals("", page.getInputValue());

        page.inputValue("55");

        assertEquals("55", page.getInputValue());
    }

    @Test
    public void verifyValidNegativeInput()
    {
        assertEquals("", page.getInputValue());

        page.inputValue("-91");

        assertEquals("-91", page.getInputValue());
    }

    @Test
    public void verifyInvalidInput()
    {
        assertEquals("", page.getInputValue());

        page.inputValue("abc");

        assertEquals("", page.getInputValue());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.DropdownPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Dropdown_Test extends BaseTest
{
    private DropdownPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dropdown";

    @ParameterizedTest(name = "verifyDropdown: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDropdown(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DropdownPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("Please select an option", page.getSelectedOption());

        page.selectByValue("1");

        assertEquals("Option 1", page.getSelectedOption());
    }
}
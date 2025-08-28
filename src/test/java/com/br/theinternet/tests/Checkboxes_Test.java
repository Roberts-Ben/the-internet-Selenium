package com.br.theinternet.tests;

import com.br.theinternet.pages.CheckboxesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Checkboxes_Test extends BaseTest
{
    private CheckboxesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/checkboxes";

    @ParameterizedTest(name = "verifyCheckboxes: {0}")
    @EnumSource(BrowserType.class)
    public void verifyCheckboxes(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, CheckboxesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertFalse(page.isFirstCheckboxSelected());
        assertTrue(page.isLastCheckboxSelected());

        page.clickFirstCheckbox();
        page.clickLastCheckbox();

        assertTrue(page.isFirstCheckboxSelected());
        assertFalse(page.isLastCheckboxSelected());
    }
}
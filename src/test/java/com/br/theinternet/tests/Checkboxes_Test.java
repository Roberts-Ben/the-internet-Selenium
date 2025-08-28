package com.br.theinternet.tests;

import com.br.theinternet.pages.CheckboxesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Checkboxes_Test extends BaseTest
{
    private CheckboxesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/checkboxes";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, CheckboxesPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyCheckboxes()
    {
        assertFalse(page.isFirstCheckboxSelected());
        assertTrue(page.isLastCheckboxSelected());

        page.clickFirstCheckbox();
        page.clickLastCheckbox();

        assertTrue(page.isFirstCheckboxSelected());
        assertFalse(page.isLastCheckboxSelected());
    }
}
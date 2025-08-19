package com.br.theinternet.tests;

import com.br.theinternet.pages.DropdownPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Dropdown_Test extends BaseTest
{
    private DropdownPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dropdown";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new DropdownPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyDropdown()
    {
        assertEquals("Please select an option", page.getSelectedOption());

        page.selectByValue("1");

        assertEquals("Option 1", page.getSelectedOption());
    }
}
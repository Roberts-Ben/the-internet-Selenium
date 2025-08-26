package com.br.theinternet.tests;

import com.br.theinternet.pages.HoversPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Hovers_Test extends BaseTest
{
    private HoversPage page;

    private static final String URL = "https://the-internet.herokuapp.com/hovers";
    private static final String userURL = "https://the-internet.herokuapp.com/hovers/users/";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new HoversPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyHover()
    {
        // Verify all hidden hover info is hidden
        for (int i = 0; i < page.getHiddenElements().size(); i++)
        {
            assertFalse(page.isElementVisible(i));
        }

        // Loop through each hover element
        for(int i = 0 ; i < page.getHoverElements().size(); i++)
        {
            // Hover over it and confirm only the hidden info attached to that element is visible
            page.moveToElement(i);

            // Verify only the hovered element’s hidden info is visible
            for (int j = 0; j < page.getHiddenElements().size(); j++)
            {
                if (i == j)
                {
                    assertTrue(page.isElementVisible(j));
                }
                else
                {
                    assertFalse(page.isElementVisible(j));
                }
            }
            int statusCode = page.verifyValidLink(userURL, i);
            assertTrue(statusCode == 200 || statusCode == 404,
                    "Unexpected status code. Found: " + statusCode
            );
        }
    }
}
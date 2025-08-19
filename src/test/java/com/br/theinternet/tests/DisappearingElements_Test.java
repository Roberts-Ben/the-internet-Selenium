package com.br.theinternet.tests;

import com.br.theinternet.pages.DisappearingElementsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DisappearingElements_Test extends BaseTest
{
    private DisappearingElementsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/disappearing_elements";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new DisappearingElementsPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyDisappearingElements()
    {
        boolean verified5Elements = false;
        boolean verified4Elements = false;

        while(!verified4Elements || !verified5Elements)
        {
            int listSize = page.getMenuSize();

            if(listSize == 5 && !verified5Elements)
            {
                assertEquals(5, listSize);
                verified5Elements = true;
            }
            else if(listSize == 4 && !verified4Elements)
            {
                assertEquals(4, listSize);
                verified4Elements = true;
            }

            driver.navigate().refresh();
        }
    }
}
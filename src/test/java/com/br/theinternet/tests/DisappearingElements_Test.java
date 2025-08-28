package com.br.theinternet.tests;

import com.br.theinternet.pages.DisappearingElementsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class DisappearingElements_Test extends BaseTest
{
    private DisappearingElementsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/disappearing_elements";

    @ParameterizedTest(name = "verifyDisappearingElements: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDisappearingElements(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DisappearingElementsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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

            page.refreshPage();
        }
    }
}
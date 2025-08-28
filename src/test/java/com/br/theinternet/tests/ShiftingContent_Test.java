package com.br.theinternet.tests;

import com.br.theinternet.pages.ShiftingContentPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftingContent_Test extends BaseTest
{
    private ShiftingContentPage page;

    private static final String URL = "https://the-internet.herokuapp.com/shifting_content";
    
    private static final String menuURL = "https://the-internet.herokuapp.com/shifting_content/menu";
    private static final String imageURL = "https://the-internet.herokuapp.com/shifting_content/image";
    private static final String listURL = "https://the-internet.herokuapp.com/shifting_content/list";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, ShiftingContentPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyMenuElement()
    {
        page.clickMenuLink();
        assertEquals(menuURL, page.getCurrentURL());

        confirmButtonElements();

        page.clickMenuShift();

        confirmButtonElements();
    }

    @Test
    public void verifyImageElement()
    {
        page.clickImageLink();
        assertEquals(imageURL, page.getCurrentURL());

        assertTrue(page.isImageDisplayed());

        page.clickImageShift();

        assertTrue(page.isImageDisplayed());
    }

    @Test
    public void verifyListElement()
    {
        page.clickListLink();
        assertEquals(listURL, page.getCurrentURL());

        confirmListElement();

        page.refreshPage();

        confirmListElement();
    }

    private void confirmButtonElements()
    {
        List<String> menuLinks = page.getMenuLinks();

        for (String newURL : menuLinks)
        {
            // Switch to new tab
            page.openWindowAndNavigate(newURL);

            assertEquals(newURL, page.getCurrentURL());

            // Close and return
            page.closeWindowAndNavigate();
        }
    }

    private void confirmListElement()
    {
        List<String> lines = page.getListItems();
        String expected = "Important Information You're Looking For";

        assertTrue(lines.stream().anyMatch(line -> line.equals(expected)));
    }
}
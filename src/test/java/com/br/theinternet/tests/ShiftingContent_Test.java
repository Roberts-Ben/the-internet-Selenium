package com.br.theinternet.tests;

import com.br.theinternet.pages.ShiftingContentPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShiftingContent_Test extends BaseTest
{
    private ShiftingContentPage page;

    private static final String URL = "https://the-internet.herokuapp.com/shifting_content";

    private static final String baseURL = "https://the-internet.herokuapp.com";
    private static final String menuURL = "https://the-internet.herokuapp.com/shifting_content/menu";
    private static final String imageURL = "https://the-internet.herokuapp.com/shifting_content/image";
    private static final String listURL = "https://the-internet.herokuapp.com/shifting_content/list";

    @ParameterizedTest(name = "verifyMenuElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyMenuElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ShiftingContentPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickMenuLink();
        assertEquals(menuURL, page.getCurrentURL());

        confirmButtonElements();

        page.clickMenuShift();

        confirmButtonElements();
    }

    @ParameterizedTest(name = "verifyImageElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyImageElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ShiftingContentPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickImageLink();
        assertEquals(imageURL, page.getCurrentURL());

        assertTrue(page.isImageDisplayed());

        page.clickImageShift();

        assertTrue(page.isImageDisplayed());
    }

    @ParameterizedTest(name = "verifyListElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyListElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ShiftingContentPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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
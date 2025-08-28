package com.br.theinternet.tests;

import com.br.theinternet.pages.LargeAndDeepDOMPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LargeandDeepDOM_Test extends BaseTest
{
    private LargeAndDeepDOMPage page;

    private static final String URL = "https://the-internet.herokuapp.com/large";

    @ParameterizedTest(name = "verifyDeepSiblings: {0}")
    @EnumSource(BrowserType.class)
    public void verifyDeepSiblings(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, LargeAndDeepDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("No siblings", page.getNoSiblingsText());

        List<WebElement> siblingsContent = page.getSiblingsContent();
        int totalSteps = 3;

        for (int i = 0; i < siblingsContent.size(); i++)
        {
            int row = (i / totalSteps) + 1;
            int col = (i % totalSteps) + 1;

            String expectedContent = row + "." + col;

            page.scrollIntoView(siblingsContent.get(i));

            assertEquals(expectedContent, page.getDirectText(siblingsContent.get(i)), "Mismatch at cell index " + i);
        }
    }

    @ParameterizedTest(name = "verifyLargeTable: {0}")
    @EnumSource(BrowserType.class)
    public void verifyLargeTable(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, LargeAndDeepDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        List<WebElement> tableContent = page.getLargeTableContent();
        int totalCols = 50;

        for (int i = 0; i < tableContent.size(); i++)
        {
            int row = (i / totalCols) + 1;
            int col = (i % totalCols) + 1;

            String expectedContent = row + "." + col;

            page.scrollIntoView(tableContent.get(i));

            assertEquals(expectedContent, page.getDirectText(tableContent.get(i)), "Mismatch at cell index " + i);
        }
    }
}
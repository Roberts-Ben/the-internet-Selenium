package com.br.theinternet.tests;

import com.br.theinternet.pages.LargeAndDeepDOMPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LargeandDeepDOM_Test extends BaseTest
{
    private LargeAndDeepDOMPage page;

    private static final String URL = "https://the-internet.herokuapp.com/large";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new LargeAndDeepDOMPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyDeepSiblings()
    {
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

    @Test
    public void verifyLargeTable()
    {
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
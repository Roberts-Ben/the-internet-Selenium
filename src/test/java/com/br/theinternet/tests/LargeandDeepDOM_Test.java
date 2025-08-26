package com.br.theinternet.tests;

import com.br.theinternet.pages.LargeAndDeepDOMPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LargeandDeepDOM_Test extends BaseTest
{
    private LargeAndDeepDOMPage page;

    private static final String URL = "https://the-internet.herokuapp.com/large";

    JavascriptExecutor js;

    @BeforeEach
    public void setup() throws Exception
    {
        page = new LargeAndDeepDOMPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());

        js = (JavascriptExecutor) driver;
    }

    @Test
    public void verifyDeepSiblings()
    {
        WebElement noSiblingsParent = driver.findElement(By.className("parent"));
        WebElement noSiblingsContent = noSiblingsParent.findElement(By.xpath(".//div[@id='no-siblings']"));

        assertEquals("No siblings", noSiblingsContent.getText());

        WebElement siblingsParent = driver.findElement(By.id("siblings"));
        List<WebElement> siblingsContent = siblingsParent.findElements(By.xpath("//div[starts-with(@id, 'sibling-')]"));

        int totalSteps = 3;

        for (int i = 0; i < siblingsContent.size(); i++)
        {
            String currentContent = getDirectText(siblingsContent.get(i));

            int row = (i / totalSteps) + 1;
            int col = (i % totalSteps) + 1;

            String expectedContent = row + "." + col;

            js.executeScript("arguments[0].scrollIntoView();", siblingsContent.get(i));

            assertEquals(expectedContent, currentContent);
        }
    }

    @Test
    public void verifyLargeTable()
    {
        WebElement largeTable = driver.findElement(By.id("large-table"));
        List<WebElement> tableCells = largeTable.findElements(By.xpath(".//tbody/tr/td"));

        int totalCols = 50;

        for (int i = 0; i < tableCells.size(); i++)
        {
            String currentContent = tableCells.get(i).getText();

            int row = (i / totalCols) + 1;
            int col = (i % totalCols) + 1;

            String expectedContent = row + "." + col;

            js.executeScript("arguments[0].scrollIntoView();", tableCells.get(i));

            assertEquals(expectedContent, currentContent, "Mismatch at cell index " + i);
        }
    }

    private String getDirectText(WebElement element)
    {
        String content = element.getText();
        int index = content.indexOf('\n');

        String subString;

        if(index != -1)
        {
            subString = content.substring(0 , index);
        }
        else
        {
            subString = content;
        }

        return subString;
    }
}
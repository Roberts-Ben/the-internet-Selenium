package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class SortableDataTablesPage extends BasePage
{
    private By tableBodyBy = By.cssSelector("tbody tr");
    private By tabletdBy = By.tagName("td");
    private By actionBy = By.tagName("a");
    private By headerBy = By.className("header");

    public SortableDataTablesPage(WebDriver driver)
    {
        super(driver);
    }

    private By getTableLocator(String tableId)
    {
        return By.id(tableId);
    }

    public List<WebElement> getRows(String tableId)
    {
        return find(getTableLocator(tableId)).findElements(tableBodyBy);
    }

    public List<String> getColumnContentByIndex(String tableId, int columnIndex)
    {
        return getRows(tableId).stream()
                .map(r -> r.findElements(tabletdBy).get(columnIndex).getText())
                .collect(Collectors.toList());
    }

    public List<String> getColumnContentByID(String tableId, String columnClass)
    {
        return getRows(tableId).stream()
                .map(r -> r.findElement(By.cssSelector("td." + columnClass)).getText())
                .collect(Collectors.toList());
    }

    public void clickEdit(String tableId, int rowIndex)
    {
        WebElement row = getRows(tableId).get(rowIndex);
        row.findElements(actionBy).getFirst().click();
    }

    public void clickDelete(String tableId, int rowIndex)
    {
        WebElement row = getRows(tableId).get(rowIndex);
        row.findElements(actionBy).getLast().click();
    }

    public void clickColumnHeader(String tableId, int index)
    {
        List<WebElement> header = find(getTableLocator(tableId)).findElements(headerBy);
        header.get(index).click();
    }
}

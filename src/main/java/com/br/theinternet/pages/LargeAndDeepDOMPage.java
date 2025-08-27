package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class LargeAndDeepDOMPage extends BasePage
{
    private By noSiblingsParentBy = By.className("parent");
    private By noSiblingsContentBy = By.id("no-siblings");
    private By siblingsParentBy = By.id("siblings");
    private By siblingsContentBy = By.xpath(".//div[starts-with(@id, 'sibling-')]");
    private By largeTableBy = By.id("large-table");
    private By tableCellsBy = By.xpath(".//tbody/tr/td");

    private JavascriptExecutor js;

    public LargeAndDeepDOMPage(WebDriver driver)
    {
        super(driver);
        js = (JavascriptExecutor) driver;
    }

    public String getNoSiblingsText()
    {
        WebElement parent = find(noSiblingsParentBy);
        return parent.findElement(noSiblingsContentBy).getText();
    }

    public List<WebElement> getSiblingsContent()
    {
        WebElement parent = find(siblingsParentBy);
        return parent.findElements(siblingsContentBy);
    }

    public List<WebElement> getLargeTableContent()
    {
        WebElement table = find(largeTableBy);
        return table.findElements(tableCellsBy);
    }

    public String getDirectText(WebElement element)
    {
        String content = element.getText();
        int index = content.indexOf('\n');

        return (index != -1) ? content.substring(0, index) : content;
    }

    public void scrollIntoView(WebElement element)
    {
        js.executeScript("arguments[0].scrollIntoView();", element);
    }
}

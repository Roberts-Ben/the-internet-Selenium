package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DragAndDropPage extends BasePage
{
    private By dragColumnABy = By.id("column-a");
    private By dragColumnBBy = By.id("column-b");
    private By dragHeaderABy = By.cssSelector("#column-a > header");
    private By dragHeaderBBy = By.cssSelector("#column-b > header");

    public DragAndDropPage(WebDriver driver)
    {
        super(driver);
    }

    public String getHeaderAText()
    {
        return getText(dragHeaderABy);
    }

    public String getHeaderBText()
    {
        return getText(dragHeaderBBy);
    }

    public void dragAndDrop()
    {
        WebElement colA = find(dragColumnABy);
        WebElement colB = find(dragColumnBBy);

        new Actions(driver).dragAndDrop(colA, colB).build().perform();
    }
}

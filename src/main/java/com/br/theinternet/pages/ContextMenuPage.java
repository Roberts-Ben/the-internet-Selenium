package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ContextMenuPage extends BasePage
{
    private By contextMenuAreaBy = By.id("hot-spot");

    public ContextMenuPage(WebDriver driver)
    {
        super(driver);
    }

    public void rightClickHotSpot()
    {
        WebElement contextMenuArea = find(contextMenuAreaBy);
        new Actions(driver).contextClick(contextMenuArea).perform();
    }
}

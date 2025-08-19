package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddRemoveElementsPage extends BasePage
{
    private By addButtonBy = By.cssSelector("button[onclick='addElement()']");
    private By deleteButtonsBy = By.cssSelector("button[onclick='deleteElement()']");

    public AddRemoveElementsPage(WebDriver driver)
    {
        super(driver);
    }

    public void addElement()
    {
        click(addButtonBy);
    }

    public void deleteElement(WebElement element)
    {
        element.click();
    }

    public List<WebElement> getDeleteButtons()
    {
        return findAll(deleteButtonsBy);
    }

    public boolean isElementStale(WebElement element)
    {
        return Boolean.TRUE.equals(ExpectedConditions.stalenessOf(element).apply(driver));
    }
}

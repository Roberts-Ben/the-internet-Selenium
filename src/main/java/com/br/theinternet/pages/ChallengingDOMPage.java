package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ChallengingDOMPage extends BasePage
{
    private By buttonBy = By.xpath("//a[@class='button']");
    private By alertButtonBy = By.xpath("//a[@class='button alert']");
    private By successButtonBy = By.xpath("//a[@class='button success']");

    private By tableLoremHeaderBy = By.xpath("//th[contains(text(),'Lorem')]");
    private By tableLoremBy = By.xpath("//td[contains(text(),'Iuvaret')]");
    private By tableEditActionBy = By.xpath("//a[text()='edit']");
    private By tableDeleteActionvBy = By.xpath("//a[text()='delete']");

    private By canvasBy = By.id("canvas");

    public ChallengingDOMPage(WebDriver driver)
    {
        super(driver);
    }

    // Buttons
    public void clickButton()
    {
        click(buttonBy);
    }

    public void clickAlertButton()
    {
        click(alertButtonBy);
    }

    public void clickSuccessButton()
    {
        click(successButtonBy);
    }

    public boolean isButtonVisible()
    {
        return isDisplayed(buttonBy);
    }

    public boolean isAlertButtonVisible()
    {
        return isDisplayed(alertButtonBy);
    }

    public boolean isSuccessButtonVisible()
    {
        return isDisplayed(successButtonBy);
    }

    // Table
    public boolean isLoremHeaderVisible()
    {
        return isDisplayed(tableLoremHeaderBy);
    }

    public String getLoremCellText(int index)
    {
        return findAll(tableLoremBy).get(index).getText();
    }

    public void clickFirstEdit()
    {
        findAll(tableEditActionBy).getFirst().click();
    }

    public void clickFirstDelete()
    {
        findAll(tableDeleteActionvBy).getFirst().click();
    }

    // Canvas
    public boolean isCanvasVisible()
    {
        return isDisplayed(canvasBy);
    }
}

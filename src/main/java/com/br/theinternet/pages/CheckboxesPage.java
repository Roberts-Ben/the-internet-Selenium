package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxesPage extends BasePage
{

    private By checkboxesBy = By.cssSelector("input");

    public CheckboxesPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isFirstCheckboxSelected()
    {
        return findAll(checkboxesBy).getFirst().isSelected();
    }

    public boolean isLastCheckboxSelected()
    {
        return findAll(checkboxesBy).getLast().isSelected();
    }

    public void clickFirstCheckbox()
    {
        findAll(checkboxesBy).getFirst().click();
    }

    public void clickLastCheckbox()
    {
        findAll(checkboxesBy).getLast().click();
    }
}

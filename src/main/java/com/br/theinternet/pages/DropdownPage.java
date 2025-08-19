package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownPage extends BasePage
{
    private By dropdownBy = By.id("dropdown");

    public DropdownPage(WebDriver driver)
    {
        super(driver);
    }

    private Select getDropdown()
    {
        return new Select(find(dropdownBy));
    }

    public String getSelectedOption()
    {
        WebElement selectedOption = getDropdown().getFirstSelectedOption();
        return selectedOption.getText();
    }

    public void selectByValue(String value)
    {
        getDropdown().selectByValue(value);
    }
}

package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicControlsPage extends BasePage
{
    private By checkboxBy = By.id("checkbox");
    private By swapCheckboxButtonBy = By.xpath("//button[@onclick='swapCheckbox()']");
    private By successMessageBy = By.id("message");
    private By inputFieldBy = By.xpath("//input[@type='text']");
    private By swapInputButtonBy = By.xpath("//button[@onclick='swapInput()']");

    public DynamicControlsPage(WebDriver driver)
    {
        super(driver);
    }

    public int getCheckboxSize()
    {
        return findAll(checkboxBy).size();
    }

    public void clickSwapCheckboxButton()
    {
        click(swapCheckboxButtonBy);
    }

    public void clickSwapInputButton()
    {
        click(swapInputButtonBy);
    }

    public boolean isSuccessMessageVisible()
    {
        return waitForVisible(successMessageBy);
    }

    public boolean isInputFieldEnabled()
    {
        return waitForEnabled(inputFieldBy);
    }

    public boolean isInputFieldDisabled()
    {
        waitForNotClickable(inputFieldBy);
        return !find(inputFieldBy).isEnabled();
    }

    public String getSuccessText()
    {
        return getText(successMessageBy);
    }
}

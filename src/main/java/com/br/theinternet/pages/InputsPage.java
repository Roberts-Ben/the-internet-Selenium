package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputsPage extends BasePage
{
    public By inputFieldBy = By.xpath("//input[@type='number']");

    public InputsPage(WebDriver driver)
    {
        super(driver);
    }

    public String getInputValue()
    {
        return getAttribute(inputFieldBy, "value");
    }

    public void inputValue(String input)
    {
        type(inputFieldBy, input);
    }
}

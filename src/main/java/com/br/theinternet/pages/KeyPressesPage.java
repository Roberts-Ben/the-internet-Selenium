package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class KeyPressesPage extends BasePage
{
    private By inputFieldBy = By.id("target");
    private By resultLabelBy = By.id("result");

    public KeyPressesPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isResultVisible()
    {
        return isDisplayed(resultLabelBy);
    }

    public String getResult()
    {
        return getText(resultLabelBy);
    }

    public void sendInput(Keys key)
    {
        sendKeys(inputFieldBy, key);
    }

    public void waitForPageStaleness()
    {
        waitForStaleness(find(resultLabelBy));
    }
}

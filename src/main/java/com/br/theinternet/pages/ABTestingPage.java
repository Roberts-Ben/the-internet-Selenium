package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ABTestingPage extends BasePage
{
    private By headerBy = By.cssSelector("h3");

    public ABTestingPage(WebDriver driver)
    {
        super(driver);
    }

    public String getHeaderText()
    {
        return getText(headerBy);
    }
}
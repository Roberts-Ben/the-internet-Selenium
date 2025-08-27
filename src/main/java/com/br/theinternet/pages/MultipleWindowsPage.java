package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleWindowsPage extends BasePage
{
    private By newTabButtonBy = By.xpath("//a[@href='/windows/new']");
    private By headerBy = By.cssSelector("h3");

    public MultipleWindowsPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickNewTabButton()
    {
        click(newTabButtonBy);
    }

    public String getHeaderText()
    {
        return getText(headerBy);
    }
}

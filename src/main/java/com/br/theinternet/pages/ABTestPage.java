package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ABTestPage
{
    protected WebDriver driver;

    private By headerBy = By.cssSelector("h3");

    public ABTestPage(WebDriver driver)
    {
        this.driver = driver;
        if (!driver.getCurrentUrl().equals("https://the-internet.herokuapp.com/abtest"))
        {
          throw new IllegalStateException("Incorrect page," +" current page is: " + driver.getCurrentUrl());
        }
    }

    public String getHeaderText()
    {
        return driver.findElement(headerBy).getText();
    }
}
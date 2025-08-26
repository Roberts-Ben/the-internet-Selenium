package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FloatingMenuPage extends BasePage
{
    private By floatingMenuBy = By.id("menu");

    public FloatingMenuPage(WebDriver driver)
    {
        super(driver);
    }

    public void scrollWindow()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public String getMenuPosition()
    {
        return find(floatingMenuBy).getAttribute("style");
    }
}

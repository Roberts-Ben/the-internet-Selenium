package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class FloatingMenuPage extends BasePage
{
    private By floatingMenuBy = By.id("menu");

    int scrollPauseTime = 100; // ms

    public FloatingMenuPage(WebDriver driver)
    {
        super(driver);
    }

    public void scrollWindow()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public void delay() throws InterruptedException
    {
        Thread.sleep(scrollPauseTime);
    }

    public String getMenuPosition()
    {
        return find(floatingMenuBy).getAttribute("style");
    }
}

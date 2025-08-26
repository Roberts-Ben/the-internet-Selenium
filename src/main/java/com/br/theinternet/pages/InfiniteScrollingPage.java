package com.br.theinternet.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class InfiniteScrollingPage extends BasePage
{
    private JavascriptExecutor js;

    public InfiniteScrollingPage(WebDriver driver)
    {
        super(driver);
        js = (JavascriptExecutor) driver;
    }

    public void scrollWindow()
    {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public long getScreenHeight()
    {
        return (long)js.executeScript("return document.body.scrollHeight");
    }
}

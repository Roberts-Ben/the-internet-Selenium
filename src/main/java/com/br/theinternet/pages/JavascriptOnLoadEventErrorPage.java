package com.br.theinternet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.util.List;

public class JavascriptOnLoadEventErrorPage extends BasePage
{
    public JavascriptOnLoadEventErrorPage(WebDriver driver)
    {
        super(driver);
    }

    public List<LogEntry>  getBrowserLogs()
    {
        return driver.manage().logs().get(LogType.BROWSER).getAll();
    }
}

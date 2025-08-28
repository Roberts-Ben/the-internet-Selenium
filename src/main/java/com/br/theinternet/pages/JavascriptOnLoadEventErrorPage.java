package com.br.theinternet.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;

import java.util.List;

public class JavascriptOnLoadEventErrorPage extends BasePage
{
    public JavascriptOnLoadEventErrorPage(WebDriver driver)
    {
        super(driver);
    }

    public List<LogEntry> getBrowserLogs()
    {
        return getLogs();
    }
}

package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TyposPage extends BasePage
{
    private By contentBy = By.xpath("//*[@id=\"content\"]/div/p[2]");

    public TyposPage(WebDriver driver)
    {
        super(driver);
    }

    public String getContent()
    {
        return getText(contentBy);
    }
}

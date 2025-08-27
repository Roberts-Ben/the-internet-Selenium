package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RedirectLinkPage extends BasePage
{
    private By redirectLinkBy = By.id("redirect");

    public RedirectLinkPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickRedirectLink()
    {
        click(redirectLinkBy);
    }
}

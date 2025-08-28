package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormAuthPage extends BasePage
{
    private By loginButtonBy = By.className("fa-sign-in");
    private By logoutButtonBy = By.cssSelector("a[href*='/logout']");
    private By usernameFieldBy = By.id("username");
    private By passwordFieldBy = By.id("password");
    private By alertBy = By.cssSelector("div#flash");

    public FormAuthPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickLogin()
    {
        click(loginButtonBy);
    }

    public void clickLogout()
    {
        click(logoutButtonBy);
    }

    public void inputCredentials(String username, String password)
    {
        type(usernameFieldBy, username);
        type(passwordFieldBy, password);
    }

    public String getDataAlertText()
    {
        return getText(alertBy);
    }
}

package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage extends BasePage
{
    private By inputFieldBy = By.id("email");
    private By retrieveButtonBy = By.id("form_submit");
    private By pageRefreshHeaderBy = By.cssSelector("h1");

    public ForgotPasswordPage(WebDriver driver)
    {
        super(driver);
    }

    public void inputEmail(String email)
    {
        type(inputFieldBy, email);
    }

    public String getInputFieldValue()
    {
        return getAttribute(inputFieldBy, "value");
    }

    public String getHeaderText()
    {
        return getText(pageRefreshHeaderBy);
    }

    public void clickRetrieve()
    {
        click(retrieveButtonBy);
    }
}

package com.br.theinternet.pages;

import org.openqa.selenium.*;

public class BasicAuthPage extends BasePage
{
    private By contentBy = By.cssSelector("p");

    private static final String baseURL = "the-internet.herokuapp.com/basic_auth";

    public BasicAuthPage(WebDriver driver)
    {
        super(driver);
    }

    public void navigateWithCredentials(String username, String password)
    {
        String authURL = "https://" + username + ":" + password + "@" + baseURL;
        driver.get(authURL);
    }

    public void navigateWithAuth(HasAuthentication authDriver, String username, String password)
    {
        authDriver.register(UsernameAndPassword.of(username, password));
        driver.get("https://" + baseURL);
    }

    public String getSuccessMessage()
    {
        return getText(contentBy);
    }
}

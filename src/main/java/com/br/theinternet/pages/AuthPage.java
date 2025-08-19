package com.br.theinternet.pages;

import org.openqa.selenium.*;

public class AuthPage extends BasePage
{
    private By contentBy = By.cssSelector("p");

    public AuthPage(WebDriver driver)
    {
        super(driver);
    }

    public void navigateWithCredentials(String username, String password, String baseURL)
    {
        String authURL = "https://" + username + ":" + password + "@" + baseURL;
        driver.get(authURL);
    }

    public void navigateWithAuth(HasAuthentication authDriver, String username, String password, String baseURL)
    {
        authDriver.register(UsernameAndPassword.of(username, password));
        driver.get("https://" + baseURL);
    }

    public String getSuccessMessage()
    {
        return getText(contentBy);
    }
}

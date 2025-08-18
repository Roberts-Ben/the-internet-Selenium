package com.br.theinternet.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import static org.junit.jupiter.api.Assertions.*;

public class DigestAuth_Test extends BaseTest
{
    String baseURL = "the-internet.herokuapp.com/digest_auth";;
    String username = "admin";
    String password = "admin";

    @Test
    public void verifyAuthSuccessViaDirectURL()
    {
        String authURL = "https://" + username + ":" + password + "@" + baseURL;

        driver.get(authURL);

        WebElement authSuccessContent = driver.findElement(By.cssSelector("p"));
        assertEquals("Congratulations! You must have the proper credentials.", authSuccessContent.getText());
    }

    @Test
    public void verifyAuthSuccessViaHasAuthentication()
    {
        ((HasAuthentication)driver).register(UsernameAndPassword.of(username, password));

        driver.get("https://" + baseURL);

        WebElement authSuccessContent = driver.findElement(By.cssSelector("p"));
        assertEquals("Congratulations! You must have the proper credentials.", authSuccessContent.getText());
    }
}
package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuth_Test extends BaseTest
{
    String baseURL = "the-internet.herokuapp.com/basic_auth";
    String username = "admin";
    String password = "admin";

    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/basic_auth");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/basic_auth", URL);
    }

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
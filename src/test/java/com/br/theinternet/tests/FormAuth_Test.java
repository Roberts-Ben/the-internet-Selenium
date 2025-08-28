package com.br.theinternet.tests;

import com.br.theinternet.pages.FormAuthPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FormAuth_Test extends BaseTest
{
    private FormAuthPage page;

    private static final String URL = "https://the-internet.herokuapp.com/login";
    private static final String bypassURL = "https://the-internet.herokuapp.com/secure";

    private static final String username = "tomsmith";
    private static final String password = "SuperSecretPassword!";
    private static final String invalidUsername = "test";
    private static final String invalidPassword = "pass";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, FormAuthPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyValidAuth()
    {
        page.inputCredentials(username, password);

        page.clickLogin();

        assertTrue(page.getDataAlertText().contains("You logged into a secure area!"));
    }

    @Test
    public void verifyInvalidAuth()
    {
        page.inputCredentials(invalidUsername, invalidPassword);

        page.clickLogin();

        assertTrue(page.getDataAlertText().contains("Your username is invalid!"));
    }

    @Test
    public void verifyLogout()
    {
        page.inputCredentials(username, password);

        page.clickLogin();

        page.clickLogout();

        assertTrue(page.getDataAlertText().contains("You logged out of the secure area!"));
    }

    @Test
    public void verifyUnableToBypass()
    {
        page.navigateTo(bypassURL);
        assertEquals(URL, page.getCurrentURL());

        assertTrue(page.getDataAlertText().contains("You must login to view the secure area!"));
    }
}
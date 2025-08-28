package com.br.theinternet.tests;

import com.br.theinternet.pages.FormAuthPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

    @ParameterizedTest(name = "verifyValidAuth: {0}")
@   EnumSource(BrowserType.class)
    public void verifyValidAuth(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FormAuthPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.inputCredentials(username, password);

        page.clickLogin();

        assertTrue(page.getDataAlertText().contains("You logged into a secure area!"));
    }

    @ParameterizedTest(name = "verifyInvalidAuth: {0}")
    @EnumSource(BrowserType.class)
    public void verifyInvalidAuth(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FormAuthPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.inputCredentials(invalidUsername, invalidPassword);

        page.clickLogin();

        assertTrue(page.getDataAlertText().contains("Your username is invalid!"));
    }

    @ParameterizedTest(name = "verifyLogout: {0}")
    @EnumSource(BrowserType.class)
    public void verifyLogout(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FormAuthPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.inputCredentials(username, password);

        page.clickLogin();

        page.clickLogout();

        assertTrue(page.getDataAlertText().contains("You logged out of the secure area!"));
    }

    @ParameterizedTest(name = "verifyUnableToBypass: {0}")
    @EnumSource(BrowserType.class)
    public void verifyUnableToBypass(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FormAuthPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.navigateTo(bypassURL);
        assertEquals(URL, page.getCurrentURL());

        assertTrue(page.getDataAlertText().contains("You must login to view the secure area!"));
    }
}
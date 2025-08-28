package com.br.theinternet.tests;

import com.br.theinternet.pages.AuthPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.HasAuthentication;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuth_Test extends BaseTest
{
    private AuthPage page;

    private static final String baseURL = "the-internet.herokuapp.com/basic_auth";

    String username = "admin";
    String password = "admin";

    @ParameterizedTest(name = "verifyAuthSuccessViaDirectURL: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAuthSuccessViaDirectURL(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, "", AuthPage.class);

        // Test
        page.navigateWithCredentials(username, password, baseURL);
        assertEquals("Congratulations! You must have the proper credentials.", page.getSuccessMessage());
    }

    @ParameterizedTest(name = "verifyAuthSuccessViaHasAuthentication: {0}")
    @EnumSource(BrowserType.class)
    public void verifyAuthSuccessViaHasAuthentication(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, "", AuthPage.class);

        // Test
        page.navigateWithAuth((HasAuthentication) driver, username, password, baseURL);
        assertEquals("Congratulations! You must have the proper credentials.", page.getSuccessMessage());
    }
}
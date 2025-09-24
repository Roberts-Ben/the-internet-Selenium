package com.br.theinternet.tests;

import com.br.theinternet.pages.AuthPage;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasAuthentication;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuth_Test extends BaseTest
{
    private AuthPage page;

    private static final String baseURL = "the-internet.herokuapp.com/basic_auth";

    String username = "admin";
    String password = "admin";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, "", AuthPage.class);
    }

    @Test
    public void verifyAuthSuccessViaDirectURL()
    {
        page.navigateWithCredentials(username, password, baseURL);
        assertEquals("Congratulations! You must have the proper credentials.", page.getSuccessMessage());
    }

    @Test
    public void verifyAuthSuccessViaHasAuthentication()
    {
        Assumptions.assumeTrue(browser != BrowserType.FIREFOX, "Skipping test: Firefox does not support HasAuthentication");

        page.navigateWithAuth((HasAuthentication) driver, username, password, baseURL);
        assertEquals("Congratulations! You must have the proper credentials.", page.getSuccessMessage());
    }
}
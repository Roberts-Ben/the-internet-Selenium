package com.br.theinternet.tests;

import com.br.theinternet.pages.BasicAuthPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.HasAuthentication;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuth_Test extends BaseTest
{
    private BasicAuthPage page;

    private static final String URL = "the-internet.herokuapp.com/basic_auth";

    String username = "admin";
    String password = "admin";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new BasicAuthPage(driver);
    }

    @Test
    public void verifyAuthSuccessViaDirectURL()
    {
        page.navigateWithCredentials(username, password);
        assertEquals("Congratulations! You must have the proper credentials.", page.getSuccessMessage());
    }

    @Test
    public void verifyAuthSuccessViaHasAuthentication()
    {
        page.navigateWithAuth((HasAuthentication) driver, username, password);
        assertEquals("Congratulations! You must have the proper credentials.", page.getSuccessMessage());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.ForgotPasswordPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class ForgotPassword_Test extends BaseTest
{
    private ForgotPasswordPage page;

    private static final String URL = "https://the-internet.herokuapp.com/forgot_password";

    @ParameterizedTest(name = "verifyForgotPassword: {0}")
    @EnumSource(BrowserType.class)
    public void verifyForgotPassword(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ForgotPasswordPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        String email = "TestUser@TestEmail.com";

        page.inputEmail(email);

        assertEquals(email, page.getInputFieldValue());

        page.clickRetrieve();

        assertEquals("Internal Server Error", page.getHeaderText());
    }
}
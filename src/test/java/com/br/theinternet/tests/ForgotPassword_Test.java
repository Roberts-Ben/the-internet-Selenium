package com.br.theinternet.tests;

import com.br.theinternet.pages.ForgotPasswordPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ForgotPassword_Test extends BaseTest
{
    private ForgotPasswordPage page;

    private static final String URL = "https://the-internet.herokuapp.com/forgot_password";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new ForgotPasswordPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyForgotPassword()
    {
        String email = "TestUser@TestEmail.com";

        page.inputEmail(email);

        assertEquals(email, page.getInputFieldValue());

        page.clickRetrieve();

        assertEquals("Internal Server Error", page.getHeaderText());
    }
}
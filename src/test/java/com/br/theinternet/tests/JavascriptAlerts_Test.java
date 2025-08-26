package com.br.theinternet.tests;

import com.br.theinternet.pages.JavascriptAlertsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptAlerts_Test extends BaseTest
{
    private JavascriptAlertsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/javascript_alerts";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new JavascriptAlertsPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyJSAlert()
    {
        page.clickJSAlert();

        assertEquals("I am a JS Alert", page.getAlertText());

        page.acceptAlert();

        assertEquals("You successfully clicked an alert", page.getResultText());
    }

    @Test
    public void verifyJSConfirmOK()
    {
        page.clickJSConfirm();

        assertEquals("I am a JS Confirm", page.getAlertText());

        page.acceptAlert();

        assertEquals("You clicked: Ok", page.getResultText());
    }

    @Test
    public void verifyJSConfirmCancel()
    {
        page.clickJSConfirm();

        assertEquals("I am a JS Confirm", page.getAlertText());

        page.dismissAlert();

        assertEquals("You clicked: Cancel", page.getResultText());
    }

    @Test
    public void verifyJSPromptAccept()
    {
         page.clickJSPrompt();

        assertEquals("I am a JS prompt", page.getAlertText());

        page.enterText("Test");
        page.acceptAlert();

        assertEquals("You entered: Test", page.getResultText());
    }

    @Test
    public void verifyJSPromptEmpty()
    {
        page.clickJSPrompt();

        assertEquals("I am a JS prompt", page.getAlertText());

        page.acceptAlert();

        assertEquals("You entered:", page.getResultText());
    }

    @Test
    public void verifyJSPromptCancel()
    {
        page.clickJSPrompt();

        assertEquals("I am a JS prompt", page.getAlertText());

        page.dismissAlert();

        assertEquals("You entered: null", page.getResultText());
    }
}
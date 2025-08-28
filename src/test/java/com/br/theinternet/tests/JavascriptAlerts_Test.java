package com.br.theinternet.tests;

import com.br.theinternet.pages.JavascriptAlertsPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptAlerts_Test extends BaseTest
{
    private JavascriptAlertsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/javascript_alerts";

    @ParameterizedTest(name = "verifyJSAlert: {0}")
    @EnumSource(BrowserType.class)
    public void verifyJSAlert(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptAlertsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickJSAlert();

        assertEquals("I am a JS Alert", page.getAlertText());

        page.acceptAlert();

        assertEquals("You successfully clicked an alert", page.getResultText());
    }

    @ParameterizedTest(name = "verifyJSConfirmOK: {0}")
    @EnumSource(BrowserType.class)
    public void verifyJSConfirmOK(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptAlertsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickJSConfirm();

        assertEquals("I am a JS Confirm", page.getAlertText());

        page.acceptAlert();

        assertEquals("You clicked: Ok", page.getResultText());
    }

    @ParameterizedTest(name = "verifyJSConfirmCancel: {0}")
    @EnumSource(BrowserType.class)
    public void verifyJSConfirmCancel(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptAlertsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickJSConfirm();

        assertEquals("I am a JS Confirm", page.getAlertText());

        page.dismissAlert();

        assertEquals("You clicked: Cancel", page.getResultText());
    }

    @ParameterizedTest(name = "verifyJSPromptAccept: {0}")
    @EnumSource(BrowserType.class)
    public void verifyJSPromptAccept(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptAlertsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickJSPrompt();

        assertEquals("I am a JS prompt", page.getAlertText());

        page.enterAlertText("Test");
        page.acceptAlert();

        assertEquals("You entered: Test", page.getResultText());
    }

    @ParameterizedTest(name = "verifyJSPromptEmpty: {0}")
    @EnumSource(BrowserType.class)
    public void verifyJSPromptEmpty(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptAlertsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickJSPrompt();

        assertEquals("I am a JS prompt", page.getAlertText());

        page.acceptAlert();

        assertEquals("You entered:", page.getResultText());
    }

    @ParameterizedTest(name = "verifyJSPromptCancel: {0}")
    @EnumSource(BrowserType.class)
    public void verifyJSPromptCancel(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptAlertsPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickJSPrompt();

        assertEquals("I am a JS prompt", page.getAlertText());

        page.dismissAlert();

        assertEquals("You entered: null", page.getResultText());
    }
}
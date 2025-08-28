package com.br.theinternet.tests;

import com.br.theinternet.pages.StatusCodesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class StatusCodes_Test extends BaseTest
{
    private StatusCodesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/status_codes";

    @ParameterizedTest(name = "verifyCode200: {0}")
    @EnumSource(BrowserType.class)
    public void verifyCode200(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, StatusCodesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals(200, page.getStatusCode(200));
    }

    @ParameterizedTest(name = "verifyCode301: {0}")
    @EnumSource(BrowserType.class)
    public void verifyCode301(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, StatusCodesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals(301, page.getRedirectStatusCode(301));
    }

    @ParameterizedTest(name = "verifyCode404: {0}")
    @EnumSource(BrowserType.class)
    public void verifyCode404(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, StatusCodesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals(404, page.getStatusCode(404));
    }

    @ParameterizedTest(name = "verifyCode500: {0}")
    @EnumSource(BrowserType.class)
    public void verifyCode500(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, StatusCodesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals(500, page.getStatusCode(500));
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.RedirectLinkPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class RedirectLink_Test extends BaseTest
{
    private RedirectLinkPage page;

    private static final String URL = "https://the-internet.herokuapp.com/redirector";
    private static final String redirectURL = "https://the-internet.herokuapp.com/status_codes";

    @ParameterizedTest(name = "verifyRedirect: {0}")
    @EnumSource(BrowserType.class)
    public void verifyRedirect(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, RedirectLinkPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickRedirectLink();

        assertEquals(redirectURL, page.getCurrentURL());
    }
}
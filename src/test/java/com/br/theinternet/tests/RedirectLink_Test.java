package com.br.theinternet.tests;

import com.br.theinternet.pages.RedirectLinkPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RedirectLink_Test extends BaseTest
{
    private RedirectLinkPage page;

    private static final String URL = "https://the-internet.herokuapp.com/redirector";
    private static final String redirectURL = "https://the-internet.herokuapp.com/status_codes";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, RedirectLinkPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyRedirect()
    {
        page.clickRedirectLink();

        assertEquals(redirectURL, page.getCurrentURL());
    }
}
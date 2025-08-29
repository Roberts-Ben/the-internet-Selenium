package com.br.theinternet.tests;

import com.br.theinternet.pages.StatusCodesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StatusCodes_Test extends BaseTest
{
    private StatusCodesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/status_codes";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, StatusCodesPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyCode200()
    {
        assertEquals(200, page.getStatusCode(200));
    }

    @Test
    public void verifyCode301()
    {
        assertEquals(301, page.getRedirectStatusCode(301));
    }

    @Test
    public void verifyCode404()
    {
        assertEquals(404, page.getStatusCode(404));
    }

    @Test
    public void verifyCode500()
    {
        assertEquals(500, page.getStatusCode(500));
    }
}
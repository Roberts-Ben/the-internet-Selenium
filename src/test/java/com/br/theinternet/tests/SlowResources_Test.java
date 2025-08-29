package com.br.theinternet.tests;

import com.br.theinternet.pages.SlowResourcesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Currently this page fails with a 503 rather than loading the expected elements
// To test around this:
// Navigate directly to the page that calls the GET request and validating that it fails after 30 seconds instead of loading the content
public class SlowResources_Test extends BaseTest
{
    private SlowResourcesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/slow";
    private static final String slowURL = "https://the-internet.herokuapp.com/slow_external";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, SlowResourcesPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifySlowElement()
    {
        assertEquals(503, page.getSlowResourceStatus(slowURL));
    }
}
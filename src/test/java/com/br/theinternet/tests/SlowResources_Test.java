package com.br.theinternet.tests;

import com.br.theinternet.pages.SlowResourcesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Currently this page fails with a 503 rather than loading the expected elements
// To test around this:
// Navigate directly to the page that calls the GET request and validating that it fails after 30 seconds instead of loading the content
public class SlowResources_Test extends BaseTest
{
    private SlowResourcesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/slow";
    private static final String slowURL = "https://the-internet.herokuapp.com/slow_external";

    @ParameterizedTest(name = "verifySlowElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifySlowElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, SlowResourcesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals(503, page.getSlowResourceStatus(slowURL));
    }
}
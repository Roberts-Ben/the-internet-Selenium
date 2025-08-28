package com.br.theinternet.tests;

import com.br.theinternet.pages.ShadowDOMPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShadowDOM_Test extends BaseTest
{
    private ShadowDOMPage page;

    private static final String URL = "https://the-internet.herokuapp.com/shadowdom";

    @ParameterizedTest(name = "verifyShadowDOM: {0}")
    @EnumSource(BrowserType.class)
    public void verifyShadowDOM(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, ShadowDOMPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        List<WebElement> shadowHosts = page.getShadowHosts();

        // First shadow element
        assertEquals("My default text", page.getShadowText(shadowHosts.getFirst()));

        // First projected light
        assertEquals("Let's have some different text!", page.getLightSpanText());

        // Second shadow element
        assertEquals("My default text", page.getShadowText(shadowHosts.getLast()));

        // Second projected light
        List<WebElement> listItems = page.getLightListItems();
        assertEquals("Let's have some different text!", listItems.getFirst().getText());
        assertEquals("In a list!", listItems.getLast().getText());
    }
}
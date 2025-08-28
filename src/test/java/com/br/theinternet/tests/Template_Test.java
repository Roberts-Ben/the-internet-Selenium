package com.br.theinternet.tests;

import com.br.theinternet.pages.TemplatePage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Template_Test extends BaseTest
{
    private TemplatePage page;

    private static final String URL = "https://the-internet.herokuapp.com/";

    @ParameterizedTest(name = "TestCase: {0}")
    @EnumSource(BrowserType.class)
    public void TestCase(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, TemplatePage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.TyposPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Typos_Test extends BaseTest
{
    private TyposPage page;

    private static final String URL = "https://the-internet.herokuapp.com/typos";

    private static final String expectedText = "Sometimes you'll see a typo, other times you won't.";
    private static final String typoText = "Sometimes you'll see a typo, other times you won,t.";

    @ParameterizedTest(name = "verifyText: {0}")
    @EnumSource(BrowserType.class)
    public void verifyText(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, TyposPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        String actualText = page.getContent();

        assertTrue(actualText.equals(expectedText) || actualText.equals(typoText));
    }
}
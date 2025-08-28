package com.br.theinternet.tests;

import com.br.theinternet.pages.InfiniteScrollingPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class InfiniteScrolling_Test extends BaseTest
{
    private InfiniteScrollingPage page;

    private static final String URL = "https://the-internet.herokuapp.com/infinite_scroll";

    @ParameterizedTest(name = "verifyInfiniteScroll: {0}")
    @EnumSource(BrowserType.class)
    public void verifyInfiniteScroll(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, InfiniteScrollingPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        int numberOfTimesToScroll = 5;

        long screenHeight = page.getScreenHeight();
        long newScreenHeight;

        for(int i = 0; i < numberOfTimesToScroll; i++)
        {
            page.scrollWindow();

            page.delay(100);

            newScreenHeight = page.getScreenHeight();

            assertTrue(newScreenHeight > screenHeight);

            screenHeight = newScreenHeight;
        }
    }
}
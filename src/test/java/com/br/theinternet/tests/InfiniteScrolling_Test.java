package com.br.theinternet.tests;

import com.br.theinternet.pages.InfiniteScrollingPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InfiniteScrolling_Test extends BaseTest
{
    private InfiniteScrollingPage page;

    private static final String URL = "https://the-internet.herokuapp.com/infinite_scroll";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, InfiniteScrollingPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyInfiniteScroll() throws InterruptedException
    {
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
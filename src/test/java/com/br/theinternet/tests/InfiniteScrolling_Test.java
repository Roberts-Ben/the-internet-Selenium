package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;

import static org.junit.jupiter.api.Assertions.*;

public class InfiniteScrolling_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/infinite_scroll", URL);
    }

    @Test
    public void verifyInfiniteScroll() throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int numberOfTimesToScroll = 5;
        int scrollPauseTime = 100; // ms
        long screenHeight = (long)js.executeScript("return document.body.scrollHeight");
        long newScreenHeight;

        for(int i = 0; i < numberOfTimesToScroll; i++)
        {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            Thread.sleep(scrollPauseTime);

            newScreenHeight = (long)js.executeScript("return document.body.scrollHeight");

            assertTrue(newScreenHeight > screenHeight);

            screenHeight = newScreenHeight;
        }
    }
}
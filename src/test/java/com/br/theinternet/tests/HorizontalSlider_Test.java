package com.br.theinternet.tests;

import com.br.theinternet.pages.HorizontalSliderPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.*;

public class HorizontalSlider_Test extends BaseTest
{
    private HorizontalSliderPage page;

    private static final String URL = "https://the-internet.herokuapp.com/horizontal_slider";

    @ParameterizedTest(name = "verifySliderClickAndDrag: {0}")
    @EnumSource(BrowserType.class)
    public void verifySliderClickAndDrag(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, HorizontalSliderPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("0", page.getSliderValue());

        page.dragSlider(1);

        assertEquals("2.5", page.getSliderValue());
    }

    @ParameterizedTest(name = "verifySliderArrowKeys: {0}")
    @EnumSource(BrowserType.class)
    public void verifySliderArrowKeys(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, HorizontalSliderPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertEquals("0", page.getSliderValue());

        page.moveSliderViaKeys(Keys.ARROW_RIGHT);

        assertEquals("0.5", page.getSliderValue());

        page.moveSliderViaKeys(Keys.ARROW_LEFT);

        assertEquals("0", page.getSliderValue());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.HorizontalSliderPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.*;

public class HorizontalSlider_Test extends BaseTest
{
    private HorizontalSliderPage page;

    private static final String URL = "https://the-internet.herokuapp.com/horizontal_slider";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new HorizontalSliderPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifySliderClickAndDrag()
    {
        assertEquals("0", page.getSliderValue());

        page.dragSlider(1);

        assertEquals("2.5", page.getSliderValue());
    }

    @Test
    public void verifySliderArrowKeys()
    {
        assertEquals("0", page.getSliderValue());

        page.moveSliderViaKeys(Keys.ARROW_RIGHT);

        assertEquals("0.5", page.getSliderValue());

        page.moveSliderViaKeys(Keys.ARROW_LEFT);

        assertEquals("0", page.getSliderValue());
    }
}
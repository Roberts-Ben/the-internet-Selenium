package com.br.theinternet.tests;

import com.br.theinternet.pages.TyposPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class Typos_Test extends BaseTest
{
    private TyposPage page;

    private static final String URL = "https://the-internet.herokuapp.com/typos";

    private static final String expectedText = "Sometimes you'll see a typo, other times you won't.";
    private static final String typoText = "Sometimes you'll see a typo, other times you won,t.";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new TyposPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyText()
    {
        String actualText = page.getContent();

        assertTrue(actualText.equals(expectedText) || actualText.equals(typoText));
    }
}
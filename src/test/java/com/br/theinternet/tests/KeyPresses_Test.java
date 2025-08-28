package com.br.theinternet.tests;

import com.br.theinternet.pages.KeyPressesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.Keys;

import static org.junit.jupiter.api.Assertions.*;

public class KeyPresses_Test extends BaseTest
{
    private KeyPressesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/key_presses";

    @ParameterizedTest(name = "verifyValidKeyPress: {0}")
    @EnumSource(BrowserType.class)
    public void verifyValidKeyPress(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, KeyPressesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        assertFalse(page.isResultVisible());

        page.sendInput(Keys.ARROW_UP);
        assertEquals("You entered: UP", page.getResult());

        page.sendInput(Keys.BACK_SPACE);
        assertEquals("You entered: BACK_SPACE", page.getResult());

        page.sendInput(Keys.TAB);
        assertEquals("You entered: TAB", page.getResult());

        page.sendInput(Keys.ESCAPE);
        assertEquals("You entered: ESCAPE", page.getResult());

        page.sendInput(Keys.ENTER);

        page.delay(100);

        assertFalse(page.isResultVisible());
    }
}
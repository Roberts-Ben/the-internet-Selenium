package com.br.theinternet.tests;

import com.br.theinternet.pages.ABTestingPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class ABTesting_Test extends BaseTest
{
    private ABTestingPage page;

    private static final String URL = "https://the-internet.herokuapp.com/abtest";
    private static final String HEADER_A = "A/B Test Variation 1";
    private static final String HEADER_B = "A/B Test Control";


    @ParameterizedTest(name = "verifyHeaderText: {0}")
    @EnumSource(BrowserType.class)
    public void verifyHeaderText(BrowserType browserType) throws Exception
    {
        // Setup
        System.setProperty("browser", browserType.name());
        initializeDriver();

        page = new ABTestingPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());

        // Test
        String headerText = page.getHeaderText();

        assertTrue(headerText.equals(HEADER_A) || headerText.equals(HEADER_B),
                "Header text does not match any expected value. Found: " + headerText
        );
    }
}
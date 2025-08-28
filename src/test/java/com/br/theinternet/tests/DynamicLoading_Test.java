package com.br.theinternet.tests;

import com.br.theinternet.pages.DynamicLoadingPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicLoading_Test extends BaseTest
{
    private DynamicLoadingPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_controls";
    private static final String URLTest1 = "https://the-internet.herokuapp.com/dynamic_loading/1";
    private static final String URLTest2 = "https://the-internet.herokuapp.com/dynamic_loading/2";

    @ParameterizedTest(name = "verifyHiddenElement: {0}")
    @EnumSource(BrowserType.class)
    public void verifyHiddenElement(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DynamicLoadingPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.navigateTo(URLTest1);

        assertFalse(page.isHiddenElementVisible());

        page.clickStartButton();

        assertTrue(page.waitForHiddenElementVisible());
    }

    @ParameterizedTest(name = "verifyElementExistsAfterLoad: {0}")
    @EnumSource(BrowserType.class)
    public void verifyElementExistsAfterLoad(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, DynamicLoadingPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.navigateTo(URLTest2);

        assertEquals(0, page.getHiddenElementSize());

        page.clickStartButton();

        assertTrue(page.waitForHiddenElementVisible());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.DynamicLoadingPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicLoading_Test extends BaseTest
{
    private DynamicLoadingPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_loading";
    private static final String URLTest1 = "https://the-internet.herokuapp.com/dynamic_loading/1";
    private static final String URLTest2 = "https://the-internet.herokuapp.com/dynamic_loading/2";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, DynamicLoadingPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyHiddenElement()
    {
        page.navigateTo(URLTest1);

        assertFalse(page.isHiddenElementVisible());

        page.clickStartButton();

        assertTrue(page.waitForHiddenElementVisible());
    }

    @Test
    public void verifyElementExistsAfterLoad()
    {
        page.navigateTo(URLTest2);

        assertEquals(0, page.getHiddenElementSize());

        page.clickStartButton();

        assertTrue(page.waitForHiddenElementVisible());
    }
}
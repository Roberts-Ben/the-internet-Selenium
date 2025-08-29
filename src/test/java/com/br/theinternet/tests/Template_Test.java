package com.br.theinternet.tests;

import com.br.theinternet.pages.TemplatePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Template_Test extends BaseTest
{
    private TemplatePage page;

    private static final String URL = "https://the-internet.herokuapp.com/";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, TemplatePage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void TestCase()
    {

    }
}
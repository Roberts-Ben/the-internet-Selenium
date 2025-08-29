package com.br.theinternet.tests;

import com.br.theinternet.pages.DynamicContentPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicContent_Test extends BaseTest
{
    private DynamicContentPage page;

    private static final String URL = "https://the-internet.herokuapp.com/dynamic_content";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, DynamicContentPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyDynamicContent()
    {
        List<String> originalAvatars = page.getAvatarSources();
        List<String> originalParagraphs = page.getParagraphTexts();

        page.refreshPage();

        List<String> newAvatars = page.getAvatarSources();
        List<String> newParagraphs = page.getParagraphTexts();

        assertTrue(page.hasContentChanged(originalAvatars, newAvatars, false) || page.hasContentChanged(originalParagraphs, newParagraphs, false));
    }

    @Test
    public void verifyStaticAndDynamicContent()
    {
        page.enableStaticDynamicMode();

        List<String> originalAvatars = page.getAvatarSources();
        List<String> originalParagraphs = page.getParagraphTexts();

        page.refreshPage();

        List<String> newAvatars = page.getAvatarSources();
        List<String> newParagraphs = page.getParagraphTexts();

        for (int i = 0; i < originalAvatars.size(); i++)
        {
            if(i < 2)
            {
                // First 2 are always static
                assertEquals(originalAvatars.get(i), newAvatars.get(i));
                assertEquals(originalParagraphs.get(i), newParagraphs.get(i));
            }
        }

        // Confirm content is different
        assertTrue(page.hasContentChanged(originalAvatars, newAvatars, true) || page.hasContentChanged(originalParagraphs, newParagraphs, true));
    }
}
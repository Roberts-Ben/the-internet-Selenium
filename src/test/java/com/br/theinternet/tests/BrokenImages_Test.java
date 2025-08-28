package com.br.theinternet.tests;

import com.br.theinternet.pages.BrokenImagesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenImages_Test extends BaseTest
{
    private BrokenImagesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/broken_images";

    @ParameterizedTest(name = "verifyImages: {0}")
    @EnumSource(BrowserType.class)
    public void verifyImages (BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, BrokenImagesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        List<WebElement> images = page.getAllImages();

        for (WebElement image : images)
        {
            String imageURL = page.getImageURL(image);

            if(imageURL != null)
            {
                int statusCode = page.verifyImageStatus(imageURL);

                assertTrue(statusCode == 200 || statusCode == 404,
                        "Unexpected status code. Found: " + statusCode
                );
            }
        }
    }
}
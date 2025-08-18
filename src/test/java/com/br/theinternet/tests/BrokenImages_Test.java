package com.br.theinternet.tests;

import com.br.theinternet.pages.AddRemoveElementsPage;
import com.br.theinternet.pages.BrokenImagesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenImages_Test extends BaseTest
{
    private BrokenImagesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/broken_images";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new BrokenImagesPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyImages ()
    {
        List<WebElement> images = page.getAllImages();

        for (WebElement image : images)
        {
            String imageURL = image.getAttribute("src");

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
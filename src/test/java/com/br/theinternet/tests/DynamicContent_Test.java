package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicContent_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/dynamic_content");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/dynamic_content", URL);
    }

    @Test
    public void verifyDynamicContent()
    {
        List<String> originalAvatarContent = getAvatarSources();
        List<String> originalParagraphContent = getParagraphTexts();

        // Refresh page
        driver.navigate().refresh();

        List<String> newAvatarContent = getAvatarSources();
        List<String> newParagraphContent = getParagraphTexts();

        // Confirm content is different
        for (int i = 0; i < originalAvatarContent.size(); i++)
        {
            if(!Objects.equals(originalAvatarContent.get(i), newAvatarContent.get(i)))
            {
                assertNotEquals(originalAvatarContent.get(i), newAvatarContent.get(i));
            }
            else
            {
                assertEquals(originalAvatarContent.get(i), newAvatarContent.get(i));
            }

            if(!Objects.equals(originalParagraphContent.get(i), newParagraphContent.get(i)))
            {
                assertNotEquals(originalParagraphContent.get(i), newParagraphContent.get(i));
            }
            else
            {
                assertEquals(originalParagraphContent.get(i), newParagraphContent.get(i));
            }
        }
    }

    @Test
    public void verifyStaticandDynamicContent()
    {
        WebElement staticContentLink = driver.findElement(By.xpath("//a[text()='click here']"));
        staticContentLink.click();

        List<String> originalAvatarContent = getAvatarSources();
        List<String> originalParagraphContent = getParagraphTexts();

        // Refresh page
        driver.navigate().refresh();

        List<String> newAvatarContent = getAvatarSources();
        List<String> newParagraphContent = getParagraphTexts();

        // Confirm content is different
        for (int i = 0; i < originalAvatarContent.size(); i++)
        {
            if(i < 2)
            {
                // First 2 are always static
                assertEquals(originalAvatarContent.get(i), newAvatarContent.get(i));
                assertEquals(originalParagraphContent.get(i), newParagraphContent.get(i));
            }
            else
            {
                if(!Objects.equals(originalAvatarContent.get(i), newAvatarContent.get(i)))
                {
                    assertNotEquals(originalAvatarContent.get(i), newAvatarContent.get(i));
                }
                else
                {
                    assertEquals(originalAvatarContent.get(i), newAvatarContent.get(i));
                }

                if(!Objects.equals(originalParagraphContent.get(i), newParagraphContent.get(i)))
                {
                    assertNotEquals(originalParagraphContent.get(i), newParagraphContent.get(i));
                }
                else
                {
                    assertEquals(originalParagraphContent.get(i), newParagraphContent.get(i));
                }
            }
        }
    }

    private List<String> getAvatarSources()
    {
        List<WebElement> avatars = driver.findElements(By.xpath("//img[contains(@src, 'avatar')]"));
        List<String> sources = new ArrayList<>();
        for (WebElement avatar : avatars)
        {
            sources.add(avatar.getAttribute("src"));
        }
        return sources;
    }

    private List<String> getParagraphTexts()
    {
        WebElement paragraphHolder = driver.findElement(By.className("large-10"));

        List<WebElement> paragraphs = paragraphHolder.findElements(By.className("large-10"));
        List<String> texts = new ArrayList<>();
        for (WebElement paragraph : paragraphs)
        {
            texts.add(paragraph.getText());
        }
        return texts;
    }
}
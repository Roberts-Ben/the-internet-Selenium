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
        List<WebElement> avatars = driver.findElements(By.xpath("//img[contains(@src, 'avatar')]"));
        List<WebElement> paragraphs = driver.findElements(By.className("large-10"));

        List<String> avatarContent = getAvatarSources();
        List<String> paragraphContent = getParagraphTexts();

        // Refresh page
        driver.navigate().refresh();

        avatars.clear();
        paragraphs.clear();
        avatarContent.clear();
        paragraphContent.clear();

        avatars = driver.findElements(By.xpath("//img[contains(@src, 'avatar')]"));
        paragraphs = driver.findElements(By.className("large-10"));

        avatarContent = getAvatarSources();
        paragraphContent = getParagraphTexts();

        // Confirm content is different
        for (int i = 0; i < avatars.size(); i++)
        {
            String currentAvatarContent = avatars.get(i).getAttribute("src");
            String currentParagraphContent = paragraphs.get(i).getText();

            if(!Objects.equals(avatarContent.get(i), currentAvatarContent))
            {
                assertNotEquals(avatarContent.get(i), currentAvatarContent);
            }
            else
            {
                assertEquals(avatarContent.get(i), currentAvatarContent);
            }

            if(!Objects.equals(paragraphContent.get(i), currentParagraphContent))
            {
                assertNotEquals(paragraphContent.get(i), currentParagraphContent);
            }
            else
            {
                assertEquals(paragraphContent.get(i), currentParagraphContent);
            }
        }
    }

    @Test
    public void verifyStaticandDynamicContent()
    {
        WebElement staticContentLink = driver.findElement(By.xpath("//a[text()='click here']"));
        staticContentLink.click();

        List<WebElement> avatars = driver.findElements(By.xpath("//img[contains(@src, 'avatar')]"));
        List<WebElement> paragraphs = driver.findElements(By.className("large-10"));

        List<String> avatarContent = getAvatarSources();
        List<String> paragraphContent = getParagraphTexts();

        // Refresh page
        driver.navigate().refresh();

        avatars.clear();
        paragraphs.clear();
        avatarContent.clear();
        paragraphContent.clear();

        avatars = driver.findElements(By.xpath("//img[contains(@src, 'avatar')]"));
        paragraphs = driver.findElements(By.className("large-10"));

        avatarContent = getAvatarSources();
        paragraphContent = getParagraphTexts();

        // Confirm content is different
        for (int i = 0; i < avatars.size(); i++)
        {
            String currentAvatarContent = avatars.get(i).getAttribute("src");
            String currentParagraphContent = paragraphs.get(i).getText();

            if(i < 2)
            {
                // First 2 are always static
                assertEquals(avatarContent.get(i), currentAvatarContent);
                assertEquals(paragraphContent.get(i), currentParagraphContent);
            }
            else
            {
                if(!Objects.equals(avatarContent.get(i), currentAvatarContent))
                {
                    assertNotEquals(avatarContent.get(i), currentAvatarContent);
                }
                else
                {
                    assertEquals(avatarContent.get(i), currentAvatarContent);
                }

                if(!Objects.equals(paragraphContent.get(i), currentParagraphContent))
                {
                    assertNotEquals(paragraphContent.get(i), currentParagraphContent);
                }
                else
                {
                    assertEquals(paragraphContent.get(i), currentParagraphContent);
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
        List<WebElement> paragraphs = driver.findElements(By.className("large-10"));
        List<String> texts = new ArrayList<>();
        for (WebElement paragraph : paragraphs)
        {
            texts.add(paragraph.getText());
        }
        return texts;
    }
}
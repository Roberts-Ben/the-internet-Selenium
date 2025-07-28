import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class Frames_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/frames");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/frames", URL);
    }

    @Test
    public void verifyNestedFramesLink()
    {
        WebElement nestedFramesLink = driver.findElement(By.xpath("//a[@href='/nested_frames']"));
        nestedFramesLink.click();

        assertEquals("https://the-internet.herokuapp.com/nested_frames", driver.getCurrentUrl());
    }

    @Test
    public void verifyiFrame()
    {
        driver.get("https://the-internet.herokuapp.com/iframe");

        // Need to close the TinyMCE read-only warning before interacting with other elements
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//div[@aria-label='Close']/..")));
        closeButton.click();

        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame("mce_0_ifr");

        WebElement editor = driver.findElement(By.tagName("body"));

        assertEquals("Your content goes here.", editor.getText());
    }
}
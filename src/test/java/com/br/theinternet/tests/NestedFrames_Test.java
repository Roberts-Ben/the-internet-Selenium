package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class NestedFrames_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/nested_frames");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/nested_frames", URL);
    }

    @Test
    public void verifyNestedFrames()
    {
        // Navigate to the top frameset
        driver.switchTo().defaultContent();
        driver.switchTo().frame("frame-top");

        // Find the top 3 frames
        WebElement middleFrame = driver.findElement(By.name("frame-middle"));
        WebElement leftFrame = driver.findElement(RelativeLocator.with(By.tagName("frame")).toLeftOf(middleFrame));
        WebElement rightFrame = driver.findElement(RelativeLocator.with(By.tagName("frame")).toRightOf(middleFrame));

        // Navigate to the root to find the bottom frame
        driver.switchTo().defaultContent();
        WebElement bottomFrame = driver.findElement(By.name("frame-bottom"));

        // Confirm text matches what we expect
        ConfirmTextContent(new String[][] {
                {"frame-top", "frame-middle", "MIDDLE"},
                {"frame-top", "frame-left", "LEFT"},
                {"frame-top", "frame-right", "RIGHT"},
                {"frame-bottom", "BOTTOM"}
        });
    }

    private void ConfirmTextContent(String[]... frameNamesAndExpectedText)
    {
        for (String[] arr : frameNamesAndExpectedText)
        {
            // Body text should be "LEFT", "MIDDLE" etc. last element in the array.
            String expectedText = arr[arr.length - 1];
            String[] frames = Arrays.copyOf(arr, arr.length - 1);

            driver.switchTo().defaultContent();

            for (String frame : frames) // Navigate through the frameset until we reach the expected frame
            {
                driver.switchTo().frame(frame);
            }

            WebElement bodyContent = driver.findElement(By.tagName("body"));
            assertEquals(expectedText, bodyContent.getText());
        }
    }
}
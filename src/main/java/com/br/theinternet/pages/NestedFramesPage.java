package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage extends BasePage
{
    private By frameBodyBy = By.tagName("body");

    public NestedFramesPage(WebDriver driver)
    {
        super(driver);
    }

    public String getFrameText(String... frames)
    {
        switchToDefault();

        // Navigate through nested frames
        for (String frame : frames)
        {
            switchToFrame(frame);
        }

        return getText(frameBodyBy);
    }

    public boolean verifyFrameText(String expectedText, String... frames)
    {
        return getFrameText(frames).equals(expectedText);
    }
}

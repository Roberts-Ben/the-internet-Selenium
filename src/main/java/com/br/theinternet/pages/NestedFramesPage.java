package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NestedFramesPage extends BasePage
{
    private By frameBodyBy = By.tagName("body");

    public NestedFramesPage(WebDriver driver)
    {
        super(driver);
    }

    public String getFrameText(String... frames)
    {
        driver.switchTo().defaultContent();

        // Navigate through nested frames
        for (String frame : frames)
        {
            driver.switchTo().frame(frame);
        }

        return getText(frameBodyBy);
    }

    public boolean verifyFrameText(String expectedText, String... frames)
    {
        return getFrameText(frames).equals(expectedText);
    }
}

package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FramesPage extends BasePage
{
    private By nestedFramesLinkBy = By.xpath("//a[@href='/nested_frames']");
    private By closeButtonBy = By.xpath("//button//div[@aria-label='Close']/..");
    private By editorBy = By.tagName("body");

    public FramesPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickNestedFramesLink()
    {
        click(nestedFramesLinkBy);
    }

    public void clickCloseButton()
    {
        click(closeButtonBy);
    }

    public String getFrameContent()
    {
        driver.switchTo().frame("mce_0_ifr");
        return getText(editorBy);
    }
}

package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShadowDOMPage extends BasePage
{
    private By shadowHostBy = By.cssSelector("my-paragraph");
    private By slotInsideShadowBy = By.name("my-text");
    private By slotInsideFirstLightBy = By.cssSelector("my-paragraph > span[slot='my-text']");
    private By getSlotInsideSecondLightBy = By.cssSelector("my-paragraph > ul[slot='my-text'] > li");

    public ShadowDOMPage(WebDriver driver)
    {
        super(driver);
    }

    public List<WebElement> getShadowHosts()
    {
        return driver.findElements(shadowHostBy);
    }

    // Get default shadow text inside first/second host
    public String getShadowText(WebElement shadowHost)
    {
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        return shadowRoot.findElement(slotInsideShadowBy).getText();
    }

    public String getLightSpanText()
    {
        return driver.findElement(slotInsideFirstLightBy).getText();
    }

    public List<WebElement> getLightListItems()
    {
        return driver.findElements(getSlotInsideSecondLightBy);
    }
}

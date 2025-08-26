package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HorizontalSliderPage extends BasePage
{
    private By sliderValueBy = By.id("range");
    private By sliderBy = By.xpath("//input[@type='range']");

    public HorizontalSliderPage(WebDriver driver)
    {
        super(driver);
    }

    public String getSliderValue()
    {
        return getText(sliderValueBy);
    }

    public void dragSlider(int value)
    {
        new Actions(driver).dragAndDropBy(find(sliderBy),value, 0).build().perform();
    }

    public void moveSliderViaKeys(Keys key)
    {
        type(sliderBy, String.valueOf(key));
    }
}

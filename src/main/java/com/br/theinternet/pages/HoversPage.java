package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class HoversPage extends BasePage
{
    private By hoverElementsBy = By.className("figure");
    private By hiddenElementsBy = By.className("figcaption");

    public HoversPage(WebDriver driver)
    {
        super(driver);
    }

    public List<WebElement> getHoverElements()
    {
        return findAll(hoverElementsBy);
    }

    public List<WebElement> getHiddenElements()
    {
        return findAll(hiddenElementsBy);
    }

    public void moveToElement(int index)
    {
        new Actions(driver).moveToElement(getHoverElements().get(index)).build().perform();
    }

    public boolean isElementVisible(int index)
    {
        return getHiddenElements().get(index).isDisplayed();
    }

    public int verifyValidLink(String userURL, int index)
    {
        String finalURL = userURL + index;
        return getStatusCode(finalURL);
    }
}

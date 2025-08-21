package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage extends BasePage
{
    private By startButtonBy = By.xpath("//button");
    private By hiddenElementBy = By.id("finish");

    public DynamicLoadingPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickStartButton()
    {
        click(startButtonBy);
    }

    public boolean isHiddenElementVisible()
    {
        return isDisplayed(hiddenElementBy);
    }

    public boolean waitForHiddenElementVisible()
    {
        return waitForVisible(hiddenElementBy);
    }

    public boolean waitForHiddenElementHidden()
    {
        return waitForInvisible(hiddenElementBy);
    }

    public int getHiddenElementSize()
    {
        return findAll(hiddenElementBy).size();
    }
}

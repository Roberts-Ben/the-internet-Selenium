package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ExitIntentPage extends BasePage
{
    private By modalBy = By.id("ouibounce-modal");
    private By modalCloseButtonBy = By.className("modal-footer");

    public ExitIntentPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isModalVisible()
    {
        return isDisplayed(modalBy);
    }

    public boolean isModalInteractive()
    {
        waitForClickable(modalCloseButtonBy);
        return isModalVisible();
    }

    public void closeModal()
    {
        click(modalCloseButtonBy);
        waitForInvisible(modalBy);
    }

    public void forceModal()
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("_ouibounce.fire();");
    }
}

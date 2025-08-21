package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EntryAdPage extends BasePage
{
    private By modalFooterBy = By.className("modal-footer");
    private By resetButtonBy = By.id("restart-ad");

    public EntryAdPage(WebDriver driver)
    {
        super(driver);
    }

    public boolean isModalVisible()
    {
        return isDisplayed(modalFooterBy);
    }

    public boolean waitForModalVisible()
    {
        return waitForVisible(modalFooterBy);
    }

    public boolean waitForModalHidden()
    {
        return waitForInvisible(modalFooterBy);
    }

    public void clickModal()
    {
        click(modalFooterBy);
    }

    public void clickResetButton()
    {
        click(resetButtonBy);
    }
}

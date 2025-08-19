package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DisappearingElementsPage extends BasePage
{
    private By buttonsBy = By.tagName("li");

    public DisappearingElementsPage(WebDriver driver)
    {
        super(driver);
    }

    public int getMenuSize()
    {
        return findAll(buttonsBy).size();
    }

    public void refreshPage()
    {
        driver.navigate().refresh();
    }
}

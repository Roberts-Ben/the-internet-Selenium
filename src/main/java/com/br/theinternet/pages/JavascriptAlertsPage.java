package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavascriptAlertsPage extends BasePage
{
    private By jsAlertButtonBy = By.xpath("//button[@onclick='jsAlert()']");
    private By jsConfirmButtonBy = By.xpath("//button[@onclick='jsConfirm()']");
    private By jsPromptButtonBy = By.xpath("//button[@onclick='jsPrompt()']");
    private By resultLabelBy = By.id("result");

    public JavascriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    public void clickJSAlert()
    {
        click(jsAlertButtonBy);
    }

    public void clickJSConfirm()
    {
        click(jsConfirmButtonBy);
    }

    public void clickJSPrompt()
    {
        click(jsPromptButtonBy);
    }

    public String getResultText()
    {
        return getText(resultLabelBy);
    }
}

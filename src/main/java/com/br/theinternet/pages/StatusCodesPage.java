package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StatusCodesPage extends BasePage
{
    private String codeLinks = "//div[@class='example']//a[@href='status_codes/";

    public StatusCodesPage(WebDriver driver)
    {
        super(driver);
    }

    // Click a specific code link
    public void clickLink(int code)
    {
        String xpath = codeLinks + code + "']";
        click(By.xpath(xpath));
    }
}

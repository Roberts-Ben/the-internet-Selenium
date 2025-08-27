package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class JQueryUIMenuPage extends BasePage
{
    private final By menuRootBy = By.id("ui-id-1");
    private final String menuItemPattern = "ui-id-%d";

    public JQueryUIMenuPage(WebDriver driver)
    {
        super(driver);
    }

    private By menuItem(int index)
    {
        return By.id(String.format(menuItemPattern, index + 1));
    }

    public boolean isDisabled(int index)
    {
        return Boolean.parseBoolean(getAttribute(menuItem(index), "aria-disabled"));
    }

    public boolean isDisplayed(int index)
    {
        return isDisplayed(menuItem(index));
    }

    public void hoverMenuPath(int... indices)
    {
        Actions hover = new Actions(driver);
        for (int index : indices)
        {
            hover.moveToElement(find(menuItem(index))).pause(500);
        }
        hover.build().perform();
    }

    public void resetMenuState()
    {
        new Actions(driver).moveToElement(find(menuRootBy)).build().perform();
    }

    public void clickMenuItem(int index)
    {
        click(menuItem(index));
    }

    public String triggerDownload(int index)
    {
        WebElement downloadItem = find(menuItem(index));
        WebElement link = downloadItem.findElement(By.tagName("a"));

        String downloadLink = link.getAttribute("href");
        String encodedFileName = downloadLink.substring(downloadLink.lastIndexOf('/') + 1);
        String fileName = URLDecoder.decode(encodedFileName, StandardCharsets.UTF_8);

        link.click();
        return fileName;
    }
}

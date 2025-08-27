package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NotificationMessagesPage extends BasePage
{
    private By notificationTriggerBy = By.xpath("//a[@href='/notification_message']");
    private By notificationBy = By.id("flash");

    public NotificationMessagesPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickNotificationTrigger()
    {
        click(notificationTriggerBy);
    }

    public String getNotificationText()
    {
        return getText(notificationBy);
    }
}

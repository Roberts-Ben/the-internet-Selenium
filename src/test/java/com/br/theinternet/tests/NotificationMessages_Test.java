package com.br.theinternet.tests;

import com.br.theinternet.pages.NotificationMessagesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationMessages_Test extends BaseTest
{
    private NotificationMessagesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/notification_message_rendered";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new NotificationMessagesPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyNotification()
    {
        WebElement notificationTrigger = driver.findElement(By.xpath("//a[@href='/notification_message']"));

        notificationTrigger.click();

        WebElement notification = driver.findElement(By.id("flash"));
        String notificationText = notification.getText();

        if(notificationText.contains("Action successful"))
        {
            assertTrue(notificationText.contains("Action successful"));
        }
        else
        {
            // Note: Typo is intended
            assertTrue(notificationText.contains("Action unsuccesful, please try again"));
        }
    }
}
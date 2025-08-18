package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationMessages_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/notification_message_rendered", URL);
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
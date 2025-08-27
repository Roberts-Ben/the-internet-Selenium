package com.br.theinternet.tests;

import com.br.theinternet.pages.NotificationMessagesPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationMessages_Test extends BaseTest
{
    private NotificationMessagesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/notification_message_rendered";
    private static final String successNotif = "Action successful";
    private static final String failNotif = "Action unsuccesful, please try again";

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
        page.clickNotificationTrigger();

        String notificationText = page.getNotificationText();
        assertTrue(notificationText.contains(successNotif) || notificationText.contains(failNotif));
    }
}
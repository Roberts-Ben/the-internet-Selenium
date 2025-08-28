package com.br.theinternet.tests;

import com.br.theinternet.pages.NotificationMessagesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationMessages_Test extends BaseTest
{
    private NotificationMessagesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/notification_message_rendered";
    private static final String successNotif = "Action successful";
    private static final String failNotif = "Action unsuccesful, please try again";

    @ParameterizedTest(name = "verifyNotification: {0}")
    @EnumSource(BrowserType.class)
    public void verifyNotification(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, NotificationMessagesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickNotificationTrigger();

        String notificationText = page.getNotificationText();
        assertTrue(notificationText.contains(successNotif) || notificationText.contains(failNotif));
    }
}
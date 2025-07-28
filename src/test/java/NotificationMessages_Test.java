import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class NotificationMessages_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/notification_message_rendered");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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
            assertTrue(notificationText.contains("Action unsuccesful, please try again"));
        }
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
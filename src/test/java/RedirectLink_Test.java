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

public class RedirectLink_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/redirector");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/redirector", URL);
    }

    @Test
    public void verifyRedirect()
    {
        WebElement redirectLink = driver.findElement(By.id("redirect"));
        String redirectURL = "https://the-internet.herokuapp.com/status_codes";

        redirectLink.click();

        assertEquals(redirectURL, driver.getCurrentUrl());
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
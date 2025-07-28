import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Geolocation_Test
{
    WebDriver driver;
    WebDriverWait wait;

    String mapsBaseURL = "http://maps.google.com/";

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/geolocation");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/geolocation", URL);
    }

    @Test
    public void verifyLocation()
    {
        WebElement locationButton = driver.findElement(By.xpath("//button[@onclick='getLocation()']"));

        locationButton.click();

        WebElement latValue = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("lat-value")));
        WebElement longValue = driver.findElement(By.id("long-value"));

        String location = mapsBaseURL + "?q=" + latValue.getText() + "," + longValue.getText();

        assertTrue(latValue.isDisplayed());

        WebElement mapsLink = driver.findElement(By.xpath("//a[contains(text(), 'See it on Google')]"));

        assertEquals(location, mapsLink.getAttribute("href"));
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
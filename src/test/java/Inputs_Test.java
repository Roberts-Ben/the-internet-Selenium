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

public class Inputs_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/inputs");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/inputs", URL);
    }

    @Test
    public void verifyValidInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        assertEquals("", inputField.getAttribute("value"));

        inputField.sendKeys("55");

        assertEquals("55", inputField.getAttribute("value"));
    }

    @Test
    public void verifyValidNegativeInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        assertEquals("", inputField.getAttribute("value"));

        inputField.sendKeys("-91");

        assertEquals("-91", inputField.getAttribute("value"));
    }

    @Test
    public void verifyInvalidInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='number']"));

        assertEquals("", inputField.getAttribute("value"));

        inputField.sendKeys("abc");

        assertEquals("", inputField.getAttribute("value"));
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
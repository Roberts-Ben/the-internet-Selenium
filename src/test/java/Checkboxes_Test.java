import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Checkboxes_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/checkboxes", URL);
    }

    @Test
    public void verifyCheckboxes()
    {
        List<WebElement> checkboxes = driver.findElements(By.cssSelector("input"));
        WebElement checkbox1 = checkboxes.getFirst();
        WebElement checkbox2 = checkboxes.getLast();

        assertFalse(checkbox1.isSelected());
        assertTrue(checkbox2.isSelected());

        checkbox1.click();
        checkbox2.click();

        assertTrue(checkbox1.isSelected());
        assertFalse(checkbox2.isSelected());
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
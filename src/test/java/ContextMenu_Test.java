import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ContextMenu_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/context_menu");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/context_menu", URL);
    }

    @Test
    public void verifyContextMenuAppears()
    {
        Actions action = new Actions(driver);
        WebElement contextMenuArea = driver.findElement(By.id("hot-spot"));

        action.contextClick(contextMenuArea).perform();

        Alert alert = driver.switchTo().alert();

        alert.accept();
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class FloatingMenu_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/floating_menu");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/floating_menu", URL);
    }

    @Test
    public void verifyFloatingHeader() throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int scrollPauseTime = 100; // ms

        WebElement floatingMenu = driver.findElement(By.id("menu"));
        var menuPos = floatingMenu.getAttribute("style");

        assertEquals("top: 0px;", menuPos);

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        Thread.sleep(scrollPauseTime);
        menuPos = floatingMenu.getAttribute("style");

        assertNotEquals("top: 0px;", menuPos);
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
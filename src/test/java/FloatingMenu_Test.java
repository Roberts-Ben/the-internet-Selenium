import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class FloatingMenu_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/floating_menu");

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
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class ContextMenu_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/context_menu");

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
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DragAndDrop
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/drag_and_drop", URL);
    }

    @Test
    public void verifyDragDropSwap()
    {
        // The draggable elements
        WebElement dragColumnA = driver.findElement(By.id("column-a"));
        WebElement dragColumnB = driver.findElement(By.id("column-b"));

        // The content of each drag/drop space
        WebElement dragHeaderA = driver.findElement(By.cssSelector("#column-a > header"));
        WebElement dragHeaderB = driver.findElement(By.cssSelector("#column-b > header"));

        assertEquals("A",dragHeaderA.getText());
        assertEquals("B",dragHeaderB.getText());

        Actions dragDropAction = new Actions(driver);
        dragDropAction.dragAndDrop(dragColumnA, dragColumnB).build().perform();

        // Re-assess which content is in each drag/rop space
        dragHeaderA = driver.findElement(By.cssSelector("#column-a > header"));
        dragHeaderB = driver.findElement(By.cssSelector("#column-b > header"));

        assertEquals("B",dragHeaderA.getText());
        assertEquals("A",dragHeaderB.getText());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}



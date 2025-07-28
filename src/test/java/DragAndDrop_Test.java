import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class DragAndDrop_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");

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
}



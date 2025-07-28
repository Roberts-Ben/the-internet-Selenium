import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Checkboxes_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/checkboxes");

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
}
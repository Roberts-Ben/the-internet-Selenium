import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class ABTesting_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/abtest");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/abtest", URL);
    }

    @Test
    public void verifyAPage()
    {
        String AHeader = "A/B Test Variation 1";
        String BHeader = "A/B Test Control";

        WebElement header = driver.findElement(By.cssSelector("h3"));

        if(header.getText().equals(AHeader))
        {
            assertEquals(AHeader, header.getText());
        }
        else if(header.getText().equals(BHeader))
        {
            assertEquals(BHeader, header.getText());
        }
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class Typos_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/typos");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/typos", URL);
    }

    @Test
    public void verifyText()
    {
        WebElement content = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]"));
        String actualText = content.getText();
        String expectedText = "Sometimes you'll see a typo, other times you won't.";

        if(expectedText.equals(actualText))
        {
            System.out.println("Content matches");
            assertEquals(expectedText, actualText);
        }
        else
        {
            System.out.println("Typo in: " + actualText);
            assertNotEquals(expectedText, actualText);
        }
    }
}
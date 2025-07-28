import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisappearingElements_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/disappearing_elements", URL);
    }

    @Test
    public void verifyDisappearingElements()
    {
        boolean verified5Elements = false;
        boolean verified4Elements = false;

        List<WebElement> buttons;

        while(!verified4Elements || !verified5Elements)
        {
            buttons = driver.findElements(By.tagName("li"));

            if(buttons.size() == 5)
            {
                if(!verified5Elements)
                {
                    assertEquals(5, buttons.size());
                    verified5Elements = true;
                }
            }
            else
            {
                if(!verified4Elements)
                {
                    assertEquals(4, buttons.size());
                    verified4Elements = true;
                }
            }

            driver.navigate().refresh();
        }
    }
}
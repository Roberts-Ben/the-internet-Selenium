import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DisappearingElements
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
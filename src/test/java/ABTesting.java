import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ABTesting
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/abtest");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

    @After
    public void Teardown()
    {
        driver.quit();
    }

    private void ClearBrowserCache()
    {
        driver.manage().deleteAllCookies();
    }
}
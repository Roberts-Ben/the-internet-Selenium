import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class InfiniteScrolling
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/infinite_scroll");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/infinite_scroll", URL);
    }

    @Test
    public void verifyInfiniteScroll() throws InterruptedException
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        int numberOfTimesToScroll = 5;
        int scrollPauseTime = 100; // ms
        long screenHeight = (long)js.executeScript("return document.body.scrollHeight");
        long newScreenHeight;

        for(int i = 0; i < numberOfTimesToScroll; i++)
        {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            Thread.sleep(scrollPauseTime);

            newScreenHeight = (long)js.executeScript("return document.body.scrollHeight");

            assertTrue(newScreenHeight > screenHeight);

            screenHeight = newScreenHeight;
        }
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
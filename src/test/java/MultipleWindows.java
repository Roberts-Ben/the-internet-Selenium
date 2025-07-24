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

public class MultipleWindows
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/windows");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/windows", URL);
    }

    @Test
    public void verifyNewWindow()
    {
        WebElement newTabButton = driver.findElement(By.xpath("//a[@href='/windows/new']"));

        newTabButton.click();

        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        WebElement newTabHeader = driver.findElement(By.cssSelector("h3"));
        assertEquals("New Window",newTabHeader.getText());

        driver.close();
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class KeyPresses
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/key_presses");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/key_presses", URL);
    }

    @Test
    public void verifyValidKeyPress()
    {
        WebElement inputField = driver.findElement(By.id("target"));
        WebElement resultLabel = driver.findElement(By.id("result"));

        assertFalse(resultLabel.isDisplayed());

        inputField.sendKeys(Keys.ARROW_UP);
        assertEquals("You entered: UP", resultLabel.getText());

        inputField.sendKeys(Keys.BACK_SPACE);
        assertEquals("You entered: BACK_SPACE", resultLabel.getText());

        inputField.sendKeys(Keys.TAB);
        assertEquals("You entered: TAB", resultLabel.getText());

        inputField.sendKeys(Keys.ESCAPE);
        assertEquals("You entered: ESCAPE", resultLabel.getText());

        inputField.sendKeys(Keys.ENTER);

        resultLabel = driver.findElement(By.id("result"));

        assertFalse(resultLabel.isDisplayed());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
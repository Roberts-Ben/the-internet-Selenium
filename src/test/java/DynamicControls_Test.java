import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DynamicControls_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/dynamic_controls", URL);
    }

    @Test
    public void verifyDynamicCheckbox()
    {
        List<WebElement> checkbox = driver.findElements(By.id("checkbox"));
        WebElement swapButton = driver.findElement(By.xpath("//button[@onclick='swapCheckbox()']"));

        assertEquals(1, checkbox.size());

        swapButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));

        assertTrue(successMessage.isDisplayed());
        assertEquals("It's gone!", successMessage.getText());

        checkbox = driver.findElements(By.id("checkbox"));
        assertEquals(0, checkbox.size());

        swapButton.click();

        successMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message")));

        assertTrue(successMessage.isDisplayed());
        assertEquals("It's back!", successMessage.getText());

        checkbox = driver.findElements(By.id("checkbox"));
        assertEquals(1, checkbox.size());
    }

    @Test
    public void verifyDynamicInput()
    {
        WebElement inputField = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement swapButton = driver.findElement(By.xpath("//button[@onclick='swapInput()']"));

        assertFalse(inputField.isEnabled());

        swapButton.click();

        wait.until(ExpectedConditions.elementToBeClickable(inputField));

        assertTrue(inputField.isEnabled());

        swapButton.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(inputField)));

        assertFalse(inputField.isEnabled());
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
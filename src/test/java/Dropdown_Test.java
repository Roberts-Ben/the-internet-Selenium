import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class Dropdown_Test
{
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/dropdown", URL);
    }

    @Test
    public void verifyDropdown()
    {
        Select dropdownSelect = new Select(driver.findElement(By.id("dropdown")));
        WebElement selectedOption = dropdownSelect.getFirstSelectedOption();

        assertEquals("Please select an option", selectedOption.getText());

        dropdownSelect.selectByValue("1");

        selectedOption = dropdownSelect.getFirstSelectedOption();
        assertEquals("Option 1", selectedOption.getText());
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
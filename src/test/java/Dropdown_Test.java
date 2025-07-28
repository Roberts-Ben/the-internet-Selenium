import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.*;

public class Dropdown_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/dropdown");

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
}
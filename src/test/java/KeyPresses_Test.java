import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class KeyPresses_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/key_presses");

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

        wait.until(ExpectedConditions.stalenessOf(resultLabel));
        resultLabel = driver.findElement(By.id("result"));

        assertFalse(resultLabel.isDisplayed());
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class ExitIntent_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/exit_intent");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/exit_intent", URL);
    }

    @Test
    public void verifyModal()
    {
        WebElement modal = driver.findElement(By.id("ouibounce-modal"));
        WebElement modalCloseButton = driver.findElement(By.className("modal-footer"));

        assertFalse(modal.isDisplayed());

        // Force the modal as if we moved cursor outside the window
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("_ouibounce.fire();");

        wait.until(ExpectedConditions.visibilityOf(modal));
        assertTrue(modal.isDisplayed());

        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton));

        modalCloseButton.click();

        assertFalse(modal.isDisplayed());
    }
}
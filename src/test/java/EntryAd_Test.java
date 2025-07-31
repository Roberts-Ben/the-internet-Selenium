import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class EntryAd_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/entry_ad");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/entry_ad", URL);
    }

    @Test
    public void verifyAdOnFirstLoad() throws InterruptedException
    {
        WebElement modalFooter = driver.findElement(By.className("modal-footer"));
        assertFalse(modalFooter.isDisplayed());

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOf(modalFooter));
        assertTrue(modalFooter.isDisplayed());

        Thread.sleep(2000);
        modalFooter.click();

        wait.until(ExpectedConditions.invisibilityOf(modalFooter));
        assertFalse(modalFooter.isDisplayed());
    }

    @Test
    public void verifyAdOnlyAppearsOnce() throws InterruptedException
    {
        WebElement modalFooter = driver.findElement(By.className("modal-footer"));
        assertFalse(modalFooter.isDisplayed());

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOf(modalFooter));
        assertTrue(modalFooter.isDisplayed());

        Thread.sleep(2000);
        modalFooter.click();

        wait.until(ExpectedConditions.invisibilityOf(modalFooter));
        assertFalse(modalFooter.isDisplayed());

        Thread.sleep(2000); // Force a wait here otherwise we refresh too quickly and the modal triggers again
        driver.navigate().refresh();

        wait.until(ExpectedConditions.stalenessOf(modalFooter));

        modalFooter = driver.findElement(By.id("modal"));

        //Ensure it doesn't appear after the refresh
        CheckModalAfterDelay(modalFooter);
    }

    @Test
    public void verifyAdAppearsAfterRefresh() throws InterruptedException
    {
        WebElement modalFooter = driver.findElement(By.className("modal-footer"));
        assertFalse(modalFooter.isDisplayed());

        // Wait for modal to appear (using the close button as we'll be clicking it right after)
        wait.until(ExpectedConditions.visibilityOf(modalFooter));
        assertTrue(modalFooter.isDisplayed());

        Thread.sleep(2000);
        modalFooter.click();

        wait.until(ExpectedConditions.invisibilityOf(modalFooter));
        assertFalse(modalFooter.isDisplayed());

        Thread.sleep(2000);
        // Re-enable the popup
        WebElement resetButton = driver.findElement(By.id("restart-ad"));
        resetButton.click();

        Thread.sleep(2000);
        driver.navigate().refresh();

        wait.until(ExpectedConditions.stalenessOf(modalFooter));

        // Wait for modal to appear
        modalFooter = driver.findElement(By.className("modal-footer"));
        wait.until(ExpectedConditions.visibilityOf(modalFooter));
        assertTrue(modalFooter.isDisplayed());
    }

    private void CheckModalAfterDelay(WebElement modal)
    {
        int timeElapsed = 0;
        int timeout = 5;

        while (timeElapsed < timeout)
        {
            if(modal.isDisplayed())
            {
                System.out.println("Modal is visible when it shouldn't be");
            }

            try {
                Thread.sleep(1000);  // Wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restore interrupted status
                break;
            }

            timeElapsed++;

            if(timeElapsed == timeout)
            {
                assertFalse(modal.isDisplayed());
            }
        }
    }
}
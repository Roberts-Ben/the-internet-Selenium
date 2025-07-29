import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

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
    public void verifyAdOnFirstLoad()
    {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalCloseButton = driver.findElement(By.className("modal-footer"));

        assertFalse(modal.isDisplayed());

        // Wait for modal to appear
        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton));
        assertTrue(modal.isDisplayed());

        modalCloseButton.click();
        CheckModalNotDisplayed(modal);
    }

    @Test
    public void verifyAdOnlyAppearsOnce()
    {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalCloseButton = driver.findElement(By.className("modal-footer"));

        assertFalse(modal.isDisplayed());

        // Wait for modal to appear
        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton));
        assertTrue(modal.isDisplayed());

        modalCloseButton.click();
        CheckModalNotDisplayed(modal);

        if(driver.getCurrentUrl() != null)
        {
            driver.get(driver.getCurrentUrl());
        }

        modal = driver.findElement(By.id("modal"));

        CheckModalNotDisplayed(modal);

    }

    @Test
    public void verifyAdAppearsAfterRefresh()
    {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalCloseButton = driver.findElement(By.className("modal-footer"));

        assertFalse(modal.isDisplayed());

        // Wait for modal to appear (using the close button as we'll be clicking it right after)
        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton));
        assertTrue(modal.isDisplayed());

        modalCloseButton.click();
        CheckModalNotDisplayed(modal);

        // Re-enable the popup
        WebElement resetButton = driver.findElement(By.id("restart-ad"));
        wait.until(ExpectedConditions.elementToBeClickable(resetButton));
        resetButton.click();

        if(driver.getCurrentUrl() != null)
        {
            driver.get(driver.getCurrentUrl());
        }

        modal = driver.findElement(By.id("modal"));

        // Wait for modal to appear
        wait.until(ExpectedConditions.elementToBeClickable(modalCloseButton));
        assertTrue(modal.isDisplayed());
    }

    private void CheckModalNotDisplayed(WebElement _modal)
    {
        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            shortWait.until(ExpectedConditions.visibilityOf(_modal));
            System.out.println("Modal is visible when it shouldn't be");
        } catch (TimeoutException e) {
            assertFalse(_modal.isDisplayed());
        }
    }
}
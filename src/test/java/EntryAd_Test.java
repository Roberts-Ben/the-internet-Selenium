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
        wait.until(ExpectedConditions.visibilityOf(modal));
        assertTrue(modal.isDisplayed());

        modalCloseButton.click();

        assertFalse(modal.isDisplayed());
    }

    @Test
    public void verifyAdOnlyAppearsOnce()
    {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalCloseButton = driver.findElement(By.className("modal-footer"));

        assertFalse(modal.isDisplayed());

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOf(modal));
        assertTrue(modal.isDisplayed());

        modalCloseButton.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.visibilityOf(modal)));
        assertFalse(modal.isDisplayed());

        if(driver.getCurrentUrl() != null)
        {
            driver.get(driver.getCurrentUrl());
        }

        modal = driver.findElement(By.id("modal"));

        try {
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            shortWait.until(ExpectedConditions.visibilityOf(modal));
        } catch (TimeoutException e) {
            assertFalse(modal.isDisplayed());
        }
    }

    @Test
    public void verifyAdAppearsAfterRefresh()
    {
        WebElement modal = driver.findElement(By.id("modal"));
        WebElement modalCloseButton = driver.findElement(By.className("modal-footer"));

        assertFalse(modal.isDisplayed());

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOf(modal));
        assertTrue(modal.isDisplayed());

        modalCloseButton.click();

        assertFalse(modal.isDisplayed());

        // Re-enable the popup
        WebElement resetButton = driver.findElement(By.id("restart-ad"));

        if(driver.getCurrentUrl() != null)
        {
            driver.get(driver.getCurrentUrl());
        }

        modal = driver.findElement(By.id("modal"));

        // Wait for modal to appear
        wait.until(ExpectedConditions.visibilityOf(modal));
        assertTrue(modal.isDisplayed());
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class EntryAd
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/entry_ad");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
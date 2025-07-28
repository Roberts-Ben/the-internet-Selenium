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

import static org.junit.jupiter.api.Assertions.*;

public class FormAuth_Test
{
    WebDriver driver;
    WebDriverWait wait;

    String username = "tomsmith";
    String password = "SuperSecretPassword!";
    String invalidUsername = "test";
    String invalidPassword = "pass";

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/login", URL);
    }

    @Test
    public void verifyValidAuth()
    {
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));

        InputCredentials(true);

        loginButton.click();

        CheckAlert("Success");
    }

    @Test
    public void verifyInvalidAuth()
    {
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));

        InputCredentials(false);

        loginButton.click();

        CheckAlert("Invalid");
    }

    @Test
    public void verifyLogout()
    {
        WebElement loginButton = driver.findElement(By.className("fa-sign-in"));

        InputCredentials(true);

        loginButton.click();

        WebElement logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[href*='/logout']")));

        logoutButton.click();

        CheckAlert("Logout");
    }

    @Test
    public void verifyUnableToBypass()
    {
        driver.get("https://the-internet.herokuapp.com/secure");
        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/login", URL);

        CheckAlert("Bypass");
    }

    private void InputCredentials(boolean validCredentials)
    {
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));

        usernameField.sendKeys(validCredentials ? username : invalidUsername);
        passwordField.sendKeys(validCredentials ? password : invalidPassword);
    }

    private void CheckAlert(String alertType)
    {
        WebElement alert = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#flash")));
        String alertText = alert.getText();

        assertTrue(alert.isDisplayed());
        switch (alertType)
        {
            case "Success":
                assertTrue(alertText.contains("You logged into a secure area!"));
                break;
            case "Invalid":
                assertTrue(alertText.contains("Your username is invalid!"));
                break;
            case "Bypass":
                assertTrue(alertText.contains("You must login to view the secure area!"));
                break;
            case "Logout":
                assertTrue(alertText.contains("You logged out of the secure area!"));
            default:
                break;
        }
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class ForgotPassword
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/forgot_password");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/forgot_password", URL);
    }

    @Test
    public void verifyForgotPassword()
    {
        String email = "TestUser@TestEmail.com";
        WebElement inputField = driver.findElement(By.id("email"));;
        WebElement retrieveButton = driver.findElement(By.id("form_submit"));

        inputField.sendKeys(email);
        assertEquals(email, inputField.getAttribute("value"));

        retrieveButton.click();

        WebElement pageRefreshHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1")));

        assertEquals("Internal Server Error", pageRefreshHeader.getText());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
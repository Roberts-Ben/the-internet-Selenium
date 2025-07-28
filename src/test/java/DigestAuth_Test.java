import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class DigestAuth_Test
{
    WebDriver driver;
    WebDriverWait wait;

    String baseURL = "the-internet.herokuapp.com/digest_auth";;
    String username = "admin";
    String password = "admin";

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Test
    public void verifyAuthSuccessViaDirectURL()
    {
        String authURL = "https://" + username + ":" + password + "@" + baseURL;

        driver.get(authURL);

        WebElement authSuccessContent = driver.findElement(By.cssSelector("p"));
        assertEquals("Congratulations! You must have the proper credentials.", authSuccessContent.getText());
    }

    @Test
    public void verifyAuthSuccessViaHasAuthentication()
    {
        ((HasAuthentication)driver).register(UsernameAndPassword.of(username, password));

        driver.get("https://" + baseURL);

        WebElement authSuccessContent = driver.findElement(By.cssSelector("p"));
        assertEquals("Congratulations! You must have the proper credentials.", authSuccessContent.getText());
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
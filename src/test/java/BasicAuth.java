import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.UsernameAndPassword;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class BasicAuth
{
    WebDriver driver;
    WebDriverWait wait;

    String baseURL = "the-internet.herokuapp.com/basic_auth";;
    String username = "admin";;
    String password = "admin";;

    @Before
    public void Setup() throws Exception
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

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
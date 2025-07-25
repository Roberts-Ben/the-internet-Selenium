import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.*;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class StatusCodes
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/status_codes");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/status_codes", URL);
    }

    @Test
    public void verifyCode200()
    {
        WebElement code200Link = driver.findElement(By.xpath("//a[@href='status_codes/200']"));

        code200Link.click();

        ConfirmCode(200);
    }

    @Test
    public void verifyCode301()
    {
        WebElement code301Link = driver.findElement(By.xpath("//a[@href='status_codes/301']"));

        code301Link.click();

        ConfirmRedirectCode(301);
    }

    @Test
    public void verifyCode404()
    {
        WebElement code404Link = driver.findElement(By.xpath("//a[@href='status_codes/404']"));

        code404Link.click();

        ConfirmCode(404);
    }

    @Test
    public void verifyCode500()
    {
        WebElement code500Link = driver.findElement(By.xpath("//a[@href='status_codes/500']"));

        code500Link.click();

        ConfirmCode(500);
    }

    private void ConfirmCode(int expectedCode)
    {
        int statusCode = RestAssured.given().when().get(driver.getCurrentUrl()).statusCode();

        assertEquals(expectedCode, statusCode);
    }

    private void ConfirmRedirectCode(int expectedCode)
    {
        try {
            // Verify 301 with no redirect
            URL url = new URL(driver.getCurrentUrl());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setInstanceFollowRedirects(false);

            int responseCode = connection.getResponseCode();

            assertEquals(expectedCode, responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
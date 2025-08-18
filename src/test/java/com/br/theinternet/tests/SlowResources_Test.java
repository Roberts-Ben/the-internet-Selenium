package com.br.theinternet.tests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

// NOTE: Currently this page fails with a 503 rather than loading the expected elements
// To test around this:
// Navigate directly to the page that calls the GET request and validating that it fails after 30 seconds instead of loading the content
public class SlowResources_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        driver.get("https://the-internet.herokuapp.com/slow");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/slow", URL);
    }

    @Test
    public void verifySlowElement()
    {
        int status = RestAssured.get("https://the-internet.herokuapp.com/slow_external").getStatusCode();

        assertEquals(503, status);
    }
}
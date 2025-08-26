package com.br.theinternet.tests;

import com.br.theinternet.pages.StatusCodesPage;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.*;

import static org.junit.jupiter.api.Assertions.*;

public class StatusCodes_Test extends BaseTest
{
    private StatusCodesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/status_codes";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new StatusCodesPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyCode200()
    {
        clickAndConfirmCode(200);
    }

    @Test
    public void verifyCode301()
    {
        clickAndConfirmRedirectCode(301);
    }

    @Test
    public void verifyCode404()
    {
        clickAndConfirmCode(404);
    }

    @Test
    public void verifyCode500()
    {
        clickAndConfirmCode(500);
    }

    private void clickAndConfirmCode(int expectedCode)
    {
        page.clickLink(expectedCode);

        int statusCode = RestAssured.given().when().get(page.getCurrentURL()).statusCode();
        assertEquals(expectedCode, statusCode);
    }

    private void clickAndConfirmRedirectCode(int expectedCode)
    {
        page.clickLink(expectedCode);

        try {
            // Verify 301 with no redirect
            URL url = new URL(page.getCurrentURL());

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setInstanceFollowRedirects(false);

            int responseCode = connection.getResponseCode();

            assertEquals(expectedCode, responseCode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
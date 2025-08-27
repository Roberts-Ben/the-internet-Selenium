package com.br.theinternet.pages;

import io.restassured.RestAssured;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class StatusCodesPage extends BasePage
{
    private String codeLinks = "//div[@class='example']//a[@href='status_codes/";

    public StatusCodesPage(WebDriver driver)
    {
        super(driver);
    }

    // Click a specific code link
    public void clickLink(int code)
    {
        String xpath = codeLinks + code + "']";
        click(By.xpath(xpath));
    }

    // Returns status code via RestAssured for normal requests
    public int getStatusCode(int code)
    {
        clickLink(code);
        return RestAssured.given().when().get(getCurrentURL()).statusCode();
    }

    // Returns status code via HttpURLConnection without following redirects
    public int getRedirectStatusCode(int code)
    {
        clickLink(code);

        try {
            URL url = new URL(getCurrentURL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setInstanceFollowRedirects(false);

            return connection.getResponseCode();

        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
package com.br.theinternet.pages;

import io.restassured.RestAssured;
import org.openqa.selenium.WebDriver;

public class SlowResourcesPage extends BasePage
{
    public SlowResourcesPage(WebDriver driver)
    {
        super(driver);
    }

    public int getSlowResourceStatus (String url)
    {
        return RestAssured.get(url).getStatusCode();
    }
}

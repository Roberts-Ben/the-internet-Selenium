package com.br.theinternet.tests;

import com.br.theinternet.pages.RedirectLinkPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class RedirectLink_Test extends BaseTest
{
    private RedirectLinkPage page;

    private static final String URL = "https://the-internet.herokuapp.com/redirector";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new RedirectLinkPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyRedirect()
    {
        WebElement redirectLink = driver.findElement(By.id("redirect"));
        String redirectURL = "https://the-internet.herokuapp.com/status_codes";

        redirectLink.click();

        assertEquals(redirectURL, page.getCurrentURL());
    }
}
package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class ForgotPassword_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/forgot_password");

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
}
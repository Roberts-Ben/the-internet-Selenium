package com.br.theinternet.tests;

import com.br.theinternet.pages.JavascriptAlertsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptAlerts_Test extends BaseTest
{
    private JavascriptAlertsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/javascript_alerts";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new JavascriptAlertsPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyJSAlert()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS Alert", alert.getText());

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You successfully clicked an alert", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSConfirmOK()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS Confirm", alert.getText());

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You clicked: Ok", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSConfirmCancel()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS Confirm", alert.getText());

        alert.dismiss();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You clicked: Cancel", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSPromptAccept()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS prompt", alert.getText());

        alert.sendKeys("Test");

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You entered: Test", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSPromptEmpty()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS prompt", alert.getText());

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You entered:", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSPromptCancel()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS prompt", alert.getText());

        alert.dismiss();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You entered: null", alertSuccessLabel.getText());
    }
}
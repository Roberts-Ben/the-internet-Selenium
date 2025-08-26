package com.br.theinternet.tests;

import com.br.theinternet.pages.WYSIWYGEditorPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class WYSIWYGEditor_Test extends BaseTest
{
    private WYSIWYGEditorPage page;

    private static final String URL = "https://the-internet.herokuapp.com/tinymce";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new WYSIWYGEditorPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyEditor()
    {
        // Need to close the TinyMCE read-only warning before interacting with other elements
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button//div[@aria-label='Close']/..")));
        closeButton.click();

        WebElement iframe = driver.findElement(By.id("mce_0_ifr"));
        driver.switchTo().frame("mce_0_ifr");

        WebElement editor = driver.findElement(By.tagName("body"));

        assertEquals("Your content goes here.", editor.getText());
    }
}
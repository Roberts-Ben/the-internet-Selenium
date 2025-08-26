package com.br.theinternet.tests;

import com.br.theinternet.pages.MultipleWindowsPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleWindows_Test extends BaseTest
{
    private MultipleWindowsPage page;

    private static final String URL = "https://the-internet.herokuapp.com/windows";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new MultipleWindowsPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyNewWindow()
    {
        WebElement newTabButton = driver.findElement(By.xpath("//a[@href='/windows/new']"));

        newTabButton.click();

        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        WebElement newTabHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h3")));
        assertEquals("New Window",newTabHeader.getText());

        driver.close();
    }
}
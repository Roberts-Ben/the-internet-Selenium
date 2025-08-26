package com.br.theinternet.tests;

import com.br.theinternet.pages.TyposPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class Typos_Test extends BaseTest
{
    private TyposPage page;

    private static final String URL = "https://the-internet.herokuapp.com/typos";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new TyposPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyText()
    {
        WebElement content = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]"));
        String actualText = content.getText();
        String expectedText = "Sometimes you'll see a typo, other times you won't.";

        if(expectedText.equals(actualText))
        {
            System.out.println("Content matches");
            assertEquals(expectedText, actualText);
        }
        else
        {
            System.out.println("Typo in: " + actualText);
            assertNotEquals(expectedText, actualText);
        }
    }
}
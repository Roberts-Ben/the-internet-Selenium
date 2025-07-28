import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengingDOM_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/challenging_dom");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/challenging_dom", URL);
    }

    @Test
    public void verifyButton()
    {
        WebElement button = driver.findElement(By.xpath("//a[@class='button']"));
        assertTrue(button.isDisplayed());

        button.click();
    }

    @Test
    public void verifyAlertButton()
    {
        WebElement alertButton = driver.findElement(By.xpath("//a[@class='button alert']"));
        assertTrue(alertButton.isDisplayed());

        alertButton.click();
    }

    @Test
    public void verifySuccessButton()
    {
        WebElement successButton = driver.findElement(By.xpath("//a[@class='button success']"));
        assertTrue(successButton.isDisplayed());

        successButton.click();
    }

    @Test
    public void verifyTable()
    {
        int tableSize = 10;
        String baseURL = driver.getCurrentUrl();

        WebElement tableLoremHeader = driver.findElement(By.xpath("//th[contains(text(),'Lorem')]"));
        List<WebElement> tableLorem = driver.findElements(By.xpath("//td[contains(text(),'Iuvaret')]"));

        List<WebElement> tableEditAction = driver.findElements(By.xpath("//a[text()='edit']"));
        List<WebElement> tableDeleteAction = driver.findElements(By.xpath("//a[text()='delete']"));

        assertTrue(tableLoremHeader.isDisplayed());

        tableEditAction.getFirst().click();
        assertEquals(baseURL +  "#edit", driver.getCurrentUrl());

        tableDeleteAction.getFirst().click();
        assertEquals(baseURL +  "#delete", driver.getCurrentUrl());

        for(int i = 0; i < tableSize; i++)
        {
            assertEquals("Iuvaret" + i, tableLorem.get(i).getText());
        }
    }

    @Test
    public void verifyCanvas()
    {
        WebElement canvas = driver.findElement(By.id("canvas"));
        assertTrue(canvas.isDisplayed());
    }
}
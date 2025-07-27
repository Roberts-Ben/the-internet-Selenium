import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengingDOM
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

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

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
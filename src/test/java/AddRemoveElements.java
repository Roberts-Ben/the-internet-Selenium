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
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class AddRemoveElements
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/add_remove_elements/", URL);
    }

    @Test
    public void verifyAddElement()
    {
        AddElement();

        WebElement deleteButton = driver.findElement(By.cssSelector("button[onclick='deleteElement()']"));

        assertTrue(deleteButton.isDisplayed());
    }

    @Test
    public void verifyAddMultipleElement()
    {
        int elementsToAdd = 10;

        for(int i = 0; i < elementsToAdd; i++)
        {
            AddElement();
        }

        List<WebElement> deleteButtons = driver.findElements(By.cssSelector("button[onclick='deleteElement()']"));

        assertEquals(elementsToAdd, deleteButtons.size());
    }

    @Test
    public void verifyDeleteElement()
    {
        AddElement();

        List<WebElement> deleteButton = driver.findElements(By.cssSelector("button[onclick='deleteElement()']"));

        assertEquals(1, deleteButton.size());

        deleteButton.getFirst().click();

        deleteButton.clear();
        deleteButton = driver.findElements(By.cssSelector("button[onclick='deleteElement()']"));

        assertEquals(0, deleteButton.size());
    }

    @Test
    public void verifyDeleteAllElements()
    {
        int elementsToAdd = 10;

        for(int i = 0; i < elementsToAdd; i++)
        {
            AddElement();
        }

        List<WebElement> deleteButtons = GetDeleteButtons();

        assertEquals(elementsToAdd, deleteButtons.size());

        for(int i = 0; i < elementsToAdd; i++)
        {
            DeleteElement(deleteButtons.get(i));
        }

        deleteButtons.clear();
        deleteButtons = GetDeleteButtons();

        assertEquals(0, deleteButtons.size());
    }

    @Test
    public void verifyDeleteRandomElements()
    {
        int elementsToAdd = 10;
        int elementsToDelete = 3;

        for(int i = 0; i < elementsToAdd; i++)
        {
            AddElement();
        }

        List<WebElement> deleteButtons = GetDeleteButtons();

        assertEquals(elementsToAdd, deleteButtons.size());

        for(int i = 0; i < elementsToDelete; i++)
        {
            int randomIndex = ThreadLocalRandom.current().nextInt(0, elementsToAdd + 1);
            DeleteElement(deleteButtons.get(randomIndex));
        }

        deleteButtons.clear();
        deleteButtons = GetDeleteButtons();

        assertEquals(elementsToAdd - elementsToDelete, deleteButtons.size());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }

    private void AddElement()
    {
        WebElement addButton = driver.findElement(By.cssSelector("button[onclick='addElement()']"));

        addButton.click();
    }

    private List<WebElement> GetDeleteButtons()
    {
        return driver.findElements(By.cssSelector("button[onclick='deleteElement()']"));
    }

    private void DeleteElement(WebElement elementToDelete)
    {
        elementToDelete.click();
    }
}
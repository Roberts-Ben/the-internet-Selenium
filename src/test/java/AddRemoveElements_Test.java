import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

public class AddRemoveElements_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

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
            int randomIndex = ThreadLocalRandom.current().nextInt(0, deleteButtons.size());

            if(IsElementStale(deleteButtons.get(randomIndex)))
            {
                i--; // Attempt another removal as this element was already removed
            }
            else
            {
                DeleteElement(deleteButtons.get(randomIndex));
            }
        }

        deleteButtons.clear();
        deleteButtons = GetDeleteButtons();

        assertEquals(elementsToAdd - elementsToDelete, deleteButtons.size());
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

    private boolean IsElementStale(WebElement element)
    {
        return Boolean.TRUE.equals(ExpectedConditions.stalenessOf(element).apply(driver));
    }
}
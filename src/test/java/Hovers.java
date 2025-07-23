import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Hovers
{
    WebDriver driver;
    WebDriverWait wait;

    String userURL = "https://the-internet.herokuapp.com/hovers/users/";

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/hovers");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/hovers", URL);
    }

    @Test
    public void verifyHover()
    {
        List<WebElement> hoverElements = driver.findElements(By.className("figure"));
        List<WebElement> hiddenElements = driver.findElements(By.className("figcaption"));

        Actions action = new Actions(driver);

        // Verify all hidden hover info is hidden
        for(WebElement hiddenElement : hiddenElements)
        {
            assertFalse(hiddenElement.isDisplayed());
        }

        // Loop through each hover element
        for(int i = 0 ; i < hoverElements.size(); i++)
        {
            // Hover over it and confirm only the hidden info attached to that element is visible
            action.moveToElement(hoverElements.get(i)).build().perform();
            ConfirmElementVisibility(hiddenElements, i);

            NavigateToLink(i);
        }
    }

    private void NavigateToLink(int elementID)
    {
        String finalURL = userURL + elementID;
        driver.get(finalURL);

        assertEquals(finalURL, driver.getCurrentUrl());

        driver.navigate().back();
    }

    private void ConfirmElementVisibility(List<WebElement> elements, int currentIndex)
    {
        for(int i = 0 ; i < elements.size(); i++)
        {
            if(i == currentIndex)
            {
                assertTrue(elements.get(i).isDisplayed());
            }
            else
            {
                assertFalse(elements.get(i).isDisplayed());
            }
        }
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BrokenImages_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/broken_images");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/broken_images", URL);
    }

    @Test
    public void verifyImages ()
    {
        List<WebElement> images = driver.findElements(By.cssSelector(("img[src]")));

        for (WebElement image : images)
        {
            String imageURL = image.getAttribute("src");

            if(imageURL != null)
            {
                ((JavascriptExecutor) driver).executeScript("window.open()");

                ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
                driver.switchTo().window(tabs.get(1));
                driver.get(imageURL);

                VerifyImageStatus(imageURL);

                driver.close();
                driver.switchTo().window(tabs.getFirst());
            }
        }
    }

    private void VerifyImageStatus(String imgURL)
    {
        int statusCode = RestAssured.given().when().get(imgURL).statusCode();

        if(statusCode == 200)
        {
            List<WebElement> image = driver.findElements(By.cssSelector("img"));
            assertEquals(1, image.size());
        }
        else if(statusCode == 404)
        {
            WebElement header = driver.findElement(By.cssSelector("h1"));
            assertEquals("Not Found", header.getText());
        }
    }
}
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

public class RedirectLink_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/redirector");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/redirector", URL);
    }

    @Test
    public void verifyRedirect()
    {
        WebElement redirectLink = driver.findElement(By.id("redirect"));
        String redirectURL = "https://the-internet.herokuapp.com/status_codes";

        redirectLink.click();

        assertEquals(redirectURL, driver.getCurrentUrl());
    }
}
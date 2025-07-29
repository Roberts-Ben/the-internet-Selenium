import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class MultipleWindows_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/windows");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/windows", URL);
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
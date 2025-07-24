import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptAlerts
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/javascript_alerts", URL);
    }

    @Test
    public void verifyJSAlert()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsAlert()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS Alert", alert.getText());

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You successfully clicked an alert", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSConfirmOK()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS Confirm", alert.getText());

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You clicked: Ok", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSConfirmCancel()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsConfirm()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS Confirm", alert.getText());

        alert.dismiss();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You clicked: Cancel", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSPromptAccept()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS prompt", alert.getText());

        alert.sendKeys("Test");

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You entered: Test", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSPromptEmpty()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS prompt", alert.getText());

        alert.accept();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You entered:", alertSuccessLabel.getText());
    }

    @Test
    public void verifyJSPromptCancel()
    {
        WebElement jsAlert = driver.findElement(By.xpath("//button[@onclick='jsPrompt()']"));

        jsAlert.click();

        Alert alert = driver.switchTo().alert();

        assertEquals("I am a JS prompt", alert.getText());

        alert.dismiss();

        WebElement alertSuccessLabel = driver.findElement(By.id("result"));

        assertEquals("You entered: null", alertSuccessLabel.getText());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
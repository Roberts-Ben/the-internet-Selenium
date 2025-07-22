import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileUpload
{
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/upload");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/upload", URL);
    }

    @Test
    public void verifyUpload()
    {
        File fileToUpload = new File("src/main/resources/testuploadfile/testFile.txt");
        String fileString = fileToUpload.toString();
        String fileName = fileString.substring(fileString.lastIndexOf('\\') + 1);

        WebElement chooseFileButton = driver.findElement(By.id("file-upload"));
        WebElement uploadButton = driver.findElement(By.id("file-submit"));

        chooseFileButton.sendKeys(fileToUpload.getAbsolutePath());

        uploadButton.click();

        WebElement uploadedFile = driver.findElement(By.id("uploaded-files"));
        assertEquals(uploadedFile.getText(), fileName);
    }

    @Test
    public void verifyEmptyUpload()
    {
        WebElement uploadButton = driver.findElement(By.id("file-submit"));
        uploadButton.click();

        wait.until(ExpectedConditions.stalenessOf(uploadButton));
        WebElement header = driver.findElement(By.cssSelector("h1"));

        assertEquals("Internal Server Error", header.getText());
    }

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
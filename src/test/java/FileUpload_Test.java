import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileUpload_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/upload");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/upload", URL);
    }

    @Test
    public void verifyUpload()
    {
        File fileToUpload = new File("src/main/resources/testuploadfile/testFile.txt");
        String fileString = fileToUpload.toString();
        String fileName = fileString.substring(Math.max(fileString.lastIndexOf('/'), fileString.lastIndexOf('\\')) + 1); // Account for both path structures

        System.out.println("Attempting to upload file: " + fileToUpload.getAbsolutePath());

        WebElement chooseFileButton = driver.findElement(By.id("file-upload"));
        WebElement uploadButton = driver.findElement(By.id("file-submit"));

        chooseFileButton.sendKeys(fileToUpload.getAbsolutePath());

        uploadButton.click();

        WebElement uploadedFile = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("uploaded-files")));

        System.out.println("Uploaded file name shown on page: " + uploadedFile.getText());

        assertTrue(uploadedFile.getText().contains(fileName));
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
}
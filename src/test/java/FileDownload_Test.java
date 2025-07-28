import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class FileDownload_Test
{
    private static final Log log = LogFactory.getLog(FileDownload_Test.class);
    WebDriver driver;
    WebDriverWait wait;
    String downloadDirectory;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/download");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/download", URL);

        downloadDirectory = "C:/Users/" + System.getProperty("user.name") + "/Downloads/";
    }

    @Test
    public void verifyFileDownload()
    {
        Pattern pattern = Pattern.compile("\\.(jpg|png|txt|json|xlsx|pdf|.mp4|tmp|java|docx|jpeg|zip|doc)$", Pattern.CASE_INSENSITIVE);

        List<WebElement> downloadButtons = driver.findElements(By.xpath("//a[@href]"));

        // Download and verify files
        for (WebElement downloadButton : downloadButtons)
        {
            String downloadLink = downloadButton.getAttribute("href");
            String encodedFileName = downloadLink.substring(downloadLink.lastIndexOf('/') + 1);
            String fileName = URLDecoder.decode(encodedFileName, StandardCharsets.UTF_8);

            Matcher matcher = pattern.matcher(fileName);

            if (matcher.find())
            {
                assertFalse(isFileDownloaded(fileName, false));

                downloadButton.click();
                System.out.println(fileName);

                assertTrue(isFileDownloaded(fileName, true));
            }
        }
    }

    boolean isFileDownloaded(String fileName, boolean shouldExist)
    {
        File dir = new File(downloadDirectory);
        File targetFile = new File(dir, fileName);

        int timeElapsed = 0;
        int timeout = 10;

        if(!shouldExist) // Break out early on the false check as we don't need to wait for the file to download
        {
            return targetFile.exists();
        }

        while (timeElapsed < timeout)
        {
            if (targetFile.exists())
            {
                targetFile.delete();
                return true;
            }

            try {
                Thread.sleep(1000);  // Wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restore interrupted status
                break;
            }

            timeElapsed++;
        }

        return false;
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class SecureFileDownload
{
    WebDriver driver;
    WebDriverWait wait;
    ChromeOptions chromeOptions;

    String downloadDirectory;

    String baseURL = "the-internet.herokuapp.com/download_secure";;
    String username = "admin";
    String password = "admin";

    @Before
    public void Setup() throws Exception
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        downloadDirectory = "C:/Users/" + System.getProperty("user.name") + "/Downloads/";
    }

    @Test
    public void verifySecureLoginDownload()
    {
        String authURL = "https://" + username + ":" + password + "@" + baseURL;

        driver.get(authURL);

        Pattern pattern = Pattern.compile("\\.(jpg|png|txt|json|xlsx|pdf|mp4|zip|py|exe|docx|jpeg|csv|sol)$", Pattern.CASE_INSENSITIVE);

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

                assertTrue(isFileDownloaded(fileName, true));
            }
        }
    }

    boolean isFileDownloaded(String fileName, boolean shouldExist)
    {
        File dir = new File(downloadDirectory);
        File targetFile = new File(dir, fileName);

        int timeElapsed = 0;
        int timeout = 5;

        if(!shouldExist) // Break out early on the false check as we don't need to wait for the file to download
        {
            return targetFile.exists();
        }

        while (timeElapsed < timeout)
        {
            // File successfully downloaded
            if (targetFile.exists())
            {
                targetFile.delete();
                return true;
            }

            // Check partial/blocked downloads i.e. an .exe
            File[] crdownloads = dir.listFiles((d, name) -> name.startsWith(fileName) || name.endsWith(".crdownload"));
            if (crdownloads != null)
            {
                for (File partial : crdownloads) {
                    if (partial.getName().contains(fileName) || partial.getName().endsWith(".crdownload"))
                    {
                        partial.delete();
                        return true;
                    }
                }
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

    @After
    public void Teardown()
    {
        driver.quit();
    }
}
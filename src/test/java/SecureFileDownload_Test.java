import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class SecureFileDownload_Test extends BaseTest
{
    String baseURL = "the-internet.herokuapp.com/download_secure";
    String username = "admin";
    String password = "admin";

    @Test
    public void verifySecureLoginDownload()
    {
        String authURL = "https://" + username + ":" + password + "@" + baseURL;

        driver.get(authURL);

        List<WebElement> downloadButtons = driver.findElements(By.xpath("//a[@href]"));

        int totalFiles = downloadButtons.size();
        int currentFile = 0;

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
                System.out.println(fileName + " - " + currentFile + "/" + totalFiles);

                assertTrue(isFileDownloaded(fileName, true));
            }
            else
            {
                System.out.println("Invalid file: " + fileName);
            }
            currentFile++;
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

        System.out.println("File should be downloaded but is not: " + fileName);
        return false;
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.FileDownloadPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SecureFileDownload_Test extends BaseTest
{
    String baseURL = "the-internet.herokuapp.com/download_secure";
    String username = "admin";
    String password = "admin";

    @Test
    public void verifySecureLoginDownload()
    {
        FileDownloadPage page = new FileDownloadPage(driver);

        String authURL = "https://" + username + ":" + password + "@" + baseURL;

        page.navigateTo(authURL);

        List<WebElement> downloadButtons = page.getDownloadButtons();

        int totalFiles = downloadButtons.size();
        int currentFile = 0;

        // Download and verify files
        for (WebElement downloadButton : downloadButtons)
        {
            String fileName = page.extractFileName(downloadButton);

            if (page.isValidFileName(fileName))
            {
                assertFalse(isFileDownloaded(fileName, false));

                page.clickDownload(downloadButton);
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
}
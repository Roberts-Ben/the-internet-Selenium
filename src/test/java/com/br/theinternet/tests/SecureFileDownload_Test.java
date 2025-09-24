package com.br.theinternet.tests;

import com.br.theinternet.pages.FileDownloadPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class SecureFileDownload_Test extends BaseTest
{
    FileDownloadPage page;

    String baseURL = "the-internet.herokuapp.com/download_secure";
    String username = "admin";
    String password = "admin";

    @Test
    public void verifySecureLoginDownload() throws Exception
    {
        page = initPage(browser, "", FileDownloadPage.class);

        String authURL = "https://" + username + ":" + password + "@" + baseURL;

        page.navigateTo(authURL);
        page.waitForPageLoad();

        Map<String, WebElement> filesToDownload = page.getLargestFilePerExtension();
        int totalFiles = filesToDownload.size();
        int currentFile = 1;

        // Download and verify files
        for (Map.Entry<String, WebElement> entry : filesToDownload.entrySet())
        {
            String ext = entry.getKey();
            WebElement downloadButton = entry.getValue();
            String fileName = page.extractFileName(downloadButton);

            System.out.println("Downloading largest " + ext + " file: " + fileName);
            page.clickDownload(downloadButton);
            System.out.println(fileName + " - " + currentFile + "/" + totalFiles);
            currentFile++;

            assertTrue(isFileDownloaded(fileName, true));
        }
    }
}
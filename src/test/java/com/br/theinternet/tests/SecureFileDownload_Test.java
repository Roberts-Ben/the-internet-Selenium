package com.br.theinternet.tests;

import com.br.theinternet.pages.FileDownloadPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SecureFileDownload_Test extends BaseTest
{
    private FileDownloadPage page;

    String baseURL = "the-internet.herokuapp.com/download_secure";
    String username = "admin";
    String password = "admin";

    @ParameterizedTest(name = "verifySecureLoginDownload: {0}")
    @EnumSource(BrowserType.class)
    public void verifySecureLoginDownload(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, "", FileDownloadPage.class);

        // Test
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
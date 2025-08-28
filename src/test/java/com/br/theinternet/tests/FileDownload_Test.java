package com.br.theinternet.tests;

import com.br.theinternet.pages.FileDownloadPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FileDownload_Test extends BaseTest
{
    private FileDownloadPage page;

    private static final String URL = "https://the-internet.herokuapp.com/download";

    @ParameterizedTest(name = "verifyFileDownload: {0}")
    @EnumSource(BrowserType.class)
    public void verifyFileDownload(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FileDownloadPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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
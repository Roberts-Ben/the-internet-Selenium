package com.br.theinternet.tests;

import com.br.theinternet.pages.FileDownloadPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FileDownload_Test extends BaseTest
{
    private FileDownloadPage page;

    private static final String URL = "https://the-internet.herokuapp.com/download";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, FileDownloadPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyFileDownload()
    {
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
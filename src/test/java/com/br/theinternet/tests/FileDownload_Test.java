package com.br.theinternet.tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

public class FileDownload_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/download");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/download", URL);
    }

    @Test
    public void verifyFileDownload()
    {
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
}
package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public class FileDownloadPage extends BasePage
{
    private By downloadButtons = By.xpath("//a[@href]");

    public FileDownloadPage(WebDriver driver)
    {
        super(driver);
    }

    public List<WebElement> getDownloadButtons()
    {
        return findAll(downloadButtons);
    }

    public String extractFileName(WebElement downloadButton)
    {
        String downloadLink = downloadButton.getAttribute("href");
        String encodedFileName = downloadLink.substring(downloadLink.lastIndexOf('/') + 1);
        return URLDecoder.decode(encodedFileName, StandardCharsets.UTF_8);
    }

    public boolean isValidFileName(String fileName)
    {
        Matcher matcher = pattern.matcher(fileName);
        return matcher.find();
    }

    public void clickDownload(WebElement downloadButton)
    {
        downloadButton.click();
    }

    public Map<String, WebElement> getLargestFilePerExtension()
    {
        List<WebElement> buttons = getDownloadButtons();
        Map<String, WebElement> largestFilePerExt = new HashMap<>();
        Map<String, Long> largestSizePerExt = new HashMap<>();

        for (WebElement button : buttons)
        {
            String fileName = extractFileName(button);
            Matcher matcher = pattern.matcher(fileName);

            if (matcher.find())
            {
                String ext = matcher.group(1).toLowerCase();
                try
                {
                    String fileUrl = button.getAttribute("href");
                    HttpURLConnection connection = (HttpURLConnection) new URL(fileUrl).openConnection();
                    connection.setRequestMethod("HEAD");
                    long contentLength = connection.getContentLengthLong();

                    if (!largestSizePerExt.containsKey(ext) || contentLength > largestSizePerExt.get(ext))
                    {
                        largestSizePerExt.put(ext, contentLength);
                        largestFilePerExt.put(ext, button);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Failed to get size for " + fileName + ": " + e.getMessage());
                }
            }
        }

        return largestFilePerExt;
    }
}

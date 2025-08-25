package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
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
}

package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class FileUploadPage extends BasePage
{
    private By chooseFileButtonBy = By.id("file-upload");
    private By uploadButtonBy = By.id("file-submit");
    private By uploadedFileBy = By.id("uploaded-files");
    private By headerBy = By.cssSelector("h1");

    public FileUploadPage(WebDriver driver)
    {
        super(driver);
    }

    public void selectFileToUpload(File file)
    {
        type(chooseFileButtonBy, file.getAbsolutePath());
    }

    public void clickUpload()
    {
        click(uploadButtonBy);
    }

    public String getUploadedFileName()
    {
        waitForVisible(uploadedFileBy);
        return getText(uploadedFileBy);
    }

    public String getHeaderText()
    {
        waitForVisible(headerBy);
        return getText(headerBy);
    }
}

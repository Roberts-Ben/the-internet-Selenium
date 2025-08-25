package com.br.theinternet.tests;

import com.br.theinternet.pages.FileUploadPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileUpload_Test extends BaseTest
{
    private FileUploadPage page;

    private static final String URL = "https://the-internet.herokuapp.com/upload";

    @BeforeEach
    public void setup() throws Exception
    {
        page = new FileUploadPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, driver.getCurrentUrl());
    }

    @Test
    public void verifyUpload()
    {
        File fileToUpload = new File("src/main/resources/testuploadfile/testFile.txt");
        String fileName = fileToUpload.getName();

        System.out.println("Attempting to upload file: " + fileToUpload.getAbsolutePath());

        page.selectFileToUpload(fileToUpload);
        page.clickUpload();

        assertTrue(page.getUploadedFileName().contains(fileName));
    }

    @Test
    public void verifyEmptyUpload()
    {
        page.clickUpload();

        assertEquals("Internal Server Error", page.getHeaderText());
    }
}
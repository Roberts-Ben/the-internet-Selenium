package com.br.theinternet.tests;

import com.br.theinternet.pages.FileUploadPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileUpload_Test extends BaseTest
{
    private FileUploadPage page;

    private static final String URL = "https://the-internet.herokuapp.com/upload";
    private static final String fileName = "src/main/resources/testuploadfile/testFile.txt";

    @ParameterizedTest(name = "verifyUpload: {0}")
    @EnumSource(BrowserType.class)
    public void verifyUpload(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FileUploadPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        File fileToUpload = new File(fileName);
        String fileName = fileToUpload.getName();

        System.out.println("Attempting to upload file: " + fileToUpload.getAbsolutePath());

        page.selectFileToUpload(fileToUpload);
        page.clickUpload();

        assertTrue(page.getUploadedFileName().contains(fileName));
    }

    @ParameterizedTest(name = "verifyEmptyUpload: {0}")
    @EnumSource(BrowserType.class)
    public void verifyEmptyUpload(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, FileUploadPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickUpload();

        assertEquals("Internal Server Error", page.getHeaderText());
    }
}
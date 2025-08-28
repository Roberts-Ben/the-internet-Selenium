package com.br.theinternet.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Path tempProfile;
    public String downloadDirectory;

    @BeforeEach
    public void setUp() throws Exception
    {
        tempProfile = Files.createTempDirectory("chrome-profile-");
        downloadDirectory = Files.createTempDirectory("downloads").toAbsolutePath().toString();

        ChromeOptions options = getChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private ChromeOptions getChromeOptions() {
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);
        chromePrefs.put("download.default_directory", downloadDirectory);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        return options;
    }

    @AfterEach
    public void tearDown()
    {
        if (driver != null)
        {
            driver.quit();
        }

        // Delete temporary directories
        deleteDirectory(tempProfile);
        deleteDirectory(Path.of(downloadDirectory));
    }

    private void deleteDirectory(Path path) {
        if (path != null && Files.exists(path))
        {
            try
            {
                Files.walk(path).sorted((a, b) -> b.compareTo(a)).forEach(p ->
                {
                    try {
                        Files.delete(p);
                    } catch (IOException e) {
                        System.err.println("Failed to delete: " + p + " - " + e.getMessage());
                    }
                });
            } catch (IOException e) {
                System.err.println("Error walking path: " + path + " - " + e.getMessage());
            }
        }
    }

    protected boolean isFileDownloaded(String fileName, boolean shouldExist)
    {
        File file = new File(downloadDirectory, fileName);

        int timeElapsed = 0;
        int timeout = 10;

        if (!shouldExist)
        {
            return file.exists();
        }

        while (timeElapsed < timeout)
        {
            // Check full file
            if (file.exists())
            {
                file.delete(); // cleanup after verification
                return true;
            }

            // Check for partial files (.crdownload)
            File[] partials = new File(downloadDirectory).listFiles((d, name) -> name.startsWith(fileName) || name.endsWith(".crdownload"));

            if (partials != null)
            {
                for (File p : partials)
                {
                    p.delete();
                    return true; // treat as detected for cleanup
                }
            }

            try {
                Thread.sleep(1000); // wait 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }

            timeElapsed++;
        }

        System.out.println("File should be downloaded but is not: " + fileName);
        return false;
    }
}
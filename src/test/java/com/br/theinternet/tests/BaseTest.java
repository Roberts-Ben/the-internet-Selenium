package com.br.theinternet.tests;

import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class BaseTest
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Path tempProfile;
    public String downloadDirectory;

    public enum BrowserType {
        CHROME,
        EDGE,
        FIREFOX
    }
    protected BrowserType browser = BrowserType.CHROME;

    public void initializeDriver() throws Exception
    {
        tempProfile = Files.createTempDirectory("browser-profile-");
        downloadDirectory = Files.createTempDirectory("downloads").toAbsolutePath().toString();

        String browserName = System.getProperty("browser", "CHROME");
        this.browser = BrowserType.valueOf(browserName);

        driver = createDriver(browser);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    private WebDriver createDriver(BrowserType browserType)
    {
        return switch (browserType) {
            case CHROME -> new ChromeDriver(getChromeOptions());
            case FIREFOX -> new org.openqa.selenium.firefox.FirefoxDriver(getFirefoxOptions());
            case EDGE -> new org.openqa.selenium.edge.EdgeDriver(getEdgeOptions());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserType);
        };
    }

    private ChromeOptions getChromeOptions() {
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        chromePrefs.put("profile.password_manager_leak_detection", false);
        chromePrefs.put("download.default_directory", downloadDirectory);

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--user-data-dir=" + tempProfile.toAbsolutePath());
        options.setExperimentalOption("prefs", chromePrefs);

        return options;
    }

    private EdgeOptions getEdgeOptions()
    {
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDirectory);

        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        options.setExperimentalOption("prefs", prefs);

        return options;
    }

    private FirefoxOptions getFirefoxOptions()
    {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-headless");
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.download.dir", downloadDirectory);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/pdf,application/octet-stream");
        options.addPreference("pdfjs.disabled", true);

        // Fake geolocation provider response
        options.addPreference("geo.enabled", true);
        options.addPreference("geo.prompt.testing", true);
        options.addPreference("geo.prompt.testing.allow", true);

        String fakeGeo = "{ \"location\": { \"lat\": 56.9139, \"lng\": -4.514}, \"accuracy\": 100.0 }";
        options.addPreference("geo.provider.network.url",
                "data:application/json," + fakeGeo);

        return options;
    }

    protected <T> T initPage(BrowserType browserType, String url, Class<T> pageClass) throws Exception
    {
        this.browser = browserType;
        initializeDriver();

        T page = pageClass.getConstructor(WebDriver.class).newInstance(driver);
        if(!Objects.equals(url, ""))
        {
            driver.get(url);
        }
        return page;
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
        File dir = new File(downloadDirectory);

        String normalizedFileName = normalizeFileName(fileName);

        int timeElapsed = 0;
        int timeout = 10;

        if (!shouldExist)
        {
            return getMatchingFile(normalizedFileName, dir) != null;
        }

        while (timeElapsed < timeout)
        {
            // Check full file
            File match = getMatchingFile(normalizedFileName, dir);
            if (match != null)
            {
                match.delete(); // Cleanup after verification
                return true;
            }

            // Check for partial files (.crdownload)
            File[] partials = dir.listFiles((d, name) -> name.endsWith(".crdownload") || name.endsWith(".part"));
            if (partials != null)
            {
                for (File p : partials)
                {
                    p.delete();
                    return true; // Treat as detected for cleanup
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

    private File getMatchingFile(String normalizedExpected, File dir)
    {
        File[] files = dir.listFiles();

        if (files == null)
        {
            return null;
        }

        for (File file : files)
        {
            String normalizedActual = normalizeFileName(file.getName().replaceAll("%20", " ")); // decode URL spaces);

            if (normalizedActual.equals(normalizedExpected))
            {
                return file;
            }
        }
        return null;
    }

    private String normalizeFileName(String name)
    {
        return name.trim().replaceAll("[\\u00A0\\u202F]", " ") // Normalize NBSP/narrow spaces
                .replaceAll("\\s+", " ")  // Collapse all whitespace to one space
                .toLowerCase();                     // Ignore case
    }
}
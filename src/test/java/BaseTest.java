import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public String downloadDirectory;

    @BeforeEach
    public void setUp() throws Exception
    {
        Path tempProfile = Files.createTempDirectory("chrome-profile-");
        downloadDirectory = Files.createTempDirectory("downloads").toAbsolutePath().toString();
        new File(downloadDirectory).mkdirs();

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
    }
}
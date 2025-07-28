import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptOnLoadEventError_Test
{
    private static final Log log = LogFactory.getLog(JavascriptOnLoadEventError_Test.class);
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setup() throws Exception
    {
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/javascript_error");
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/javascript_error", URL);
    }

    @Test
    public void verifyError()
    {
        // Define JavaScript error types to filter
        List<String> errorStrings = Arrays.asList(
                "SyntaxError",
                "EvalError",
                "ReferenceError",
                "RangeError",
                "TypeError",
                "URIError"
        );

        // Get browser logs
        List<LogEntry> browserLogs = driver.manage().logs().get(LogType.BROWSER).getAll();

        // Filter logs for JS errors
        List<LogEntry> jsErrors = browserLogs.stream()
                .filter(log -> errorStrings.stream().anyMatch(err -> log.getMessage().contains(err)))
                .toList();

        // If JS errors found, log
        if (!jsErrors.isEmpty()) {
            String errorMessages = jsErrors.stream()
                    .map(LogEntry::getMessage)
                    .collect(Collectors.joining(System.lineSeparator()));
            System.out.println(errorMessages);
        }

        assertNotEquals(0, jsErrors.size());
    }

    @AfterEach
    public void tearDown()
    {
        driver.quit();
    }
}
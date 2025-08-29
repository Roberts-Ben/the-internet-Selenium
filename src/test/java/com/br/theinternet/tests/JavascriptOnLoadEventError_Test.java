package com.br.theinternet.tests;

import com.br.theinternet.pages.JavascriptOnLoadEventErrorPage;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.logging.LogEntry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptOnLoadEventError_Test extends BaseTest
{
    private JavascriptOnLoadEventErrorPage page;

    private static final String URL = "https://the-internet.herokuapp.com/javascript_error";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, JavascriptOnLoadEventErrorPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyError()
    {
        Assumptions.assumeTrue(browser != BrowserType.FIREFOX, "Skipping test: Firefox does not support Browser logs");

        // Define JavaScript error types to filter
        List<String> errorStrings = Arrays.asList(
                "SyntaxError",
                "EvalError",
                "ReferenceError",
                "RangeError",
                "TypeError",
                "URIError"
        );

        List<LogEntry> jsErrors = page.getBrowserLogs().stream()
                .filter(entry -> errorStrings.stream().anyMatch(err -> entry.getMessage().contains(err)))
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
}
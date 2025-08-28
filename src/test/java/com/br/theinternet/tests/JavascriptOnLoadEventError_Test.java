package com.br.theinternet.tests;

import com.br.theinternet.pages.JavascriptOnLoadEventErrorPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.openqa.selenium.logging.LogEntry;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class JavascriptOnLoadEventError_Test extends BaseTest
{
    private JavascriptOnLoadEventErrorPage page;

    private static final String URL = "https://the-internet.herokuapp.com/javascript_error";

    @ParameterizedTest(name = "verifyError: {0}")
    @EnumSource(BrowserType.class)
    public void verifyError(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, JavascriptOnLoadEventErrorPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
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
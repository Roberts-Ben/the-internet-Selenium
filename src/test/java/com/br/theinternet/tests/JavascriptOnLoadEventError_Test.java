package com.br.theinternet.tests;

import com.br.theinternet.pages.JavascriptOnLoadEventErrorPage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private static final Log log = LogFactory.getLog(JavascriptOnLoadEventError_Test.class);

    @BeforeEach
    public void setup() throws Exception
    {
        page = new JavascriptOnLoadEventErrorPage(driver);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
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
package com.br.theinternet.tests;

import com.br.theinternet.pages.SortableDataTablesPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SortableDataTables_Test extends BaseTest
{
    private SortableDataTablesPage page;

    private static final String URL = "https://the-internet.herokuapp.com/tables";

    @ParameterizedTest(name = "verifyTableWithoutID: {0}")
    @EnumSource(BrowserType.class)
    public void verifyTableWithoutID(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, SortableDataTablesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        // Assert content exists
        List<String> lastNames = page.getColumnContentByIndex("table1", 0);
        assertEquals(4, lastNames.size()); // 4 rows

        // Assert edit/delete buttons trigger
        page.clickEdit("table1", 0);
        assertTrue(page.getCurrentURL().endsWith("#edit"));

        page.clickDelete("table1", 0);
        assertTrue(page.getCurrentURL().endsWith("#delete"));

        // Assert sort buttons function as expected
        page.clickColumnHeader("table1", 0);
        page.delay(100);
        List<String> ascSorted = page.getColumnContentByIndex("table1", 0);
        assertTrue(isSortedAscending(ascSorted));

        page.clickColumnHeader("table1", 0);
        page.delay(100);
        List<String> descSorted = page.getColumnContentByIndex("table1", 0);
        assertTrue(isSortedDescending(descSorted));
    }

    @ParameterizedTest(name = "verifyTableWithID: {0}")
    @EnumSource(BrowserType.class)
    public void verifyTableWithID(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, SortableDataTablesPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        // Assert content exists
        List<String> firstNames = page.getColumnContentByID("table2", "first-name");
        assertEquals(4, firstNames.size()); // 4 rows

        // Assert edit/delete buttons trigger
        page.clickEdit("table2", 0);
        assertTrue(page.getCurrentURL().endsWith("#edit"));

        page.clickDelete("table2", 0);
        assertTrue(page.getCurrentURL().endsWith("#delete"));

        // Assert sort buttons function as expected
        page.clickColumnHeader("table2", 2);
        page.delay(100);
        List<String> ascSorted = page.getColumnContentByID("table2", "email");
        assertTrue(isSortedAscending(ascSorted));

        page.clickColumnHeader("table2", 2);
        page.delay(100);
        List<String> descSorted = page.getColumnContentByID("table2", "email");
        assertTrue(isSortedDescending(descSorted));
    }

    private boolean isSortedAscending(List<String> list)
    {
        return list.equals(list.stream().sorted().toList());
    }

    private boolean isSortedDescending(List<String> list)
    {
        return list.equals(list.stream().sorted(Comparator.reverseOrder()).toList());
    }
}
package com.br.theinternet.tests;

import com.br.theinternet.pages.GeolocationPage;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;

public class Geolocation_Test extends BaseTest
{
    private GeolocationPage page;

    private static final String URL = "https://the-internet.herokuapp.com/geolocation";
    private static final String mapsBaseURL = "http://maps.google.com/";

    @ParameterizedTest(name = "verifyLocation: {0}")
    @EnumSource(BrowserType.class)
    public void verifyLocation(BrowserType browserType) throws Exception
    {
        // Setup
        page = initPage(browserType, URL, GeolocationPage.class);
        assertEquals(URL, page.getCurrentURL());

        // Test
        page.clickLocationButton();

        assertTrue(page.isLatitudeVisible());

        String locationURL = mapsBaseURL + page.getLocation();

        assertEquals(locationURL, page.getMapsLinkAttribute());
    }
}
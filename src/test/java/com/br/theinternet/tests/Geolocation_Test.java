package com.br.theinternet.tests;

import com.br.theinternet.pages.GeolocationPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Geolocation_Test extends BaseTest
{
    private GeolocationPage page;

    private static final String URL = "https://the-internet.herokuapp.com/geolocation";
    private static final String mapsBaseURL = "http://maps.google.com/";

    @BeforeEach
    public void setup() throws Exception
    {
        page = initPage(browser, URL, GeolocationPage.class);
        page.navigateTo(URL);
        assertEquals(URL, page.getCurrentURL());
    }

    @Test
    public void verifyLocation()
    {
        page.clickLocationButton();

        assertTrue(page.isLatitudeVisible());

        String locationURL = mapsBaseURL + page.getLocation();

        assertEquals(locationURL, page.getMapsLinkAttribute());
    }
}
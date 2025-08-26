package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GeolocationPage extends BasePage
{
    private By locationButtonBy = By.xpath("//button[@onclick='getLocation()']");
    private By latValueBy = By.id("lat-value");
    private By longValueBy = By.id("long-value");
    private By mapsLinkBy = By.xpath("//a[contains(text(), 'See it on Google')]");

    public GeolocationPage(WebDriver driver)
    {
        super(driver);
    }

    public void clickLocationButton()
    {
        click(locationButtonBy);
    }

    public boolean isLatitudeVisible()
    {
        return waitForVisible(latValueBy);
    }

    public String getMapsLinkAttribute()
    {
        return getAttribute(mapsLinkBy, "href");
    }

    public String getLocation()
    {
        return "?q=" + getText(latValueBy) + "," + getText(longValueBy);
    }
}

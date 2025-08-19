package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrokenImagesPage extends BasePage
{
    private By imagesBy = By.cssSelector(("img[src]"));

    public BrokenImagesPage(WebDriver driver)
    {
        super(driver);
    }

    public List<WebElement> getAllImages()
    {
        return findAll(imagesBy);
    }
    
    public String getImageURL(WebElement image)
    {
        return image.getAttribute("src");
    }

    public int verifyImageStatus(String imgURL)
    {
        return getStatusCode(imgURL);
    }
}

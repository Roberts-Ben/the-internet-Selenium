package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ShiftingContentPage extends BasePage
{
    private By menuLinkBy = By.xpath("//a[@href='/shifting_content/menu']");
    private By listLinkBy = By.xpath("//a[@href='/shifting_content/list']");
    private By imageLinkBy = By.xpath("//a[@href='/shifting_content/image']");
    private By shiftMenuButtonBy = By.xpath("//a[@href='/shifting_content/menu?pixel_shift=100']");
    private By shiftImageButtonBy = By.xpath("//a[@href='/shifting_content/image?pixel_shift=100']");
    private By imageBy = By.className("shift");
    private By listRootBy = By.tagName("ul");
    private By contentDivBy = By.cssSelector("div.large-6.columns.large-centered");

    JavascriptExecutor js;

    public ShiftingContentPage(WebDriver driver)
    {
        super(driver);

        js = (JavascriptExecutor) driver;
    }

    public void clickMenuLink()
    {
        click(menuLinkBy);
    }

    public void clickImageLink()
    {
        click(imageLinkBy);
    }

    public void clickListLink()
    {
        click(listLinkBy);
    }

    public void clickMenuShift() {
        click(shiftMenuButtonBy);
    }

    public void clickImageShift()
    {
        click(shiftImageButtonBy);
    }

    public List<String> getMenuLinks()
    {
        return find(listRootBy)
                .findElements(By.xpath(".//a[@href]"))
                .stream()
                .map(e -> e.getAttribute("href"))
                .toList();
    }

    public boolean isImageDisplayed()
    {
        return find(imageBy).isDisplayed();
    }

    public List<String> getListItems()
    {
        WebElement contentDiv = find(contentDivBy);
        String innerHTML = contentDiv.getAttribute("innerHTML");

        String[] parts = innerHTML.split("<br\\s*/?>\\s*");
        return Arrays.stream(parts)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public void openWindowAndNavigate(String url)
    {
        js.executeScript("window.open()");
        switchWindow(1);
        navigateTo(url);
    }

    public void closeWindowAndNavigate()
    {
        closeWindow();
        switchWindow(0);
    }
}

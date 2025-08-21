package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class DynamicContentPage extends BasePage
{
    private By avatarsBy = By.xpath("//img[contains(@src, 'avatar')]");
    private By paragraphHolderBy = By.cssSelector(".large-10");
    private By paragraphsBy = By.cssSelector(".large-10.columns");
    private By staticDynamicLinkBy = By.linkText("click here");

    public DynamicContentPage(WebDriver driver)
    {
        super(driver);
    }

    public List<String> getAvatarSources()
    {
        List<WebElement> avatars = findAll(avatarsBy);
        List<String> sources = new ArrayList<>();

        for (WebElement avatar : avatars)
        {
            sources.add(avatar.getAttribute("src"));
        }
        return sources;
    }

    public List<String> getParagraphTexts()
    {
        WebElement paragraphHolder = find(paragraphHolderBy);
        List<WebElement> paragraphs = paragraphHolder.findElements(paragraphsBy);
        List<String> texts = new ArrayList<>();

        for (WebElement paragraph : paragraphs)
        {
            texts.add(paragraph.getText());
        }
        return texts;
    }

    public void enableStaticDynamicMode()
    {
        click(staticDynamicLinkBy);
    }

    public boolean hasContentChanged(List<String> original, List<String> current, boolean skipStatic)
    {
        int startIndex = skipStatic ? 2 : 0; // If we're testing the static route the first 2 will always be the same

        for (int i = startIndex; i < original.size(); i++)
        {
            if (!original.get(i).equals(current.get(i)))
            {
                return true;
            }
        }
        return false;
    }
}

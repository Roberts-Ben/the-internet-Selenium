package com.br.theinternet.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;

public class DragAndDropPage extends BasePage
{
    private By dragColumnABy = By.id("column-a");
    private By dragColumnBBy = By.id("column-b");
    private By dragHeaderABy = By.cssSelector("#column-a > header");
    private By dragHeaderBBy = By.cssSelector("#column-b > header");

    public DragAndDropPage(WebDriver driver)
    {
        super(driver);
    }

    public String getHeaderAText()
    {
        return getText(dragHeaderABy);
    }

    public String getHeaderBText()
    {
        return getText(dragHeaderBBy);
    }

    public void dragAndDrop() throws IOException
    {
        WebElement colA = find(dragColumnABy);
        WebElement colB = find(dragColumnBBy);

        // Try Selenium Actions first
        new Actions(driver).dragAndDrop(colA, colB).build().perform();

        // If it failed, try forcing js injection
        if (!getHeaderAText().equals("B") || !getHeaderBText().equals("A"))
        {
            if (!getHeaderAText().equals("B") || !getHeaderBText().equals("A"))
            {
                System.out.println("Action failed, running dragDropHelper.js");
                ((JavascriptExecutor) driver).executeScript(
                        "function simulateDragDrop(sourceNode, destinationNode) {" +
                                "  const dataTransfer = new DataTransfer();" +
                                "  const dragStartEvent = new DragEvent('dragstart', { bubbles: true, cancelable: true, dataTransfer: dataTransfer });" +
                                "  sourceNode.dispatchEvent(dragStartEvent);" +
                                "  const dropEvent = new DragEvent('drop', { bubbles: true, cancelable: true, dataTransfer: dataTransfer });" +
                                "  destinationNode.dispatchEvent(dropEvent);" +
                                "  const dragEndEvent = new DragEvent('dragend', { bubbles: true, cancelable: true, dataTransfer: dataTransfer });" +
                                "  sourceNode.dispatchEvent(dragEndEvent);" +
                                "}" +
                                "simulateDragDrop(arguments[0], arguments[1]);",
                        colA, colB
                );
            }
        }
    }
}
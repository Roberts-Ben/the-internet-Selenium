package com.br.theinternet.pages;

import io.restassured.RestAssured;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Pattern;

public class BasePage
{
    protected WebDriver driver;
    protected WebDriverWait wait;

    public Pattern pattern;

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        pattern = Pattern.compile("\\.(jpg|png|txt|json|xlsx|pdf|mp4|zip|py|exe|docx|jpeg|csv|sol|tmp|java|doc|class|bin|mov|xml|bmp)$", Pattern.CASE_INSENSITIVE);
    }

    public void navigateTo(String url)
    {
        driver.get(url);
    }

    public void switchWindow(int index)
    {
        Object[] windowHandles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String)windowHandles[index]);
    }

    public void switchToDefault()
    {
        driver.switchTo().defaultContent();
    }

    public void switchToFrame(String name)
    {
        driver.switchTo().frame(name);
    }

    public void closeWindow()
    {
        driver.close();
    }

    public void refreshPage()
    {
        driver.navigate().refresh();
    }

    public String getCurrentURL()
    {
        return driver.getCurrentUrl();
    }

    // Alerts
    public Alert getAlert()
    {
        return driver.switchTo().alert();
    }

    public String getAlertText()
    {
        return getAlert().getText();
    }

    public void acceptAlert()
    {
        getAlert().accept();
    }

    public void dismissAlert()
    {
        getAlert().dismiss();
    }

    public void enterAlertText(String input)
    {
        getAlert().sendKeys(input);
    }

    // Find element(s)
    protected WebElement find(By locator)
    {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected List<WebElement> findAll(By locator)
    {
        return driver.findElements(locator);
    }

    // Click element
    protected void click(By locator)
    {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // Type text into input
    protected void type(By locator, String text)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    // Send keypresses into input
    protected void sendKeys(By locator, Keys key)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(key);
    }

    // Get element text
    protected String getText(By locator)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    // Get attribute text
    protected String getAttribute(By locator, String attribute)
    {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute(attribute);
    }

    // Check if element is visible
    protected boolean isDisplayed(By locator)
    {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator).isDisplayed();
    }

    // Wait checks
    protected void waitForClickable(By locator)
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            System.out.println("locator should have been clickable: " + e);
        }
    }

    protected void waitForNotClickable(By locator)
    {
        try {
            wait.until(ExpectedConditions.not(ExpectedConditions.elementToBeClickable(locator)));
        } catch (Exception e) {
            System.out.println("locator should not have been clickable: " + e);
        }
    }

    protected boolean waitForEnabled(By locator)
    {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).isEnabled();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForVisible(By locator)
    {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    protected boolean waitForInvisible(By locator)
    {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Check status code of target URL
    protected int getStatusCode(String URL)
    {
        return RestAssured.given().when().get(URL).statusCode();
    }

    //Misc
    public void delay(int duration) throws InterruptedException
    {
        Thread.sleep(duration);
    }
    public List<LogEntry> getLogs()
    {
        return driver.manage().logs().get(LogType.BROWSER).getAll();
    }
}
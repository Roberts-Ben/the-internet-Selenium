import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static org.junit.jupiter.api.Assertions.*;

public class HorizontalSlider_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/horizontal_slider");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/horizontal_slider", URL);
    }

    @Test
    public void verifySliderClickandDrag()
    {
        WebElement sliderValue = driver.findElement(By.id("range"));
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        assertEquals("0", sliderValue.getText());

        Actions action = new Actions(driver);
        action.dragAndDropBy(slider,1, 0).build().perform();

        assertEquals("2.5", sliderValue.getText());
    }

    @Test
    public void verifySliderArrowKeys()
    {
        WebElement sliderValue = driver.findElement(By.id("range"));
        WebElement slider = driver.findElement(By.xpath("//input[@type='range']"));

        assertEquals("0", sliderValue.getText());

        slider.sendKeys(Keys.ARROW_RIGHT);

        assertEquals("0.5", sliderValue.getText());

        slider.sendKeys(Keys.ARROW_LEFT);

        assertEquals("0", sliderValue.getText());
    }
}
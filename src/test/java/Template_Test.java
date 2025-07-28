import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Template_Test extends BaseTest
{
    @BeforeEach
    public void setup() throws Exception
    {
        driver.get("https://the-internet.herokuapp.com/");

        String URL = driver.getCurrentUrl();
        assertEquals("https://the-internet.herokuapp.com/", URL);
    }

    @Test
    public void TestCase()
    {

    }
}
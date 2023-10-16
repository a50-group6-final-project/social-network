package weare.testing;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestTests {
    @Test
    public void test(){
        WebDriver driver1 = new ChromeDriver();
        driver1.get("https://www.google.com");
    }
}

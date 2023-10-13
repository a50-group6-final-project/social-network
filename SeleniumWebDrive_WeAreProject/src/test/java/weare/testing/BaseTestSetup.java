package weare.testing;

import com.telerikacademy.testframework.CustomWebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

public class BaseTestSetup {
    public static PageLogin pageLogin;
    public static RegisterPage registerPage;
    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driver = CustomWebDriverManager.CustomWebDriverManagerEnum.INSTANCE.getDriver();
        registerPage = new RegisterPage(driver);
        }


    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
}

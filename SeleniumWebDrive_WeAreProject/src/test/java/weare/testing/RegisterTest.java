package weare.testing;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static weare.testing.RegisterPage.*;

public class RegisterTest extends BaseTestSetup {

    @Test
    public void userRegisterWhenInputValidData() {

       registerNewUser(8);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        registerPage.assertElementPresent("//a[@id='button']");
    }



    @Test
    public void userRegisterWhenInputUserNameWithMinLength() {
        registerNewUser(2);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        registerPage.assertElementPresent("//a[@id='button']");
    }
}

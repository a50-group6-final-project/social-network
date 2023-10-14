package weare.testing;

import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterTest extends BaseTestSetup {

    @Test
    public void userRegisterWhenInputValidData() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(6);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword();
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterSuccessfullyWhenInputUsernameWithMinLength() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(2);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword();
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterSuccessfullyWhenInputUsernameWithMaxLength() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(20);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword();
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);


        BasePage.assertElementPresent("//a[@id='button']");
    }
}


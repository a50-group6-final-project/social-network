package weare.testing;
import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterTest extends BaseTestSetup {

    @Test
    public void userRegisterWhenInputValidData() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(6);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(12);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterSuccessfullyWhenInputUsernameWithMinLength() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(2);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(12);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterSuccessfullyWhenInputUsernameWithMaxLength() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(20);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(10);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);


        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterUnsuccessfullyWhenInputUsernameWithOneSymbol() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(1);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(12);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);


        BasePage.assertErrorPresent("//h1[text()='Whitelabel Error Page']", "User is registered with an invalid number of password characters");
    }

    @Test
    public void userRegisterUnsuccessfullyUsernameLengthExceedingLimit() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(21);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(12);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        BasePage.assertErrorPresent("//h1[text()='Whitelabel Error Page']", "User is registered with an invalid number of username characters");

    }

    @Test
    public void userRegisterUnsuccessfullyWithThreeCharactersPassword() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(8);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(3);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        BasePage.assertErrorPresent("//i[@style='color: red'][text()='password must be minimum 6 characters']", "User is registered with an invalid number of password characters");

    }

    @Test
    public void userRegisterUnsuccessfullyWithSevenCharactersPassword() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(7);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword(7);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        BasePage.assertErrorPresent("//i[@style='color: red'][text()='password must be minimum 6 characters']", "User is registered with an invalid number of password characters");

    }

    @Test
    public void userRegisterUnsuccessfullyWithLetterPassword() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(7);
        BaseTestSetup.generatedLetterPassword = BaseTestSetup.generateLetterPassword(8);
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedLetterPassword , BaseTestSetup.generatedEmail);

        BasePage.assertErrorPresent("//h1[text()='Whitelabel Error Page']", "User can be registered with only letter password");

    }

}


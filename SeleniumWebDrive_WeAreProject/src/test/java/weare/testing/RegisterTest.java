package weare.testing;
import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterTest extends BaseTestSetup {

    @Test
    public void userRegisterWhenInputValidData() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterSuccessfullyWhenInputUsernameWithMinLength() {
        String username = BaseTestSetup.generateRandomUsername(2);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterSuccessfullyWhenInputUsernameWithMaxLength() {
        String username = BaseTestSetup.generateRandomUsername(20);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void userRegisterUnsuccessfullyWhenInputUsernameWithOneSymbol() {
        String username = BaseTestSetup.generateRandomUsername(1);
        String password = BaseTestSetup.generateRandomPassword(12);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);


        BasePage.assertErrorPresent("//h1[text()='Whitelabel Error Page']", "User is registered with an invalid number of password characters");
    }

    @Test
    public void userRegisterUnsuccessfullyUsernameLengthExceedingLimit() {
        String username = BaseTestSetup.generateRandomUsername(21);
        String password = BaseTestSetup.generateRandomPassword(12);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        BasePage.assertErrorPresent("//h1[text()='Whitelabel Error Page']", "User is registered with an invalid number of username characters");

    }

    @Test
    public void userRegisterUnsuccessfullyWithThreeCharactersPassword() {
        String username = BaseTestSetup.generateRandomUsername(8);
        String password = BaseTestSetup.generateRandomPassword(3);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        BasePage.assertErrorPresent("//i[@style='color: red'][text()='password must be minimum 6 characters']", "User is registered with an invalid number of password characters");

    }

    @Test
    public void userRegisterUnsuccessfullyWithSevenCharactersPassword() {
        String username = BaseTestSetup.generateRandomUsername(7);
        String password = BaseTestSetup.generateRandomPassword(7);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        BasePage.assertErrorPresent("//i[@style='color: red'][text()='password must be minimum 6 characters']", "User is registered with an invalid number of password characters");

    }

    @Test
    public void userRegisterUnsuccessfullyWithLetterPassword() {
        String username = BaseTestSetup.generateRandomUsername(7);
        String letterPassword = BaseTestSetup.generateLetterPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, letterPassword, email);

        BasePage.assertErrorPresent("//h1[text()='Whitelabel Error Page']", "User can be registered with a password consisting only of letters");

    }

}


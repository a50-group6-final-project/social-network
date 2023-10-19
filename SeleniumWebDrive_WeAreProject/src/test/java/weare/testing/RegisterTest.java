package weare.testing;

import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.Test;

public class RegisterTest extends BaseTestSetup {


    @Test
    public void UserRegistered_When_InputValidData() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        BasePage.assertElementPresent("weAre.basePage.registerSuccessButton");
    }

    @Test
    public void UserRegisteredSuccessfully_When_InputUsernameWithMinLength() {
        String username = BaseTestSetup.generateRandomUsername(2);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        BasePage.assertElementPresent("weAre.basePage.registerSuccessButton");
    }

    @Test
    public void UserRegisteredSuccessfully_When_InputUsernameWithMaxLength() {
        String username = BaseTestSetup.generateRandomUsername(20);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);


        BasePage.assertElementPresent("weAre.basePage.registerSuccessButton");
    }

    @Test
    public void UserNotRegistered_When_TryToInputUsernameWithOneSymbol() {
        String username = BaseTestSetup.generateRandomUsername(1);
        String password = BaseTestSetup.generateRandomPassword(12);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        BasePage.assertErrorPresent("weAre.basePage.whitelabelError", "User is registered with an invalid number of password characters");
    }

    @Test
    public void UserNotRegistered_When_TryToInputUsernameExceedingLimit() {
        String username = BaseTestSetup.generateRandomUsername(21);
        String password = BaseTestSetup.generateRandomPassword(12);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);


        BasePage.assertErrorPresent("weAre.basePage.whitelabelError", "User is registered with an invalid number of username characters");

    }

    @Test
    public void UserNotRegistered_When_TryToInputPasswordWithThreeCharacters() {
        String username = BaseTestSetup.generateRandomUsername(8);
        String password = BaseTestSetup.generateRandomPassword(3);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);
        BasePage.assertErrorPresent("weAre.basePage.passwordError", "User is registered with an invalid number of password characters");

    }

    @Test
    public void UserNotRegistered_When_TryToInputPasswordWithSevenCharacters() {
        String username = BaseTestSetup.generateRandomUsername(7);
        String password = BaseTestSetup.generateRandomPassword(7);
        String email = BaseTestSetup.generateRandomEmail();


        registerPage.userRegister(username, password, email);

        BasePage.assertErrorPresent("weAre.basePage.passwordError", "User is registered with an invalid number of password characters");

    }

    @Test
    public void UserNotRegistered_When_TryToInputPasswordWithLettersOnly() {
        String username = BaseTestSetup.generateRandomUsername(7);
        String letterPassword = BaseTestSetup.generateLetterPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, letterPassword, email);

        BasePage.assertErrorPresent("weAre.basePage.whitelabelError", "User can be registered with a password consisting only of letters");

    }

    @Test
    public void UserRegisteredSuccessfully_When_SelectProfessionalCategory() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegisterWithProfessionalSelection(username, password, email);

        BasePage.assertElementPresent("weAre.basePage.registerSuccessButton");

    }
}


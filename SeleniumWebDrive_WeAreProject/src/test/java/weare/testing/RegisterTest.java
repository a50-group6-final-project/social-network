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
    public void UserRegisteredSuccessfully_When_SelectProfessionalCategory() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegisterWithProfessionalSelection(username, password, email);

        BasePage.assertElementPresent("weAre.basePage.registerSuccessButton");

    }
}


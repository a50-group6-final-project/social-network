package weare.testing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTestSetup {

    @Test
    public void UserLoggedIn_When_EnterValidCredentials() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        LoginPage.assertElementPresent("weAre.loginPage.logoutLink");
        homePage.logoutUser();
    }

    @Test
    public void LoginFailed_When_TryToLoginWithWrongPassword() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();
        String wrongPassword = BaseTestSetup.generateLetterPassword(10);

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, wrongPassword);
        LoginPage.assertElementPresent("weAre.loginPage.errorMessage");
    }

}


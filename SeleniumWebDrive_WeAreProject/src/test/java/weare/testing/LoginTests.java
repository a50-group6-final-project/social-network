package weare.testing;

import org.junit.jupiter.api.Test;

public class LoginTests extends BaseTestSetup {


    @Test
    public void UserLoggedIn_When_EnterValidCredentials() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);

        LoginPage.assertElementPresent("//a[@href='/logout' and text()='LOGOUT']");
    }

    @Test
    public void LoginFailed_When_TryToLogin_With_WrongPassword() {
        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();
        String wrongPassword = BaseTestSetup.generateLetterPassword(10);

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, wrongPassword);
        LoginPage.assertErrorPresent("//i[text()=' Wrong username or password. ']", "User is registered with wrong password");
    }

}


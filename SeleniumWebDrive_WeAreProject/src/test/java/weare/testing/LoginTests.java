package weare.testing;

import org.junit.jupiter.api.Test;
import static weare.testing.RegisterPage.*;

public class LoginTests extends BaseTestSetup {


    @Test
    public void loginUserWhenEnterValidCredentials() {
        BaseTestSetup.generatedUsername = BaseTestSetup.generateRandomUsername(6);
        BaseTestSetup.generatedPassword = BaseTestSetup.generateRandomPassword();
        BaseTestSetup.generatedEmail = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword, BaseTestSetup.generatedEmail);

        LoginPage.loginUser(BaseTestSetup.generatedUsername, BaseTestSetup.generatedPassword);

       LoginPage.assertElementPresent("//a[@href='/logout' and text()='LOGOUT']");
    }
}


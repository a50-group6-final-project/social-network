package weare.testing;

import org.junit.jupiter.api.Test;
import static weare.testing.RegisterPage.*;

public class LoginTests extends BaseTestSetup {
    @Test
    public void loginUserWhenEnterValidCredentials() {
        generateRandomUsername();
        generateRandomPassword();
        generateRandomEmail();

        registerPage.userRegister();

        pageLogin.loginUser();

        pageLogin.assertElementPresent("//a[@href='/logout' and text()='LOGOUT']");
    }
}

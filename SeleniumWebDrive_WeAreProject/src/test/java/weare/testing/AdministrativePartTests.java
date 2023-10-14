package weare.testing;

import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.Test;


public class AdministrativePartTests extends BaseTestSetup{

    @Test
    public void  AdminRegistered_When_InputValidData() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.adminRegister(username, password, email);

        LoginPage.loginUser(username,password);


        BasePage.assertElementPresent("//a[normalize-space(text())='GO TO admin zone']");
    }
}

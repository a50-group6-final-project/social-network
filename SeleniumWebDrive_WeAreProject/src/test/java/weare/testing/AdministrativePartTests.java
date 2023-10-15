package weare.testing;

import com.telerikacademy.pages.BasePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AdministrativePartTests extends BaseTestSetup{

    private AdminPage adminPage;

    @BeforeEach
    public void setup() {
        adminPage = new AdminPage();
    }


    @Test
    public void  AdminRegistered_When_InputValidData() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.adminRegister(username, password, email);

        BasePage.assertElementPresent("//a[@id='button']");
    }

    @Test
    public void  AdminLogin_When_InputValidData() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        loginPage.loginUser(username,password);
        BasePage.assertElementPresent("//a[normalize-space(text())='GO TO admin zone']");
    }


    @Test
    public void  IndustrySelected_When_ClickDropdown_And_ChooseOption() throws InterruptedException {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);
        loginPage.loginUser(username,password);
        adminPage.adminEditUserProfile();
        Thread.sleep(2000);

        BasePage.assertElementPresent("//a[contains(@href, 'searchParam1=Accountant')]");
    }

}

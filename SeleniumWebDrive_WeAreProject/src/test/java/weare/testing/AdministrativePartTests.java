package weare.testing;

import com.telerikacademy.pages.BasePage;
import io.restassured.http.Cookies;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DataGenerator;
import weare.models.PostModel;
import weare.models.UserRegister;


public class AdministrativePartTests extends BaseTestSetup {


    @BeforeEach
    public void setup() {
        adminPage = new AdminPage();
    }


    @Test
    public void AdminRegistered_When_InputValidData() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        BasePage.assertElementPresent("weAre.basePage.buttonElement");
    }

    @Test
    public void AdminLogin_When_InputValidData() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        loginPage.loginUser(username, password);
        BasePage.assertElementPresent("weAre.basePage.goToAdminZoneButton");
        homePage.logoutUser();

    }


    @Test
    public void IndustrySelected_When_ClickDropdownAndChooseOption() throws InterruptedException {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);
        loginPage.loginUser(username, password);
        adminPage.adminEditUserIndustrySelection();
        Thread.sleep(2000);

        BasePage.assertElementPresent("weAre.basePage.accountantSearchLink");
        homePage.logoutUser();
    }

    @Test
    public void UserDisabled_When_ClickDisableButton() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();
        homePage.navigateToPage();
        registerPage.userRegister(username, password, email);
        loginPage.loginUser(username, password);
        adminPage.adminDisableUser();

        BasePage.assertElementPresent("weAre.basePage.enableInput");
        homePage.logoutUser();

    }

    @Test
    public void PostEdited_When_AdminTriesToEditPostAndConfirmEdit() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();
        registerPage.userRegister(username, password, email);
        loginPage.loginUser(username, password);

        adminPage.adminEditUserPost();
        homePage.logoutUser();

    }


    @Test
    public void PostDeleted_When_AdminTriesToEditPostAndConfirmEdit() {


        String username = BaseTestSetup.generateRandomUsername(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);

        LoginPage.loginUser(username, password);
        newPostPage.createPost("hello", "Public");

        String usernameAdmin = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String passwordAdmin = BaseTestSetup.generateRandomPassword(10);
        String emailAdmin = BaseTestSetup.generateRandomEmail();
        homePage.navigateToPage();
        registerPage.userRegister(username, password,emailAdmin);

        loginPage.loginUser(usernameAdmin, passwordAdmin);

        adminPage.adminDeleteUserPost();
        homePage.logoutUser();

    }
    @Test
    public void CommentEdited_When_AdminTriesToEditPostAndConfirmEdit() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();
        homePage.navigateToPage();

        registerPage.userRegister(username, password, email);
        loginPage.loginUser(username, password);
        homePage.logoutUser();

    }



        @Test
    public void CommentDeleted_When_AdminTriesToEditPostAndConfirmEdit() {
            String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
            String password = BaseTestSetup.generateRandomPassword(10);
            String email = BaseTestSetup.generateRandomEmail();
            registerPage.userRegister(username, password, email);
            loginPage.loginUser(username, password);
            homePage.logoutUser();

        }

    @Test
    public void PersonalProfileEdited_When_AdminTriesToEditProfile_AndConfirmChanges() {
        String username = BaseTestSetup.generateRandomUsernameWithAdmin(6);
        String password = BaseTestSetup.generateRandomPassword(10);
        String email = BaseTestSetup.generateRandomEmail();

        registerPage.userRegister(username, password, email);
        loginPage.loginUser(username, password);
        adminPage.adminEditPersonalProfile();
        BasePage.assertElementPresent("weAre.BasePage.firstName");
        BasePage.assertElementPresent("weAre.BasePage.lastName");
        homePage.logoutUser();
    }
}

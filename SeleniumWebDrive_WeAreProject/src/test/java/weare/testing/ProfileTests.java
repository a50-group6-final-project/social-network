package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ModelGenerator;
import weare.api.UserController;
import weare.models.UserPersonal;
import weare.models.UserRegister;

public class ProfileTests extends BaseTestSetup {

    static UserRegister userToRegister;
    static int registeredUserId;
    static Cookies cookies;

    @BeforeAll
    public static void setup() {

        userToRegister = ModelGenerator.generateUserRegisterModel();
        Response response = UserController.registerUser(userToRegister);
        cookies = UserController.authenticatedAndFetchCookies(userToRegister.username, userToRegister.password);

        registeredUserId = Integer.parseInt(response.asString().split(" ")[6]);
        loginPage.navigateToPage();
        LoginPage.loginUser(userToRegister.username, userToRegister.password);
        LoginPage.assertElementPresent("weAre.loginPage.logoutLink");
        userPage = new UserPage(driver, String.format("http://localhost:8081/auth/users/%d/profile", registeredUserId));
        userPage.navigateToPage();
    }

    @Test
    public void PersonalProfileUpdated_When_UserUpdatesPersonalProfileWithValidInput() {
        System.out.println("Test");

        UserPersonal userPersonal = ModelGenerator.generateUserPersonalModel();

        userPage.editPersonalProfile(userPersonal);
        userPage.navigateToPage();
        userPage.assertNamesArePresent(userPersonal.firstName, userPersonal.lastName);
        userPage.assertEmailIsPresent(userPersonal.email);
        userPage.assertBirthDateIsPresent(userPersonal.birthYear);
    }

    public void ExpertiseProfileUpdated_When_UserUpdatesExpertiseProfileWithValidInput() {
    }

    ;
}

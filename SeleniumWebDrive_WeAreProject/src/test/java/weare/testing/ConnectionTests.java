package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.ModelGenerator;
import weare.api.UserController;
import weare.models.UserRegister;

public class ConnectionTests extends BaseTestSetup {

    protected static UserRegister firstUser;
    protected static int firstUserId;
    protected static Cookies firstUserCookies;
    protected static UserRegister secondUser;
    protected static int secondUserId;
    protected static Cookies secondUserCookies;
    @BeforeAll
    public static void setup() {
        firstUser = ModelGenerator.generateUserRegisterModel();
        secondUser = ModelGenerator.generateUserRegisterModel();

        Response responseFirstUser = UserController.registerUser(firstUser);
        firstUserCookies = UserController.authenticatedAndFetchCookies(firstUser.username, firstUser.password);
        firstUserId = Integer.parseInt(responseFirstUser.asString().split(" ")[6]);

        Response responseSecondUser = UserController.registerUser(secondUser);
        secondUserCookies = UserController.authenticatedAndFetchCookies(secondUser.username, secondUser.password);
        secondUserId = Integer.parseInt(responseSecondUser.asString().split(" ")[6]);


    }
    @Test
    public void sendConnectionRequest(){
        loginPage.navigateToPage();
        LoginPage.loginUser(firstUser.username, firstUser.password);
        LoginPage.assertElementPresent("weAre.loginPage.logoutLink");

    };

    @Test
    public void approveConnectionRequest(){};

    @Test
    public void removeConnection(){};
}

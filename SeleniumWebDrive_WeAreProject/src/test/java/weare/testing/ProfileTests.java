package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ModelGenerator;
import weare.api.PostController;
import weare.api.UserController;
import weare.models.PostModel;
import weare.models.UserRegister;

public class ProfileTests extends BaseTestSetup {

    static UserRegister userToRegister;
    static int registeredUserId;
    static Cookies cookies;

    String postContent;
    String postContentUpdate;
    PostModel[] postsList;

    @BeforeAll
    public static void setup() {

        userToRegister = ModelGenerator.generateUserRegisterModel();
        Response response = UserController.registerUser(userToRegister);
        cookies = UserController.authenticatedAndFetchCookies(userToRegister.username, userToRegister.password);

        registeredUserId = Integer.parseInt(response.asString().split(" ")[6]);
        loginPage.navigateToPage();
        LoginPage.loginUser(userToRegister.username, userToRegister.password);
        LoginPage.assertElementPresent("weAre.loginPage.logoutLink");
        userPage = new UserPage(driver,String.format("http://localhost:8081/auth/users/%d/profile", registeredUserId));
        userPage.navigateToPage();
    }
    @Test
    public void getProfilePosts(){
        PostModel postOneModel = ModelGenerator.generatePostModel(true);
        PostModel postOne = PostController.createPost(cookies, postOneModel).as(PostModel.class);
        PostModel postTwoModel = ModelGenerator.generatePostModel(false);
        PostModel postTwo = PostController.createPost(cookies, postTwoModel).as(PostModel.class);

        PostModel[] posts = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);

        latestPostsPage.navigateToPage();
        latestPostsPage.assertPostsCount(posts.length);
    }

    @Test
    public void updatePersonalProfile(){}

    @Test
    public void updateExpertiseProfile(){}
}

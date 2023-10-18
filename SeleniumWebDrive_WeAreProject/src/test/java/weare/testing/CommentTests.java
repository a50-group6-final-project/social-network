package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.ModelGenerator;
import weare.api.CommentController;
import weare.api.PostController;
import weare.api.UserController;
import weare.models.CommentModel;
import weare.models.PostModel;
import weare.models.UserRegister;

public class CommentTests extends BaseTestSetup {
    static UserRegister userToRegister;
    static int registeredUserId;
    static Cookies cookies;
    static PostModel createdPost;
    static CommentModel createdComment;

    @BeforeAll
    public static void setup() {
        userToRegister = ModelGenerator.generateUserRegisterModel();
        Response response = UserController.registerUser(userToRegister);
        cookies = UserController.authenticatedAndFetchCookies(userToRegister.username, userToRegister.password);
        registeredUserId = Integer.parseInt(response.asString().split(" ")[6]);

        PostModel postModel = ModelGenerator.generatePostModel(true);
        createdPost = PostController.createPost(cookies, postModel).as(PostModel.class);
        loginPage.navigateToPage();
        LoginPage.loginUser(userToRegister.username, userToRegister.password);
        LoginPage.assertElementPresent("weAre.loginPage.logoutLink");
    }

    @Test
    public void createComment() {
        System.out.println("Test");
        postPage.navigateToPage();
    }

    ;

    @Test
    public void editComment() {
    }

    ;

    @Test
    void likeComment() {
    }

    ;

    @Test
    void dislikeComment() {
    }

    ;

    @Test
    public void deleteComment() {
    }

    ;
}

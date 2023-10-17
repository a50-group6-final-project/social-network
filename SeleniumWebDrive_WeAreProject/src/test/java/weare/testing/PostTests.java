package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import utils.DataGenerator;
import utils.ModelGenerator;
import weare.api.UserController;
import weare.models.PostModel;
import weare.models.UserRegister;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

public class PostTests extends BaseTestSetup {


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

    }

    @BeforeEach
    public void beforeEach() {
        postContent = DataGenerator.generateUniqueContentPost();
    }

    @Test
    @Order(1)
    public void createPublicPost_withValidInput() {
        newPostPage.navigateToPage();
        newPostPage.createPost(postContent, "Public");

        allPostPage.assertNavigatedUrl();
        allPostPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContent));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContent, postsList[0].content);
        System.out.println(postsList[0].postId);
    }
    @Test
    @Order(2)
    public void updatePublicPost_withValidInput(){
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);

        postPage = new PostPage(driver, "http://localhost:8081/posts/" + postsList[0].postId );
        postPage.navigateToPage();
        postContentUpdate = DataGenerator.generateUniqueContentPost();
        postPage.update(postContentUpdate, "Public", postsList[0].postId);

        allPostPage.assertNavigatedUrl();
        allPostPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContentUpdate));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContentUpdate, postsList[0].content);
    }

    @Test
    @Order(3)
    public void createPrivatePost_withValidInput() {
        newPostPage.navigateToPage();
        newPostPage.createPost(postContent, "Private");

        allPostPage.assertNavigatedUrl();
        allPostPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContent));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContent, postsList[0].content);
    }

    @Test
    @Order(4)
    public void updatePrivatePost_withValidInput(){
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);

        postPage = new PostPage(driver, "http://localhost:8081/posts/" + postsList[0].postId );
        postPage.navigateToPage();
        postContentUpdate = DataGenerator.generateUniqueContentPost();
        postPage.update(postContentUpdate, "Private", postsList[0].postId);

        allPostPage.assertNavigatedUrl();
        allPostPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContentUpdate));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContentUpdate, postsList[0].content);
    }


}

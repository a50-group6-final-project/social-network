package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DataGenerator;
import utils.ModelGenerator;
import weare.api.UserController;
import weare.models.PostModel;
import weare.models.UserRegister;

import static com.telerikacademy.testframework.Utils.getUIMappingByKey;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PostTests extends BaseTestSetup {
    static UserRegister userToRegister;
    static int registeredUserId;
    static Cookies cookies;
    static String postContent;
    static String postContentUpdate;
    static PostModel[] postsList;

    @BeforeAll
    public static void setup() {
        userToRegister = ModelGenerator.generateUserRegisterModel();
        Response response = UserController.registerUser(userToRegister);
        cookies = UserController.authenticatedAndFetchCookies(userToRegister.username, userToRegister.password);

        registeredUserId = Integer.parseInt(response.asString().split(" ")[6]);
        loginPage.navigateToPage();
        LoginPage.loginUser(userToRegister.username, userToRegister.password);
        LoginPage.assertElementPresent("weAre.loginPage.logoutLink");

        postContentUpdate = DataGenerator.generateUniqueContentPost();
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
        Assertions.assertEquals(true, postsList[0].mypublic, "Post is not public");
        System.out.println(postsList[0].postId);
    }

    @Test
    @Order(2)
    public void updatePublicPost_withValidInput() {
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        //TODO: fix url
        postPage = new PostPage(driver, "http://localhost:8081/posts/" + postsList[0].postId);
        postPage.navigateToPage();
        postPage.update(postContentUpdate, "Public", postsList[0].postId);

        postPage.assertNavigatedUrl();
        postPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContentUpdate));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContentUpdate, postsList[0].content);
        Assertions.assertEquals(true, postsList[0].mypublic, "Post is not public");
    }

    @Test
    @Order(3)
    public void likePublicPost() {
        latestPostsPage.navigateToPage();
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);

        latestPostsPage.assertPostIsPresent(postsList[0].content);
        latestPostsPage.likePost(postsList[0].postId);
        latestPostsPage.assertPostIsLiked(postsList[0].postId);
    }

    @Test
    @Order(4)
    public void deletePublicPost() {
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(true, postsList[0].mypublic, "Post is not public");

        postPage = new PostPage(driver, "http://localhost:8081/posts/" + postsList[0].postId);
        postPage.navigateToPage();
        postPage.delete(postsList[0].postId);

        postPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.deleteMessage"), "Post deleted successfully"));
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(0, postsList.length);
    }

    @Test
    @Order(5)
    public void createPrivatePost_withValidInput() {
        newPostPage.navigateToPage();
        newPostPage.createPost(postContent, "Private");

        allPostPage.assertNavigatedUrl();
        allPostPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContent));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContent, postsList[0].content);
        Assertions.assertEquals(false, postsList[0].mypublic, "Post is not private");
    }

    @Test
    @Order(6)
    public void updatePrivatePost_withValidInput() {
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);

        postPage = new PostPage(driver, "http://localhost:8081/posts/" + postsList[0].postId);
        postPage.navigateToPage();
        postContentUpdate = DataGenerator.generateUniqueContentPost();
        postPage.update(postContentUpdate, "Private", postsList[0].postId);

        postPage.assertNavigatedUrl();
        postPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.postContent"), postContentUpdate));

        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(postContentUpdate, postsList[0].content);
        Assertions.assertEquals(false, postsList[0].mypublic, "Post is not private");

    }

    @Test
    @Order(7)
    public void dislikePrivatePost() {
        latestPostsPage.navigateToPage();
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        latestPostsPage.assertPostIsPresent(postsList[0].content);
        latestPostsPage.likePost(postsList[0].postId);
        latestPostsPage.assertPostDisliked(postsList[0].postId);
    }

    @Test
    @Order(8)
    public void deletePrivatePost() {
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(false, postsList[0].mypublic, "Post is not private");

        postPage = new PostPage(driver, "http://localhost:8081/posts/" + postsList[0].postId);
        postPage.navigateToPage();
        postPage.delete(postsList[0].postId);

        postPage.assertElementPresent(String.format(getUIMappingByKey("weAre.allPostPage.deleteMessage"), "Post deleted successfully"));
        postsList = UserController.getProfilePosts(cookies, registeredUserId).as(PostModel[].class);
        Assertions.assertEquals(0, postsList.length);
    }


}

package weare.testing;

import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utils.DataGenerator;
import utils.ModelGenerator;
import weare.api.CommentController;
import weare.api.PostController;
import weare.api.UserController;
import weare.models.CommentModel;
import weare.models.PostModel;
import weare.models.UserRegister;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CommentTests extends BaseTestSetup {
    static UserRegister userToRegister;
    static int registeredUserId;
    static Cookies cookies;
    static PostModel createdPost;
    static CommentModel createdComment;
    static CommentModel comment;

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
        postPage = new PostPage(driver, "http://localhost:8081/posts/" + createdPost.postId);
    }

    @BeforeEach
    public void beforeEach() {
        postPage.navigateToPage();

    }

    @Test
    @Order(1)
    public void createComment() {


        createdComment = ModelGenerator.generateCommentModel(createdPost.postId, registeredUserId);
        postPage.createComment(createdComment);
        postPage.navigateToPage();

        postPage.assertCommentPresent(createdComment);

        comment = CommentController.findAllCommentsOfAPost(cookies, createdPost.postId).as(CommentModel[].class)[0];
        Assertions.assertEquals(createdComment.content, comment.content);
    }

    @Test
    @Order(2)
    void likeComment() {
        postPage.assertCommentPresent(createdComment);
        postPage.likeComment(comment.commentId);
        postPage.assertCommentIsLiked(comment.commentId);

        comment = CommentController.findAllCommentsOfAPost(cookies, createdPost.postId).as(CommentModel[].class)[0];

        Assertions.assertEquals(1, comment.likes.size());
    }

    @Test
    @Order(3)
    public void editComment() {
        String updateCommentString = DataGenerator.generateUniqueContentPost();
        postPage.assertCommentPresent(createdComment);
        postPage.editComment(comment.commentId, updateCommentString);
        postPage.navigateToPage();

        createdComment.content = updateCommentString;
        postPage.assertCommentPresent(createdComment);

        comment = CommentController.findAllCommentsOfAPost(cookies, createdPost.postId).as(CommentModel[].class)[0];
        Assertions.assertEquals(updateCommentString, comment.content);
    }

    ;;

    @Test
    void dislikeComment() {
        postPage.assertCommentPresent(createdComment);
        postPage.dislikeComment(comment.commentId);
        postPage.assertCommentIsDisliked(comment.commentId);

        comment = CommentController.findAllCommentsOfAPost(cookies, createdPost.postId).as(CommentModel[].class)[0];
        Assertions.assertEquals(0, comment.likes.size());
    }

    ;

    @Test
    public void deleteComment() {
        postPage.assertCommentPresent(createdComment);
        postPage.deleteComment(comment.commentId);
        postPage.assertCommentIsDeleted();

        CommentModel[] commentsList = CommentController.findAllCommentsOfAPost(cookies, createdPost.postId).as(CommentModel[].class);
        Assertions.assertEquals(0, commentsList.length);
    }

    ;
}

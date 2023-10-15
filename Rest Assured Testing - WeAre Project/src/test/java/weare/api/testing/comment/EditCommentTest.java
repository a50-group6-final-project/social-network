package weare.api.testing.comment;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.CommentController;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.CommentModel;
import models.PostModel;
import models.UserRegister;
import org.testng.annotations.*;

import static org.testng.Assert.assertEquals;

public class EditCommentTest extends BaseTestSetup {
    String uniqueContent;
    Cookies cookies;

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
        }
        cookies = authenticateAndFetchCookies();

        if (isDeletedPost) {
            uniqueContent = DataGenerator.generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            Response response = PostController.createPost(cookies, createPost);

            createdPost = response.as(PostModel.class);
            assertEquals(createdPost.content, uniqueContent, "Content does not match.");

            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }

        if (isCommentDeleted) {
            createComment = ModelGenerator.generateCommentModel(DataGenerator.generateUniqueContentPost(), postId, currentUserId);
            Response response = CommentController.createComment(cookies, createComment);
            System.out.println(response);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println("Successfully created a new comment with Id" + " " + createdComment.commentId);
            isCommentDeleted = false;
        }
    }


    @Test
    public void editComment_Successful() {
        String updatedUniqueContent = DataGenerator.generateUniqueContentPost();
        Response response = CommentController.editComment(cookies, createdComment.commentId, updatedUniqueContent);
        isResponse200(response);
        System.out.println("Successfully edited comment with Id" + " " + commentId + " " + "successfully.");
        //TODO get comment by id and assert that the content is updated

        Response comment = CommentController.findOneCommentOfAPost(cookies, createdComment.commentId);
        System.out.println(comment.asString());
//        CommentModel updatedComment = response.as(CommentModel.class);
//        assertEquals(updatedComment.content, updatedUniqueContent, "Content does not match.");

    }

    @AfterClass
    public void tearDown() {
        if (!isDeletedPost) {
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Successfully delete a post with Id" + " " + createdPost.postId);
            isDeletedPost = true;
        }
        if (!isCommentDeleted) {
            CommentController.deleteComment(cookies, createdComment.commentId);
            System.out.println("Successfully delete a comment with Id" + " " + createdPost.postId);
            isCommentDeleted = true;
        }
    }
}

package weare.api.testing.comment;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.CommentController;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.CommentModel;
import models.PostModel;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class CreateCommentTest extends BaseTestSetup {
    String uniqueContent;

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
            userId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + userId);
        }
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
    }

    @Test
    public void createComment_Successful() {
        String uniqueContent = DataGenerator.generateUniqueContentPost();
        createComment = ModelGenerator.generateCommentModel(uniqueContent, postId, userId);

        Response response = CommentController.createComment(cookies, createComment);
        isResponse200(response);

        createdComment = response.as(CommentModel.class);
        assertEquals(createdComment.content, uniqueContent, "Content does not match.");
        Assert.assertNotNull(createdComment.commentId, "commentId is null");
        Assert.assertNotNull(createdComment.content, "content is null");
        Assert.assertNotNull(createdComment.likes, "likes is null");
        Assert.assertNotNull(createdComment.date, "date is null");
        Assert.assertNotNull(createdComment.liked, "liked is null");

        commentId = createdComment.commentId;
        System.out.println("Successfully created a new comment with Id" + " " + commentId + ". All properties are not null.");
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

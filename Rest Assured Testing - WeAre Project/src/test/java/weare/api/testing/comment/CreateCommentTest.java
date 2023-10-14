package weare.api.testing.comment;

import Utils.ModelGenerator;
import Utils.Serializer;
import api.CommentController;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CommentModel;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class CreateCommentTest extends BaseTestSetup {
    String uniqueContent;

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            postCreatorUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(postCreatorUsername, currentEmail);
            authenticateAndFetchCookies(postCreatorUsername, "Project.10");
            isRegistered = true;
            userId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + userId);
        }
        if (isDeletedPost) {
            uniqueContent = generateUniqueContentPost();
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
        String uniqueContent = generateUniqueContentPost();
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
        if (!isDeletedPost){
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Successfully delete a post with Id" + " " + createdPost.postId);

        }
        if(!isCommentDeleted){
            CommentController.deleteComment(cookies, createdComment.commentId);
            System.out.println("Successfully delete a comment with Id" + " " + createdPost.postId);

        }
    }
}

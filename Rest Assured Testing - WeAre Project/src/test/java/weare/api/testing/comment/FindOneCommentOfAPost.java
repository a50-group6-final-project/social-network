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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class FindOneCommentOfAPost extends BaseTestSetup {
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

        if (isCommentDeleted) {
            createComment = ModelGenerator.generateCommentModel(generateUniqueContentPost(), postId, userId);

            Response response = CommentController.createComment(cookies, createComment);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println("Successfully created a new comment with Id" + " " + createdComment.commentId);
            isCommentDeleted = false;
        }
    }

    @Test
    public void findOneCommentOfAPost_Successful() {
        Response response = CommentController.findOneCommentOfAPost(cookies, createdComment.commentId);

        System.out.println(response.asString());
        isResponse200(response);

        CommentModel comment = response.as(CommentModel.class);
        Assert.assertEquals(comment.content, createdComment.content, "Content does not match.");
        System.out.println("Successfully fetched one comment with Id" + " " + createdComment.commentId + " " + "of post with Id" + " " + postId + " " + "successfully.");
    }
}

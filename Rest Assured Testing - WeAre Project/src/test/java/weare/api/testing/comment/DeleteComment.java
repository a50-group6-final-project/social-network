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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class DeleteComment extends BaseTestSetup {
    String uniqueContent;
    @BeforeClass
    public void setup() {

        if(!isRegistered) {
            postCreatorUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(postCreatorUsername, currentEmail);
            authenticateAndFetchCookies(postCreatorUsername, "Project.10");
            isRegistered = true;
        }

        if(isDeletedPost){
            uniqueContent = generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            Response response = PostController.createPost(cookies, createPost);

            createdPost = response.as(PostModel.class);
            assertEquals(createdPost.content, uniqueContent, "Content does not match.");
            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }
        if(isCommentDeleted){
            createComment = ModelGenerator.generateCommentModel(generateUniqueContentPost(), postId, userId);

            Response response = CommentController.createComment(cookies, createComment);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println("Successfully created a new comment with Id" + " " + createdComment.commentId);

        }
    }

    @Test
    public void deleteComment_Successful() {
        Response response = CommentController.deleteComment(cookies, createdComment.commentId);
        isResponse200(response);
        System.out.println("Successfully deleted comment with Id" + " " + commentId + " " + "successfully.");
    }
}

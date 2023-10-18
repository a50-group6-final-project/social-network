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

import static Utils.Constants.COMMENT_SUCCESS_MESSAGE;
import static Utils.Constants.CONTENT_MISMATCH_MESSAGE;
import static org.testng.Assert.assertEquals;

public class FindOneCommentOfAPost extends BaseTestSetup {
    String uniqueContent;

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
            System.out.println("Successfully created a new user with Id" + " " + currentUserId);
        }
        if (isDeletedPost) {
            uniqueContent = DataGenerator.generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            authenticateAndFetchCookies();
            Response response = PostController.createPost(cookies, createPost);

            createdPost = response.as(PostModel.class);
            assertEquals(createdPost.content, uniqueContent, CONTENT_MISMATCH_MESSAGE);

            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }

        if (isCommentDeleted) {
            createComment = ModelGenerator.generateCommentModel(DataGenerator.generateUniqueContentPost(), postId, currentUserId);

            Response response = CommentController.createComment(cookies, createComment);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println(COMMENT_SUCCESS_MESSAGE  + " " + createdComment.commentId);
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

    @AfterClass
    public void tearDown(){
        if (!isDeletedPost){
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Successfully delete a post with Id" + " " + createdPost.postId);
            isDeletedPost = true;
        }
        if(!isCommentDeleted){
            CommentController.deleteComment(cookies, createdComment.commentId);
            System.out.println("Successfully delete a comment with Id" + " " + createdPost.postId);
            isCommentDeleted = true;
        }
    }
}

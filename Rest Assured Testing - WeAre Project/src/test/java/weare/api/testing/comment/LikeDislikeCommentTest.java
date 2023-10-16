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

import static org.testng.Assert.assertEquals;

public class LikeDislikeCommentTest extends BaseTestSetup {
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
            assertEquals(createdPost.content, uniqueContent, "Content does not match.");

            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }

        if (isCommentDeleted) {
            createComment = ModelGenerator.generateCommentModel(DataGenerator.generateUniqueContentPost(), postId, currentUserId);

            Response response = CommentController.createComment(cookies, createComment);
            isResponse200(response);

            createdComment = response.as(CommentModel.class);
            System.out.println("Successfully created a new comment with Id" + " " + createdComment.commentId);
            isCommentDeleted = false;
        }
    }

    @Test
    public void likeDislikeComment_Successful() {

        Response response = CommentController.LikeDislikeComment(cookies, createdComment.commentId);

        isResponse200(response);

        CommentModel likedComment = response.as(CommentModel.class);
        Assert.assertEquals(likedComment.liked, true, "The post is not liked.");
        System.out.println("Comment with Id" + " " + createdComment.commentId + " " + "Liked successfully.");

        response = CommentController.LikeDislikeComment(cookies, createdComment.commentId);
        CommentModel dislikedComment = response.as(CommentModel.class);
        Assert.assertEquals(dislikedComment.liked, false, "The post is disliked.");
        System.out.println("Comment with Id" + " " + createdComment.commentId + " " + "Disliked successfully.");

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




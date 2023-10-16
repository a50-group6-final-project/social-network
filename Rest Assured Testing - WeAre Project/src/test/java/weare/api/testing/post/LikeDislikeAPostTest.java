package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.PostModel;
import models.PostModelLikeDislike;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LikeDislikeAPostTest extends BaseTestSetup {
    PostModelLikeDislike editPost;

    @BeforeClass
    public void createPost_Successful() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
        }

        if (isDeletedPost) {
            String uniqueContent = DataGenerator.generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            authenticateAndFetchCookies();
            Response response = PostController.createPost(cookies, createPost);
            createdPost = response.as(PostModel.class);
            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }
    }

    @Test
    public void likeAndDislikePost_Successful() {

        Response response = PostController.likeAndDislikePost(cookies, postId);
        isResponse200(response);

        PostModelLikeDislike editPostLikeDislike = response.as(PostModelLikeDislike.class);
        Assert.assertEquals(editPostLikeDislike.liked, true, "The post is not liked.");
        System.out.println("Post with Id" + " " + postId + " " + "Liked successfully.");

        Response dislikeResponse = PostController.likeAndDislikePost(cookies, postId);
        isResponse200(dislikeResponse);
//        editPost = dislikeResponse.as(PostModel.class);
//        Assert.assertEquals(editPost.liked, false, "The post is disliked liked.");
        System.out.println("Post with Id" + " " + postId + " " + "Disliked successfully.");

    }

    @AfterTest
    public void tearDown() {
        if (!isDeletedPost) {
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Post with Id" + " " + postId + " " + "Deleted successfully.");
            isDeletedPost = true;
        }
    }
}

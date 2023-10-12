package weare.api.testing.post;

import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class GetNewsFeed extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            postCreatorUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(postCreatorUsername, currentEmail);
            authenticateAndFetchCookies(postCreatorUsername, "Project.10");
            isRegistered = true;
        }

        String uniqueContent = generateUniqueContentPost();
        createPost = ModelGenerator.generatePostModel(uniqueContent);
        Response response = PostController.createPost(cookies, createPost);
        createdPost = response.as(PostModel.class);
    }
    @Test
    public void getAllPosts_Successful() {
        Response response = PostController.getNewsFeed(cookies);
        isResponse200(response);

        PostModel[] posts = response.as(PostModel[].class);
        Assert.assertNotNull(posts, "Posts are null");
        Assert.assertTrue(posts.length > 0, "The array size is more than or equal to 1");

        Assert.assertNotNull(posts[0].content, "Content is null");
        System.out.println("Successfully fetched all posts.");
    }
}



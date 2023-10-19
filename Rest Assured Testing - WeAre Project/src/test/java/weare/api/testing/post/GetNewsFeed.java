package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.PostModel;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.*;


public class GetNewsFeed extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
        }

        String uniqueContent = DataGenerator.generateUniqueContentPost();
        createPost = ModelGenerator.generatePostModel(uniqueContent);
        authenticateAndFetchCookies();
        Response response = PostController.createPost(cookies, createPost);
        createdPost = response.as(PostModel.class);
    }
    @Test
    public void getAllPosts_Successful() {
        Response response = PostController.getNewsFeed(cookies);
        isResponse200(response);

        PostModel[] posts = response.as(PostModel[].class);
        Assert.assertNotNull(posts, POSTS_ARE_NULL_MESSAGE);
        Assert.assertTrue(posts.length > 0, ARRAY_SIZE_MESSAGE);

        Assert.assertNotNull(posts[0].content, CONTENT_IS_NULL_MESSAGE);
        System.out.println(SUCCESSFULLY_FETCHED_ALL_POSTS_MESSAGE);
    }
}



package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.PostModel;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.*;
import static org.testng.Assert.assertEquals;

public class CreatePostTest extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
        }
    }

    @Test
    public void PostCreated_WhenInputValidSymbolsAndClickCreatePostButton() {
        authenticateAndFetchCookies();
        String uniqueContent = DataGenerator.generateUniqueContentPost();
        createPost = ModelGenerator.generatePostModel(uniqueContent);

        Response response = PostController.createPost(cookies, createPost);

        isResponse200(response);

        createdPost = response.as(PostModel.class);

        assertEquals(createdPost.content, uniqueContent, CONTENT_MISMATCH_MESSAGE);
        Assert.assertNotNull(createdPost.postId, POST_ID_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.picture, PICTURE_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.comments, COMMENTS_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.rank, RANK_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.category.id, CATEGORY_ID_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.category.name, CATEGORY_NAME_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.mypublic, PUBLIC_NULL_MESSAGE);


        System.out.println(String.format(POST_SUCCESS_ALL_PROPERTIES_NOT_NULL, createdPost.postId));
    }

    @Test
    public void createPost_WithValidInput220CharactersLongMessage_Successful() {
        authenticateAndFetchCookies();
        String uniqueContent = DataGenerator.generateUniqueContentPost(220);
        createPost = ModelGenerator.generatePostModel(uniqueContent);

        Response response = PostController.createPost(cookies, createPost);

        isResponse200(response);

        createdPost = response.as(PostModel.class);

        assertEquals(createdPost.content, uniqueContent, CONTENT_MISMATCH_MESSAGE);
        Assert.assertNotNull(createdPost.postId, POST_ID_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.picture, PICTURE_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.comments, COMMENTS_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.rank, RANK_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.category.id, CATEGORY_ID_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.category.name, CATEGORY_NAME_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.mypublic, PUBLIC_NULL_MESSAGE);


        System.out.println(String.format(POST_SUCCESS_ALL_PROPERTIES_NOT_NULL, createdPost.postId));
    }

    @Test
    public void createPost_WithValidInput1CharactersLongMessage_Successful() {
        authenticateAndFetchCookies();
        String uniqueContent = DataGenerator.generateUniqueContentPost(1);
        createPost = ModelGenerator.generatePostModel(uniqueContent);

        Response response = PostController.createPost(cookies, createPost);

        isResponse200(response);

        createdPost = response.as(PostModel.class);

        assertEquals(createdPost.content, uniqueContent, CONTENT_MISMATCH_MESSAGE);
        Assert.assertNotNull(createdPost.postId, POST_ID_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.picture, PICTURE_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.comments, COMMENTS_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.rank, RANK_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.category.id, CATEGORY_ID_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.category.name, CATEGORY_NAME_NULL_MESSAGE);
        Assert.assertNotNull(createdPost.mypublic, PUBLIC_NULL_MESSAGE);


        System.out.println(String.format(POST_SUCCESS_ALL_PROPERTIES_NOT_NULL, createdPost.postId));
    }


    @AfterClass
    public void tearDown() {
        editPost = createdPost;
        postId = createdPost.postId;

        if (!isDeletedPost) {
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println(String.format(POST_DELETED_MESSAGE, postId));
            isDeletedPost = true;
        }
    }
}


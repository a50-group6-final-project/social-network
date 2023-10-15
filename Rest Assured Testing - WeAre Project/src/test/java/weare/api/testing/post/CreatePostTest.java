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
    public void createPost_Successful() {
        authenticateAndFetchCookies();
        String uniqueContent = DataGenerator.generateUniqueContentPost();
        createPost = ModelGenerator.generatePostModel(uniqueContent);

        Response response = PostController.createPost(cookies, createPost);

        isResponse200(response);

        createdPost = response.as(PostModel.class);

        assertEquals(createdPost.content, uniqueContent, "Content does not match.");
        Assert.assertNotNull(createdPost.postId, "postId is null");
        Assert.assertNotNull(createdPost.content, "content is null");
        Assert.assertNotNull(createdPost.picture, "picture is null");
        Assert.assertNotNull(createdPost.date, "date is null");
        Assert.assertNotNull(createdPost.liked, "likes is null");
        Assert.assertNotNull(createdPost.comments, "comments is null");
        Assert.assertNotNull(createdPost.rank, "rank is null");
        Assert.assertNotNull(createdPost.category.id, "category.id is null");
        Assert.assertNotNull(createdPost.category.name, "category.name is null");
        Assert.assertNotNull(createdPost.liked, "liked is null");
        Assert.assertNotNull(createdPost.mypublic, "public is null");

        System.out.println("Successfully created a new post with Id" + " " + createdPost.postId + ". All properties are not null.");
    }

    @AfterClass
    public void tearDown() {
        editPost = createdPost;
        postId = createdPost.postId;

        if (!isDeletedPost) {
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Post with Id" + " " + postId + " " + "Deleted successfully.");
            isDeletedPost = true;
        }
    }
}


package weare.api.testing.post;

import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.EditPost;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class EditPostTest extends BaseTestSetup {
    @BeforeTest
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
        System.out.println("Successfully created a new post with Id" + " " + createdPost.postId);
    }

    @Test
    public void editPost_Successful() {
        createdPost.content = "I am looking for a painter";
        Response response = PostController.editPost(cookies, createdPost);
        isResponse200(response);

//        editPost = response.as(PostModel.class);

        //Add some assertions
//        Assert.assertEquals(editPost.content, createdPost.content, "Content does not match.");

        System.out.println("Post with Id" + " " + createdPost.postId + " " + "edited successfully.");
    }

    @AfterTest
    public void tearDown() {
        Response response = PostController.deletePost(cookies, createdPost.postId);
        isResponse200(response);
        System.out.println(response.asString());
        System.out.println("Post with Id" + " " + postId + " " + "Deleted successfully.");
    }
}

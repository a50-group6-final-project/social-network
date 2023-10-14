package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.EditPost;
import models.PostModel;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class EditPostTest extends BaseTestSetup {
    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);

        }

        if (isDeletedPost) {
            String uniqueContent = DataGenerator.generateUniqueContentPost();
            createPost = ModelGenerator.generatePostModel(uniqueContent);
            Response response = PostController.createPost(cookies, createPost);
            createdPost = response.as(PostModel.class);
            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }
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
        if(!isDeletedPost){
            PostController.deletePost(cookies, createdPost.postId);
            System.out.println("Post with Id" + " " + postId + " " + "Deleted successfully.");
            isDeletedPost = true;
        }
    }
}

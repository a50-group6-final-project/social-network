package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.Post;
import models.UserRegister;
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
            authenticateAndFetchCookies();
            Response response = PostController.createPost(cookies, createPost);
            isResponse200(response);
            createdPost = response.as(Post.class);
            postId = createdPost.postId;
            System.out.println("Successfully created a new post with Id" + " " + postId);
            isDeletedPost = false;
        }
    }

    @Test
    public void PostEdited_When_ClickEditPostButton() {
        createdPost.content = "I am looking for a painter";
        Response response = PostController.editPost(cookies, createdPost);
        isResponse200(response);
        System.out.println("Post with Id" + " " + createdPost.postId + " " + "edited successfully.");
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

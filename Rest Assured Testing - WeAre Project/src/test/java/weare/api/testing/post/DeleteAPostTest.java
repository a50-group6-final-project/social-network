package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import api.UserController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.Post;
import models.UserRegister;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeleteAPostTest extends BaseTestSetup {
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
        createdPost = response.as(Post.class);
    }

    @Test
    public void PostDeleted_When_ClickDeletePost() {
        authenticateAndFetchCookies();
        Post[] post = UserController.getProfilePosts(cookies, createdPost.postId).as(Post[].class);
        Response response = PostController.deletePost(cookies, createdPost.postId);
        isResponse200(response);

        Post[] posts = UserController.getProfilePosts(cookies, createdPost.postId).as(Post[].class);
        System.out.println("Post with Id" + " " + createdPost.postId + " " + "Deleted successfully.");
        isDeletedPost = true;

    }
}

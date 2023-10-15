package weare.api.testing.post;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.PostController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.PostModel;
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
        createdPost = response.as(PostModel.class);
    }

    @Test
    public void deletePost_Successful() {

        Response response = PostController.deletePost(cookies, createdPost.postId);
        isResponse200(response);

        System.out.println(response.asString());
        System.out.println("Post with Id" + " " + postId + " " + "Deleted successfully.");
        isDeletedPost = true;

    }
}

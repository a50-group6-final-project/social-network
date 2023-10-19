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

import static Utils.Constants.POST_DELETED_SUCCESS_MESSAGE;

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
    public void PostDeleted_When_ClickDeletePostButton() {

        Response response = PostController.deletePost(cookies, createdPost.postId);
        isResponse200(response);

        System.out.println(response.asString());
        System.out.println(String.format(POST_DELETED_SUCCESS_MESSAGE, postId));
        isDeletedPost = true;

    }
}

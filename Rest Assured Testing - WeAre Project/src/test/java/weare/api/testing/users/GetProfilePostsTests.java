package weare.api.testing.users;

import Utils.ModelGenerator;
import api.PostController;
import api.UserController;
import io.restassured.response.Response;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetProfilePostsTests extends BaseUserSetup {
    PostModel postToCreate;
    PostModel post;

    @BeforeClass
    public void setup() {
        if (isRegistered == false) {
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;
        }

        postToCreate = ModelGenerator.generatePostModel();
        Response postResponse = PostController.createPost(cookies, postToCreate);
        isResponse200(postResponse);
        post = postResponse.as(PostModel.class);
        System.out.println("Post created with Id: " + post.postId);
    }

    @Test
    public void getUserPosts_Successful() {

        Response response = UserController.getProfilePosts(cookies, currentUserId);
        isResponse200(response);

        PostModel[] getPostList = response.as(PostModel[].class);
        Assert.assertEquals(getPostList[0].postId, post.postId, "PostId does not match.");
        Assert.assertEquals(getPostList[0].content, post.content, "Content does not match.");
        Assert.assertEquals(getPostList[0].picture, post.picture, "Picture does not match.");
    }
}

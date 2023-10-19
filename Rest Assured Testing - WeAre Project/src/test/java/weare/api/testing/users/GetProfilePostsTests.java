package weare.api.testing.users;

import Utils.ModelGenerator;
import api.PostController;
import api.UserController;
import io.restassured.response.Response;
import models.Post;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.*;

public class GetProfilePostsTests extends BaseUserSetup {
    Post postToCreate;
    Post post;

    @BeforeClass
    public void setup() {
        if (isRegistered == false) {
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;
        }

        postToCreate = ModelGenerator.generatePostModel();
        authenticateAndFetchCookies();
        Response postResponse = PostController.createPost(cookies, postToCreate);
        isResponse200(postResponse);
        post = postResponse.as(Post.class);
        System.out.println(String.format(POST_CREATED_MESSAGE_FORMAT, post.postId));

    }

    @Test
    public void UserPostsRetrieved_When_ClickGetUserPosts() {

        Response response = UserController.getProfilePosts(cookies, currentUserId);
        isResponse200(response);

        Post[] getPostList = response.as(Post[].class);
        Assert.assertEquals(getPostList[0].postId, post.postId, POST_ID_MISMATCH_MESSAGE);
        Assert.assertEquals(getPostList[0].content, post.content, CONTENT_MISMATCH_MESSAGE);
        Assert.assertEquals(getPostList[0].picture, post.picture, PICTURE_MISMATCH_MESSAGE);

    }
}

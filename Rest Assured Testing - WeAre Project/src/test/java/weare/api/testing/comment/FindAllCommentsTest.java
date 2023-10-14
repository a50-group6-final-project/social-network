package weare.api.testing.comment;

import api.CommentController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.CommentModel;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindAllCommentsTest extends BaseTestSetup {
    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            postCreatorUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(postCreatorUsername, currentEmail);
            authenticateAndFetchCookies(postCreatorUsername, "Project.10");
            isRegistered = true;
            userId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + userId);
        }
    }

    @Test
    public void findAllComments_Successful() {

        Response response = CommentController.findAllComments(cookies);
        isResponse200(response);

        CommentModel[] commentList = response.as(CommentModel[].class);

        Assert.assertTrue(commentList.length >= 1, "The array size is more than or equal to 1");
        Assert.assertNotNull(commentList[0].content, "Content is null");
        System.out.println("Successfully fetched all comments.");
    }
}

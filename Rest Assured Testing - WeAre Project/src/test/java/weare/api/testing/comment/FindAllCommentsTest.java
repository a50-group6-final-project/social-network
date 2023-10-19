package weare.api.testing.comment;

import Utils.ModelGenerator;
import api.CommentController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.Comment;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.*;

public class FindAllCommentsTest extends BaseTestSetup {
    @BeforeClass
    public void setup() {
        if (!isRegistered) {
            UserRegister userRegister = ModelGenerator.generateUserRegisterModel();
            register(userRegister);
            System.out.println(USER_SUCCESS_MESSAGE + " " + currentUserId);
        }
    }

    @Test
    public void FindAllCommentsSuccessful() {

        Response response = CommentController.findAllComments(cookies);
        isResponse200(response);

        Comment[] commentList = response.as(Comment[].class);

        Assert.assertTrue(commentList.length >= 1, ARRAY_SIZE_MESSAGE);
        Assert.assertNotNull(commentList[0].content, CONTENT_NULL_MESSAGE);
        System.out.println(FETCH_COMMENTS_SUCCESS_MESSAGE);

    }
}

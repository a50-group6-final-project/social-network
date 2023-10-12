package weare.api.testing.post;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.EditPost;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class EditPostTest extends BaseTestSetup {
    @BeforeTest
    public void createPost_Successful() {
        String uniqueContent = generateUniqueContentPost();

    }

    @Test
    public void editPost_Successful() {

        editPost.content="I am looking for a painter";

        String bodyEditPostString = Serializer.convertObjectToJsonString(editPost);


        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("postId", postId)
                .body(bodyEditPostString)
                .when()
                .put(EDIT_POST_ENDPOINT);


        isResponse200(response);
//        Assert.assertNotNull(editPost);
        System.out.println(response.asString());
        System.out.println("Post with Id" + " " + postId + " " + "edited successfully.");
    }
}

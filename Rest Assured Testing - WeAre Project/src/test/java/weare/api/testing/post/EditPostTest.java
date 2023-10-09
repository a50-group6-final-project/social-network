package weare.api.testing.post;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.EDIT_POST_ENDPOINT;

public class EditPostTest extends BaseTestSetup {


    @Test
    public void editPost_Successful() {

        editPost.content="I am looking for a painter";

        String bodyEditPostString = Serializer.convertObjectToJsonString(editPost);


        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(bodyEditPostString)
                .when()
                .put(EDIT_POST_ENDPOINT + postId);


        isResponse200(response);
//        Assert.assertNotNull(editPost);
        System.out.println(response.asString());

        System.out.println("Post edited successfully.");
    }
}

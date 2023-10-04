package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static Utils.JSONRequests.CREATE_POST_BODY;
import static Utils.JSONRequests.EDIT_POST_BODY;
import static weare.api.testing.CreatePostTest.postId;

public class EditPostTest extends BaseTestSetup {

    @BeforeMethod
    public void createPostForEditing() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(CREATE_POST_BODY)
                .when()
                .post(CREATЕ_POST_ENDPOINT);

        postId = response.jsonPath().getInt("postId");
        System.out.println(response.asString());
    }


    @Test
    public void editPost_Successful() {

        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(EDIT_POST_BODY)
                .when()
                .put(EDIT_POST_ENDPOINT + postId);


        System.out.println(response.asString());
        AssertResponse(response);

        System.out.println("Post edited successfully.");
    }
}
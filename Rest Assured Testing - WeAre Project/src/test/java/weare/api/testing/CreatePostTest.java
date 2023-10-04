package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.CREATЕ_POST_ENDPOINT;
import static Utils.JSONRequests.CONTENT_POST;
import static Utils.JSONRequests.CREATE_POST_BODY;
import static org.testng.Assert.assertEquals;

public class CreatePostTest extends BaseTestSetup {


    @Test
    public void createPost_Successful() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(CREATE_POST_BODY)
                .when()
                .post(CREATЕ_POST_ENDPOINT);

        System.out.println(response.asString());
        AssertResponse(response);
        String contentFromResponse = response.jsonPath().getString("content");

        assertEquals(contentFromResponse, CONTENT_POST, "Content does not match.");

        System.out.println("Successfully created a new post.");
    }

}
package weare.api.testing.post;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.CREATЕ_POST_ENDPOINT;
import static Utils.JSONRequests.CREATE_POST_BODY;
import static org.testng.Assert.assertEquals;

public class CreatePostTest extends BaseTestSetup {


    @Test
    public void createPost_Successful() {
        String uniqueContent = generateUniqueContentPost();

        RestAssured.baseURI = BASE_URL;


        String body = String.format(CREATE_POST_BODY, uniqueContent);


        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(body)
                .when()
                .post(CREATЕ_POST_ENDPOINT);

        System.out.println(response.asString());
        isResponse200(response);

        String contentFromResponse = response.jsonPath().getString("content");
        assertEquals(contentFromResponse, uniqueContent, "Content does not match.");

        postId = response.jsonPath().getInt("postId");

        System.out.println("Successfully created a new post.");

    }


}
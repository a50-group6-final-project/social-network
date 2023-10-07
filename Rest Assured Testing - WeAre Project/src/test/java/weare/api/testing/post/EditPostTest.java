package weare.api.testing.post;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.EDIT_POST_ENDPOINT;
import static Utils.JSONRequests.EDIT_POST_BODY;

public class EditPostTest extends BaseTestSetup {


    @Test
    public void editPost_Successful() {
        currentUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();
        register(currentUsername, currentEmail);
        authenticateAndFetchCookies(senderUsername,"Project.10");
        CreatePostTest createPostTest = new CreatePostTest();
        createPostTest.createPost_Successful();

        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(EDIT_POST_BODY)
                .when()
                .put(EDIT_POST_ENDPOINT + postId);


        isResponse200(response);
        System.out.println(response.asString());

        System.out.println("Post edited successfully.");
    }
}

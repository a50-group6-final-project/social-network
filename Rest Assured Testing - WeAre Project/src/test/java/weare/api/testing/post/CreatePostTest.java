package weare.api.testing.post;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
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
        String contentFromResponse = response.jsonPath().getString("content");

        isResponse200(response);
        assertEquals(contentFromResponse, uniqueContent, "Content does not match.");
        Assert.assertNotNull(response.jsonPath().get("postId"), "postId is null");
        Assert.assertNotNull(response.jsonPath().get("postId"), "postId is null");
        Assert.assertNotNull(response.jsonPath().get("content"), "content is null");
        Assert.assertNotNull(response.jsonPath().get("picture"), "picture is null");
        Assert.assertNotNull(response.jsonPath().get("date"), "date is null");
        Assert.assertNotNull(response.jsonPath().get("likes"), "likes is null");
        Assert.assertNotNull(response.jsonPath().get("comments"), "comments is null");
        Assert.assertNotNull(response.jsonPath().get("rank"), "rank is null");
        Assert.assertNotNull(response.jsonPath().get("category.id"), "category.id is null");
        Assert.assertNotNull(response.jsonPath().get("category.name"), "category.name is null");
        Assert.assertNotNull(response.jsonPath().get("liked"), "liked is null");
        Assert.assertNotNull(response.jsonPath().get("public"), "public is null");

        postId = response.jsonPath().getInt("postId");

        System.out.println("Successfully created a new post. All properties are not null.");

    }


}
package weare.api.testing.post;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class LikeDislikeAPostTest extends BaseTestSetup {
    @BeforeTest
    public void createPost_Successful() {
        String uniqueContent = generateUniqueContentPost();
        createPost = new PostModel();
        createPost.content = uniqueContent;
        createPost.picture = "";
        createPost.mypublic = true;

        String bodyPostString = Serializer.convertObjectToJsonString(createPost);

        postCreatorUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(postCreatorUsername, currentEmail);
        authenticateAndFetchCookies(postCreatorUsername, "Project.10");

        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(bodyPostString)
                .when()
                .post(CREATЕ_POST_ENDPOINT);

        System.out.println(response.asString());

        String contentFromResponse = response.jsonPath().getString("content");
        assertEquals(contentFromResponse, uniqueContent, "Content does not match.");

        isResponse200(response);
        postId = response.jsonPath().getInt("postId");
        System.out.println("Successfully created a new post with Id" + " " + postId);
    }
    @Test
    public void likePost_Successful()  {

        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("postId", postId)
                .when()
                .post(LIKE_POST_ENDPOINT);


        isResponse200(response);
        // Extract the "liked" property from the response JSON
        boolean liked = response.jsonPath().getBoolean("liked");
        // Assert that the post is liked
        Assert.assertEquals(liked, true, "The post is not liked.");
        System.out.println(response.asString());
        System.out.println("Post with Id" + " " + postId + " " + "Liked successfully.");

    }
    @AfterTest
    public void dislikePost_Successful()  {

        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("postId", postId)
                .when()
                .post(LIKE_POST_ENDPOINT);


        isResponse200(response);
        // Extract the "liked" property from the response JSON
        boolean liked = response.jsonPath().getBoolean("liked");
        // Assert that the post is disliked
        Assert.assertEquals(liked, false, "The post is liked.");
        System.out.println(response.asString());
        System.out.println("Post with Id" + " " + postId + " " + "Disliked successfully.");

    }
}

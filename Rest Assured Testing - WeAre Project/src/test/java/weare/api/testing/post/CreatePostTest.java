package weare.api.testing.post;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.EditPost;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.CREATЕ_POST_ENDPOINT;
import static org.testng.Assert.assertEquals;

public class CreatePostTest extends BaseTestSetup {


    @Test
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
        editPost=response.as(EditPost.class);
        Assert.assertNotNull(editPost.postId);
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


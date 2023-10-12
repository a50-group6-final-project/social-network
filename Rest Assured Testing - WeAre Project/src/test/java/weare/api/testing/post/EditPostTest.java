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
        postId = response.jsonPath().getInt("postId");
        System.out.println("Successfully created a new post with Id" + " " + postId);
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

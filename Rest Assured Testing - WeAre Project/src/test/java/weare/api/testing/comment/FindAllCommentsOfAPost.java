package weare.api.testing.comment;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CommentModel;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class FindAllCommentsOfAPost extends BaseTestSetup {

    @BeforeSuite
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

        postId = response.jsonPath().getInt("postId");
        userId = BaseTestSetup.currentUserId;

        System.out.println("Successfully created a new post with Id" + " " + postId);
        System.out.println("Successfully created a new user with Id" + " " + userId);
    }
    @BeforeTest
    public void createComment_Successful(){
        String uniqueContent = generateUniqueContentPost();

        createComment = new CommentModel();
        createComment.commentId = 0;
        createComment.content = uniqueContent;
        createComment.deletedConfirmed = true;
        createComment.postId = postId;
        createComment.userId = userId;

        String bodyCommentString = Serializer.convertObjectToJsonString(createComment);

        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(bodyCommentString)
                .when()
                .post(CREATЕ_COMMENT_ENDPOINT);

        System.out.println(response.asString());
        String contentFromResponse = response.jsonPath().getString("content");
        assertEquals(contentFromResponse, uniqueContent, "Content does not match.");

        commentId = response.jsonPath().getInt("commentId");
        System.out.println("Successfully created a new comment with Id" + " " + commentId);
    }
    @Test
    public void findAllCommentsOfAPost_Successful(){
        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.given()
                .cookies(cookies)
                .queryParam("postId", postId)
                .when()
                .get(FIND_ALL_COMMENTS_OF_A_POST_ENDPOINT);

        System.out.println(response.asString());
        isResponse200(response);

        int arraySize = response.jsonPath().getList("$").size();
        String content = response.jsonPath().getString("content[0]");

        Assert.assertTrue(arraySize >= 1, "The array size is more than or equal to 1");
        Assert.assertNotNull(content, "Content is null");
        System.out.println("Successfully fetched all comments of post with Id" + " " + postId + " " + "successfully.");
    }
}

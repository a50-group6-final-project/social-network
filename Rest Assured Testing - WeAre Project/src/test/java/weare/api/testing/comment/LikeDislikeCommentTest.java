package weare.api.testing.comment;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.CommentModel;
import models.PostModel;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;
import static org.testng.Assert.assertEquals;

public class LikeDislikeCommentTest extends BaseTestSetup {

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
    public void likeComment_Successful() {

        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("commentId", commentId)
                .when()
                .post(LIKE_COMMENT_ENDPOINT);


        isResponse200(response);
        // Extract the "liked" property from the response JSON
        boolean liked = response.jsonPath().getBoolean("liked");
        // Assert that the post is liked
        Assert.assertEquals(liked, true, "The post is not liked.");
        System.out.println(response.asString());
        System.out.println("Comment with Id" + " " + commentId + " " + "Liked successfully.");
    }

    @AfterTest
    public void dislikeComment_Successful()  {

        Response response = RestAssured.given().baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("commentId", commentId)
                .when()
                .post(LIKE_COMMENT_ENDPOINT);


        isResponse200(response);
        // Extract the "liked" property from the response JSON
        boolean liked = response.jsonPath().getBoolean("liked");
        // Assert that the post is disliked
        Assert.assertEquals(liked, false, "The post is liked.");
        System.out.println(response.asString());
        System.out.println("Comment with Id" + " " + commentId + " " + "Disliked successfully.");

    }
}

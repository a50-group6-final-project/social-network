package api;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.PostModel;

import static Utils.Endpoints.*;

public class PostController {

    public static Response createPost(Cookies cookies, PostModel post) {
        String bodyPostString = Serializer.convertObjectToJsonString(post);
        return RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(bodyPostString)
                .when()
                .post(CREATЕ_POST_ENDPOINT);
    }

    public static Response deletePost(Cookies cookies, int postId) {
        return RestAssured.given().baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("postId", postId)
                .when()
                .delete(DELETE_POST_ENDPOINT);
    }

    public static Response editPost(Cookies cookies, PostModel editedPost) {
        String bodyEditPostString = Serializer.convertObjectToJsonString(editedPost);
        return RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("postId", editedPost.postId)
                .body(bodyEditPostString)
                .when()
                .put(EDIT_POST_ENDPOINT);
    }

    public static Response getNewsFeed(Cookies cookies) {
        return RestAssured.given()
                .baseUri(BASE_URL)
                .cookies(cookies)
                .queryParam("sorted", true)
                .log().headers()
                .when()
                .get(GET_ALL_POSTS_ENDPOINT);
    }

    public static Response likeAndDislikePost(Cookies cookies, int postId) {
        return RestAssured.given().baseUri(BASE_URL)
                .cookies(cookies)
                .contentType("application/json")
                .queryParam("postId", postId)
                .when()
                .post(LIKE_POST_ENDPOINT);
    }
}

package api;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.CommentModel;

import static Utils.Endpoints.CREATЕ_COMMENT_ENDPOINT;
import static Utils.Endpoints.DELETE_COMMENT_ENDPOINT;

public class CommentController {

    public static Response createComment(Cookies cookies, CommentModel createComment) {
        String bodyCommentString = Serializer.convertObjectToJsonString(createComment);
        return RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(bodyCommentString)
                .when()
                .post(CREATЕ_COMMENT_ENDPOINT);
    }

    public static Response deleteComment(Cookies cookies, int commentId) {
        return RestAssured.given()
                .cookies(cookies)
                .queryParam("commentId", commentId)
                .when()
                .delete(DELETE_COMMENT_ENDPOINT);
    }
}

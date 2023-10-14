package api;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.CommentModel;

import static Utils.Endpoints.*;

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
    public static Response editComment(){
        return null;
    }

    public static Response findAllComments(Cookies cookies){
        return RestAssured.given()
                .cookies(cookies)
                .when()
                .get(FIND_ALL_COMMENTS_ENDPOINT);
    }

    public static Response findAllCommentsOfAPost(){
        return null;
    }

    public static Response findOneCommentOfAPost(){
        return null;
    }

    public static Response LikeDislikeComment(){
        return null;
    }
}

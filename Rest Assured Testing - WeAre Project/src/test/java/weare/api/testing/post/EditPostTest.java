package weare.api.testing.post;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.PostModel;
import org.testng.annotations.Test;

import static Utils.Endpoints.EDIT_POST_ENDPOINT;

public class EditPostTest extends BaseTestSetup {


    @Test
    public void editPost_Successful() {
        PostModel editPost = new PostModel();
        editPost.content = "I am looking for a painter";
        editPost.picture = "";
        editPost.mypublic = true;

        String bodyEditPostString = Serializer.convertObjectToJsonString(editPost);

        CreatePostTest createPostTest = new CreatePostTest();
        createPostTest.createPost_Successful();
        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(bodyEditPostString)
                .when()
                .put(EDIT_POST_ENDPOINT + postId);


        isResponse200(response);
        System.out.println(response.asString());

        System.out.println("Post edited successfully.");
    }
}

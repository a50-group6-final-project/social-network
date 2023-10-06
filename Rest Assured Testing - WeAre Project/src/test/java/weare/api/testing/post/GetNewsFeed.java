package weare.api.testing.post;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_ALL_POSTS_ENDPOINT;

public class GetNewsFeed extends BaseTestSetup {

    @Test
    public void getAllPosts_Successful() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .when()
                .get(GET_ALL_POSTS_ENDPOINT);

        System.out.println(response.asString());
        isResponse200(response);


        System.out.println("Successfully fetched all posts.");
    }
}



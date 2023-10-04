package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_ALL_POSTS_ENDPOINT;

public class FindAllPostTest extends BaseTestSetup {

    @Test
    public void getAllPostsTest() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .queryParam("sorted", "true")
                .queryParam("unsorted", "false")
                .when()
                .get(GET_ALL_POSTS_ENDPOINT);

        System.out.println(response.asString());
        AssertResponse(response);


        System.out.println("Successfully fetched all posts.");
    }
}



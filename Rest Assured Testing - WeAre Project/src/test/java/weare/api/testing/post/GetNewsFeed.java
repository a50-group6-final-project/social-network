package weare.api.testing.post;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_ALL_POSTS_ENDPOINT;

public class GetNewsFeed extends BaseTestSetup {

    @Test
    public void getAllPosts_Successful() {
        currentUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();
        register(currentUsername, currentEmail);
        authenticateAndFetchCookies(senderUsername,"Project.10");
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .when()
                .get(GET_ALL_POSTS_ENDPOINT);

        System.out.println(response.asString());
        isResponse200(response);

        int arraySize = response.jsonPath().getList("$").size();
        String content = response.jsonPath().getString("content[0]");

        Assert.assertTrue(arraySize >= 1, "The array size is more than or equal to 1");
        Assert.assertNotNull(content, "Content is null");
        System.out.println("Successfully fetched all posts.");
    }
}



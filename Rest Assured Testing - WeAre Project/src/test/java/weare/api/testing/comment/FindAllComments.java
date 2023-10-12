package weare.api.testing.comment;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.*;

public class FindAllComments extends BaseTestSetup {
    @Test
    public void findAllComments_Successful() {
        postCreatorUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();
        register(postCreatorUsername, currentEmail);
        authenticateAndFetchCookies(postCreatorUsername, "Project.10");

        RestAssured.baseURI = BASE_URL;
        Response response = RestAssured.given()
                .cookies(cookies)
                .when()
                .get(FIND_ALL_COMMENTS_ENDPOINT);

        System.out.println(response.asString());
        isResponse200(response);

        int arraySize = response.jsonPath().getList("$").size();
        String content = response.jsonPath().getString("content[0]");

        Assert.assertTrue(arraySize >= 1, "The array size is more than or equal to 1");
        Assert.assertNotNull(content, "Content is null");
        System.out.println("Successfully fetched all comments.");
    }
}

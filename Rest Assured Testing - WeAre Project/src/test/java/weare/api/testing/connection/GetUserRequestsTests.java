package weare.api.testing.connection;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static org.testng.Assert.assertEquals;

public class GetUserRequestsTests extends BaseTestSetup {

    @Test
    public void getUserRequestTest() {

        receiverUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(receiverUsername, currentEmail);
        receiverUserId = currentUserId;
        authenticateAndFetchCookies(receiverUsername, "Password.10");
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .pathParam("receiverUserId", receiverUserId)
                .when()
                .get("/api/auth/users/{receiverUserId}/request/");


        int statusCode = response.getStatusCode();
        String responseBody = response.getBody().asString();
        assertEquals(200, statusCode);
        System.out.println(response.asString());
        System.out.println("The status code is: " + statusCode);
        System.out.println("The response body is: " + responseBody);
    }

}

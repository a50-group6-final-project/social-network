package weare.api.testing.connection;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class ApproveRequestTest extends BaseTestSetup {


    @Test
    public void approveRequest_successful() {

        authenticateAndFetchCookies(receiverUsername, "Password.10");
        RestAssured.baseURI = BASE_URL;


        Response response = RestAssured.given()
                .cookies(cookies)
                .pathParam("receiverUserId", receiverUserId)
                .queryParam("requestId", idRequest)
                .when()
                .post("/api/auth/users/{receiverUserId}/request/?requestId=");

        int statusCode = response.getStatusCode();
        System.out.println("The status code is: " + statusCode);
    }
}

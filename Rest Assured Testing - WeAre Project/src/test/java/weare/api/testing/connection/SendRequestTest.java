package weare.api.testing.connection;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.JSONRequests.SEND_REQUEST_BODY;
import static org.testng.Assert.assertEquals;


public class SendRequestTest extends BaseTestSetup {


    @Test
    public void sendRequest_successful() {

        receiverUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(receiverUsername, currentEmail);
        receiverUserId = currentUserId;

        senderUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(senderUsername, currentEmail);
        authenticateAndFetchCookies(senderUsername, "Password.10");
        senderUserId = currentUserId;

        RestAssured.baseURI = BASE_URL;

        String requestBody = String.format(SEND_REQUEST_BODY, receiverUserId, receiverUsername);

        Response response = RestAssured.given()
                .cookies(cookies)
                .header("name", senderUsername)
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/auth/request");

        System.out.println(response.asString());

        String responseBody = response.getBody().asString();


        String[] parts = responseBody.split(" ");
        String senderNameInResponse = parts[0];
        String receiverNameInResponse = parts[parts.length - 1];

        isResponse200(response);
        Assert.assertEquals(senderUsername, senderNameInResponse, "Sender name does not match!");
        Assert.assertEquals(receiverUsername, receiverNameInResponse, "Receiver name does not match!");
    }


@Test
    public void getUserRequestTest() {

//        receiverUsername = generateUniqueUsername();
//        currentEmail = generateUniqueEmail();
//
//        register(receiverUsername, currentEmail);
//        receiverUserId = currentUserId;
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



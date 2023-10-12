package weare.api.testing.connection;

import Utils.Serializer;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SendRequest;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;


public class SendRequestTest extends BaseTestSetup {


    @Test
    public void sendRequest_successful() {

        receiverUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(receiverUsername, currentEmail);
        receiverUserId = currentUserId;

        sendRequestToUser = new SendRequest();
        sendRequestToUser.id = receiverUserId;
        sendRequestToUser.username = receiverUsername;


        senderUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(senderUsername, currentEmail);
        authenticateAndFetchCookies(senderUsername, "Password.10");
        senderUserId = currentUserId;

        RestAssured.baseURI = BASE_URL;

        String bodySendRequest = Serializer.convertObjectToJsonString(sendRequestToUser);

        Response response = RestAssured.given()
                .cookies(cookies)
                .header("name", senderUsername)
                .contentType("application/json")
                .body(bodySendRequest)
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


}

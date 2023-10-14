package weare.api.testing.connection;

import Utils.ModelGenerator;
import Utils.Serializer;
import api.ConnectionController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.SendRequest;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;


public class SendRequestTest extends BaseConnectionSetup {
    Cookies sendCookies;
    Cookies receiveCookies;
    @BeforeClass
    public void setup(){
        sendRequestToUser = ModelGenerator.generateSendRequestModel(receiverUserId, receiverUsername);
    }
    @Test
    public void sendRequest_successful() {
        authenticateAndFetchCookies(senderUsername, "Password.10");
        sendCookies = cookies;

        Response response = ConnectionController.sendRequest(sendRequestToUser, sendCookies, senderUsername);
        isResponse200(response);
        System.out.println(response.asString());

        String responseBody = response.getBody().asString();
        String[] parts = responseBody.split(" ");
        String senderNameInResponse = parts[0];
        String receiverNameInResponse = parts[parts.length - 1];


        Assert.assertEquals(senderUsername, senderNameInResponse, "Sender name does not match!");
        Assert.assertEquals(receiverUsername, receiverNameInResponse, "Receiver name does not match!");
    }

    @AfterClass
    public void tearDown(){
        isRequestSent = true;
    }

}

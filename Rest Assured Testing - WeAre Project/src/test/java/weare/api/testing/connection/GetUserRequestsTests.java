package weare.api.testing.connection;

import Utils.ModelGenerator;
import api.ConnectionController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ApproveRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class GetUserRequestsTests extends BaseConnectionSetup {

    Response sentRequestResponse;

    @BeforeTest
    public void setup() {
        if (!isRegistered) {
            senderUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(senderUsername, currentEmail);
            authenticateAndFetchCookies(senderUsername, "Password.10");
            senderCookies = cookies;
            senderUserId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + senderUserId);

            receiverUsername = generateUniqueUsername();
            currentEmail = generateUniqueEmail();
            register(receiverUsername, currentEmail);
            authenticateAndFetchCookies(receiverUsername, "Password.10");
            receiverCookies = cookies;
            receiverUserId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + receiverUserId);

            isRegistered = true;
        }
        sendRequestToUser = ModelGenerator.generateSendRequestModel(receiverUserId, receiverUsername);
        sentRequestResponse = ConnectionController.sendRequest(sendRequestToUser, senderCookies, senderUsername);
        System.out.println(sentRequestResponse.asString());
    }

    @Test
    public void getUserRequestTest() {
        authenticateAndFetchCookies(receiverUsername, "Password.10");
        Response response = ConnectionController.getUserRequests(cookies, receiverUserId);


        isResponse200(response);
        ApproveRequest[] approveRequestList = response.as(ApproveRequest[].class);
        Assert.assertEquals(approveRequestList.length >= 1, true, "The array size is more than or equal to 1");
    }

}

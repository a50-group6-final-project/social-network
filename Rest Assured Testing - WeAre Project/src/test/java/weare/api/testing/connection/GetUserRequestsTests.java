package weare.api.testing.connection;

import utils.ModelGenerator;
import api.ConnectionController;
import io.restassured.response.Response;
import models.ApproveRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static utils.Constants.ARRAY_SIZE_MESSAGE;

public class GetUserRequestsTests extends BaseConnectionSetup {

    Response sentRequestResponse;

    @BeforeClass
    public void setupTest() {
        authenticateAndFetchCookies(senderUsername, senderPassword);
        sendRequestToUser = ModelGenerator.generateSendRequestModel(receiverUserId, receiverUsername);
        sentRequestResponse = ConnectionController.sendRequest(sendRequestToUser, cookies, senderUsername);
        System.out.println(sentRequestResponse.asString());
    }

    @Test
    public void RequestReceived_When_GetUserRequest() {
        authenticateAndFetchCookies(receiverUsername, receiverPassword);
        Response response = ConnectionController.getUserRequests(cookies, receiverUserId);


        isResponse200(response);
        ApproveRequest[] approveRequestList = response.as(ApproveRequest[].class);
        Assert.assertEquals(approveRequestList.length >= 1, true, ARRAY_SIZE_MESSAGE);
    }

}

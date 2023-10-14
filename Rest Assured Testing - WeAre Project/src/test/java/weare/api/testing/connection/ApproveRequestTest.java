package weare.api.testing.connection;

import Utils.ModelGenerator;
import api.CommentController;
import api.ConnectionController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ApproveRequest;
import models.SendRequest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class ApproveRequestTest extends BaseConnectionSetup {
    int requestId;
    @BeforeClass
    public void setup(){
        sendRequestToUser = ModelGenerator.generateSendRequestModel(receiverUserId, receiverUsername);
        authenticateAndFetchCookies(senderUsername, "Password.10");
        Response response = ConnectionController.sendRequest(sendRequestToUser, cookies, senderUsername);

        authenticateAndFetchCookies(receiverUsername, "Password.10");
        Response response2 = ConnectionController.getUserRequests(cookies, receiverUserId);


        isResponse200(response2);
        ApproveRequest[] approveRequestList = response2.as(ApproveRequest[].class);
        requestId = approveRequestList[0].id;
    }
    @Test
    public void approveRequest_successful() {

        authenticateAndFetchCookies(receiverUsername, "Password.10");
        ConnectionController.approveRequest(cookies, receiverUserId, requestId);
        Response response = ConnectionController.approveRequest(cookies, receiverUserId, requestId);

        int statusCode = response.getStatusCode();
        System.out.println("The status code is: " + statusCode);
    }
}

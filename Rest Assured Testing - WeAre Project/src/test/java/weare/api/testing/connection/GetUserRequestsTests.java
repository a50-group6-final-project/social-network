package weare.api.testing.connection;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import api.ConnectionController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import models.ApproveRequest;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class GetUserRequestsTests extends BaseConnectionSetup {

    Response sentRequestResponse;

    @BeforeClass
    public void setupTest() {
        authenticateAndFetchCookies(senderUsername, "Project.10");
        sendRequestToUser = ModelGenerator.generateSendRequestModel(receiverUserId, receiverUsername);
        sentRequestResponse = ConnectionController.sendRequest(sendRequestToUser, cookies, senderUsername);
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

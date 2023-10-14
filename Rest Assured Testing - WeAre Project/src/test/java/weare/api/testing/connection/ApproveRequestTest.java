package weare.api.testing.connection;

import api.CommentController;
import api.ConnectionController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class ApproveRequestTest extends BaseConnectionSetup {

    @BeforeClass
    public void setup(){

    }

    @Test
    public void approveRequest_successful() {

        authenticateAndFetchCookies(receiverUsername, "Password.10");

        Response response = ConnectionController.approveRequest(cookies, receiverUserId, idRequest);
        isResponse200(response);
        int statusCode = response.getStatusCode();
        System.out.println("The status code is: " + statusCode);
    }
}

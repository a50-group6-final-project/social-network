package weare.api.testing.connection;

import Utils.ModelGenerator;
import api.ConnectionController;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SendRequestTest extends BaseConnectionSetup {

    @BeforeClass
    public void setup() {
        sendRequestToUser = ModelGenerator.generateSendRequestModel(receiverUserId, receiverUsername);
    }

    @Test
    public void sendRequest_successful() {
        authenticateAndFetchCookies(senderUsername, senderPassword);
        Response response = ConnectionController.sendRequest(sendRequestToUser, cookies, senderUsername);
        isResponse200(response);
        System.out.println(response.asString());

        String responseBody = response.getBody().asString();
        String[] parts = responseBody.split(" ");
        String senderNameInResponse = parts[0];
        String receiverNameInResponse = parts[parts.length - 1];


        Assert.assertEquals(senderUsername, senderNameInResponse, "Sender name does not match!");
        Assert.assertEquals(receiverUsername, receiverNameInResponse, "Receiver name does not match!");
    }

}

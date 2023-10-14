package weare.api.testing.connection;

import base.BaseTestSetup;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.SendRequest;
import org.testng.annotations.BeforeClass;

public class BaseConnectionSetup extends BaseTestSetup {
    public Cookies senderCookies;
    public Cookies receiverCookies;
    SendRequest sendRequestToUser;
    Response sentRequestResponse;
    @BeforeClass
    public void setupClass(){
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
    }
}

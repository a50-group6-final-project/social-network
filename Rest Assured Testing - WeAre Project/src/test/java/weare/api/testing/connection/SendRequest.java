package weare.api.testing.connection;

import base.BaseTestSetup;
import org.testng.annotations.Test;


public class SendRequest extends BaseTestSetup {

    private int senderUserId;
    private int receiverUserId;

    @Test
    public void sendRequest_successful() {
        senderUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();

        register(senderUsername, currentEmail);
        authenticateAndFetchCookies(senderUsername, "Password.10");
        senderUserId = currentUserId;
        System.out.println("senderUserId: " + senderUserId);

        receiverUsername = generateUniqueUsername();
        receiverEmail = generateUniqueEmail();

        register(receiverUsername, receiverEmail);
        receiverUserId = currentUserId;
        System.out.println("receiverUserId: " + receiverUserId);

        authenticateAndFetchCookies(receiverUsername, "Password.10");
    }
}



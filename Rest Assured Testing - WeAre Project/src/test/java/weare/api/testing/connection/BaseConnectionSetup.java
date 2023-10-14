package weare.api.testing.connection;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import base.BaseTestSetup;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.SendRequest;
import models.UserRegister;
import org.testng.annotations.BeforeClass;

public class BaseConnectionSetup extends BaseTestSetup {
    public Cookies senderCookies;
    public Cookies receiverCookies;
    SendRequest sendRequestToUser;
    Response sentRequestResponse;
    @BeforeClass
    public void setupClass() throws InterruptedException {
        Thread.sleep(1000);
        if (!isRegisteredTwoUsers) {
            UserRegister userRegisterOne = ModelGenerator.generateUserRegisterModel();
            senderUsername = userRegisterOne.username;
            currentEmail = userRegisterOne.email;
            register(userRegisterOne);
            senderCookies = cookies;
            senderUserId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + senderUserId);

            UserRegister userRegisterTwo = ModelGenerator.generateUserRegisterModel();

            receiverUsername = userRegisterTwo.username;
            receiverEmail = userRegisterTwo.email;
            register(userRegisterTwo);
            receiverCookies = cookies;
            receiverUserId = currentUserId;
            System.out.println("Successfully created a new user with Id" + " " + receiverUserId);

            isRegisteredTwoUsers = true;
        }
    }
}

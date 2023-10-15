package weare.api.testing.connection;

import Utils.ModelGenerator;
import base.BaseTestSetup;
import io.restassured.http.Cookies;
import models.SendRequest;
import models.UserRegister;
import org.testng.annotations.BeforeClass;

public class BaseConnectionSetup extends BaseTestSetup {
    public Cookies senderCookies;
    public String senderPassword;
    public String receiverPassword;

    public Cookies receiverCookies;
    SendRequest sendRequestToUser;

    @BeforeClass
    public void setupClass() {
        UserRegister userRegisterOne = ModelGenerator.generateUserRegisterModel();
        senderUsername = userRegisterOne.username;
        currentEmail = userRegisterOne.email;
        senderPassword = userRegisterOne.password;
        register(userRegisterOne);
        senderCookies = cookies;
        senderUserId = currentUserId;
        System.out.println("Successfully created a new user with Id" + " " + senderUserId);

        UserRegister userRegisterTwo = ModelGenerator.generateUserRegisterModel();

        receiverUsername = userRegisterTwo.username;
        receiverEmail = userRegisterTwo.email;
        receiverPassword = userRegisterTwo.password;
        register(userRegisterTwo);
        receiverCookies = cookies;
        receiverUserId = currentUserId;
        System.out.println("Successfully created a new user with Id" + " " + receiverUserId);

        isRegisteredTwoUsers = true;
    }
}

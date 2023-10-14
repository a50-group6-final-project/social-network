package weare.api.testing.users;

import Utils.ModelGenerator;
import api.UserController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserPersonal;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class GetUserByIdTests extends BaseUserSetup {

    @BeforeClass
    public void setup() {
        if(isRegistered == false){
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;
        }

    }
    @Test
    public void getUserById_Successful() {
        Response response = UserController.getUserById(userToRegister.username, currentUserId);

        isResponse200(response);

        userPersonal = response.as(UserPersonal.class);

        Assert.assertEquals(userPersonal.username, currentUsername, "Usernames don't match!");
        Assert.assertEquals(userPersonal.email, currentEmail, "Emails don't match!");
        Assert.assertEquals(userPersonal.id, currentUserId, "IDs don't match!");
    }
}

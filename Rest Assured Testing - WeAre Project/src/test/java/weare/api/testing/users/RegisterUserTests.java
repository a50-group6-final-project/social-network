package weare.api.testing.users;

import Utils.ModelGenerator;
import api.UserController;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterUserTests extends BaseUserSetup {
    @Test
    public void registerUserSuccessfully () {
        userToRegister = ModelGenerator.generateUserRegisterModel();
        Response response = UserController.registerUser(userToRegister);
        isResponse200(response);

        String[] responseString = response.asString().split(" ");
        Assert.assertEquals(responseString[3], userToRegister.username);
        currentUsername = userToRegister.username;
        currentUserId = Integer.parseInt(responseString[6]);
        System.out.println("Registered successfully!");
    }
}

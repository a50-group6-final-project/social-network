package weare.api.testing.users;

import Utils.Serializer;
import api.UserController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Category;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.SecureRandom;

import static Utils.Endpoints.*;

public class RegisterUserTests extends BaseUserSetup {
    @Test
    public void registerUserSuccessfully () {
        Response response = UserController.registerUser(userToRegister);
        isResponse200(response);

        String[] responseString = response.asString().split(" ");
        Assert.assertEquals(responseString[3], currentUsername);

        currentUserId = Integer.parseInt(responseString[6]);
        System.out.println("Registered successfully!");
    }
}

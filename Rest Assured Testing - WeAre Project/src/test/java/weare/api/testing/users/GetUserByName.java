package weare.api.testing.users;

import Utils.DataGenerator;
import Utils.ModelGenerator;
import Utils.Serializer;
import api.UserController;
import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SearchUser;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_USERS_BY_NAME_ENDPOINT;
import static Utils.JSONRequests.GET_USER_BY_NAME_BODY;
import static org.testng.Assert.assertEquals;

public class GetUserByName extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if(isRegistered == false){
            userToRegister = ModelGenerator.generateUserRegisterModel();

        }
        if (currentUserPersonalProfile == null) {
            currentUserPersonalProfile = ModelGenerator.generateUserPersonalModel();
        }
        register(userToRegister);
    }
    @Test
    public void getUserByName_Successful() {

        SearchUser searchUser = ModelGenerator.generateSearchUserModel("Lili Ivanova");
        Response response = UserController.getUserByName(cookies, searchUser);

        System.out.println(response.asString());

        isResponse200(response);

        String username = response.jsonPath().getString("[0].username");
        int userId = response.jsonPath().getInt("[0].userId");

        Assert.assertEquals(username, "Bo", "Username does not match.");
        Assert.assertEquals(userId, 779, "UserId does not match.");


    }
}
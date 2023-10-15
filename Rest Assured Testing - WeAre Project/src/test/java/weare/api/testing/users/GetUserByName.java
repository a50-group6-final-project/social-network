package weare.api.testing.users;

import Utils.ModelGenerator;
import api.UserController;
import base.BaseTestSetup;
import io.restassured.response.Response;
import models.SearchUser;
import models.UserPersonal;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class GetUserByName extends BaseTestSetup {

    @BeforeClass
    public void setup() {
        if(isRegistered == false){
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;
        }
        if (currentUserPersonalProfile == null) {
            currentUserPersonalProfile = ModelGenerator.generateUserPersonalModel();
        }
    }
    @Test
    public void getUserByName_Successful() {
        SearchUser searchUser = ModelGenerator.generateSearchUserModel("Lili Ivanova");
        Response response = UserController.getUserByName(cookies, searchUser);

        isResponse200(response);

        UserPersonal[] userPersonalList = response.as(UserPersonal[].class);
        Assert.assertEquals(userPersonalList[0].username, "Bo", "Username does not match.");
        Assert.assertEquals(userPersonalList[0].id, 779, "User Id does not match.");
    }
}
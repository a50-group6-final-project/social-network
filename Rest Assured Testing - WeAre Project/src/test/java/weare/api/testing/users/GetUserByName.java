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

import static Utils.Constants.*;

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
        SearchUser searchUser = ModelGenerator.generateSearchUserModel(SEARCH_USER_NAME);
        Response response = UserController.getUserByName(cookies, searchUser);

        isResponse200(response);

        UserPersonal[] userPersonalList = response.as(UserPersonal[].class);
        Assert.assertEquals(userPersonalList[0].username, EXPECTED_USERNAME, USERNAME_DOES_NOT_MATCH_MESSAGE);
//        Assert.assertEquals(userPersonalList[0].id, 779, "User Id does not match.");
    }
}
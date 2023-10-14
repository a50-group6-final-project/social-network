package weare.api.testing.users;

import Utils.ModelGenerator;
import Utils.Serializer;
import api.UserController;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserPersonal;
import models.UserProfile;
import models.UserRegister;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateUserPersonalProfile extends BaseUserSetup {
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
    public void updateUserPersonalProfile_Successful() {

//        authenticateAndFetchCookies(currentUsername, "Project.10");
        Response response = UserController.updatePersonalProfile(cookies, currentUserPersonalProfile, currentUserId);

        System.out.println(response.asString());
        isResponse200(response);

        UserPersonal returnedUserPersonalProfile = response.as(UserPersonal.class);

        Assert.assertEquals(returnedUserPersonalProfile.firstName, currentUserPersonalProfile.firstName, "First name does not match.");
        Assert.assertEquals(returnedUserPersonalProfile.lastName, currentUserPersonalProfile.lastName, "Last name does not match.");
        System.out.println("The profile is successfully updated.");
    }
}

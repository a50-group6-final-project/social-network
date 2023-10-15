package weare.api.testing.users;

import Utils.ModelGenerator;
import api.UserController;
import io.restassured.response.Response;
import models.ExpertiseProfile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UpdateUserExpertiseTests extends BaseUserSetup {

    @BeforeClass
    public void setup() {
        if (isRegistered == false) {
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;

        }
    }

    @Test
    public void updateUserExpertise_Successful() {
        ExpertiseProfile expertiseProfile = ModelGenerator.generateUserExpertiseProfile(158, "Market", 33.66);
        authenticateAndFetchCookies();
        Response response = UserController.updateExpertiseProfile(cookies, expertiseProfile, currentUserId);
        System.out.println(response.asString());

        ExpertiseProfile updatedProfile = response.as(ExpertiseProfile.class);
        isResponse200(response);

        Assert.assertEquals(expertiseProfile.category.id, updatedProfile.category.id, "Category ID does not match.");
        Assert.assertEquals(expertiseProfile.category.name, updatedProfile.category.name, "Category name does not match.");
        Assert.assertEquals(expertiseProfile.availability, updatedProfile.availability, "Availability does not match.");
        System.out.println("The profile is successfully updated.");
    }
}

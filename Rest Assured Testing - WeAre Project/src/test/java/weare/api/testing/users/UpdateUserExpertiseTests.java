package weare.api.testing.users;

import Utils.ModelGenerator;
import Utils.Serializer;
import api.UserController;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import models.ExpertiseProfile;
import models.UserProfile;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.UPDATE_ENDPOINT;

public class UpdateUserExpertiseTests extends BaseUserSetup {

    @BeforeClass
    public void setup() {
        if (isRegistered == false) {
            userToRegister = ModelGenerator.generateUserRegisterModel();
            register(userToRegister);
            isRegistered = true;

        }
        if (currentUserPersonalProfile == null) {
            currentUserPersonalProfile = ModelGenerator.generateUserPersonalModel();
        }

    }

    @Test
    public void updateUserExpertise_Successful() {
        System.out.println("STOP");
        ExpertiseProfile expertiseProfile = ModelGenerator.generateUserExpertiseProfile(157, "Marketing Updated", 33.66);
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

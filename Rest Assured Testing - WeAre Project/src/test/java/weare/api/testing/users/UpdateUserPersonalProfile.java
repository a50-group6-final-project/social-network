package weare.api.testing.users;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.City;
import models.UserPersonal;
import models.UserUpdatePersonal;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class UpdateUserPersonalProfile extends BaseUserSetup {


    @Test
    public void updateUserPersonalProfile_Successful() {
        UserUpdatePersonal updateProfile = new UserUpdatePersonal();
        updateProfile.username = currentUserPersonalProfile.username;
        updateProfile.email = currentUserPersonalProfile.email;
        updateProfile.firstName = "firstName Updated";
        updateProfile.lastName = "lastName Updated";
        updateProfile.location.city = new City();
        updateProfile.location.city.id = 1;
        updateProfile.birthYear = "1990-04-04";
        updateProfile.skills = currentUserPersonalProfile.skills;

        String bodyUpdatedPersonalProfileString = Serializer.convertObjectToJsonString(updateProfile);

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .cookie("JSESSIONID", JSESSIONID)
                .contentType("application/json")
                .body(bodyUpdatedPersonalProfileString)
                .when()
                .post("/api/users/auth/" + currentUserId + "/personal");

        System.out.println(response.asString());
        isResponse200(response);

        UserPersonal returnedUserPersonalProfile = response.as(UserPersonal.class);

        Assert.assertEquals(returnedUserPersonalProfile.firstName, currentUserPersonalProfile.firstName, "First name does not match.");
        Assert.assertEquals(returnedUserPersonalProfile.lastNAme, currentUserPersonalProfile.lastNAme, "Last name does not match.");
        Assert.assertEquals(returnedUserPersonalProfile.id, currentUserPersonalProfile.id, "ID does not match.");

        System.out.println("The profile is successfully updated.");
    }
}

package weare.api.testing.users;

import Utils.Serializer;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserPersonal;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UpdateUserPersonalProfile extends BaseUserSetup {


    @Test
    public void updateUserPersonalProfile_Successful() {
        currentUserPersonalProfile.firstName = "firstTestUpdated";
        currentUserPersonalProfile.lastNAme = "lastTestUpdated";
        currentUserPersonalProfile.gender = "MALE";
        currentUserPersonalProfile.city = "Sofia";
        currentUserPersonalProfile.birthYear = "1990";
        String bodyUpdatedPersonalProfileString = Serializer.convertObjectToJsonString(currentUserPersonalProfile);

        Response response = RestAssured.given()
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

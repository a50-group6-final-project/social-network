package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.JSONRequests.*;
import static org.testng.Assert.assertEquals;

public class UpgradeUserPersonalProfile extends BaseTestSetup {


    @Test
    public void updateUserPersonalProfile_Successful() {

        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies).
                contentType("application/json").
                body(UPGRADE_USER_PERSONAL_PROFILE_BODY).
                when().
                post("/api/users/auth/" + userID + "/personal");

        System.out.println(response.asString());
        AssertResponse(response);
        int idFromResponse = response.jsonPath().getInt("id");
        String firstNameFromResponse = response.jsonPath().getString("firstName");

        assertEquals(idFromResponse, userID, "ID does not match.");
        assertEquals(firstNameFromResponse, updatedName, "First name does not match.");

        System.out.println("The profile is successfully updated.");
    }
}

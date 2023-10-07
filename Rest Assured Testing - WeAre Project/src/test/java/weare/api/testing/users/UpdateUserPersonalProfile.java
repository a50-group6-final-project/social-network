package weare.api.testing.users;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.JSONRequests.UPGRADE_USER_PROFILE_TEMPLATE;
import static Utils.JSONRequests.updatedName;
import static org.testng.Assert.assertEquals;

public class UpdateUserPersonalProfile extends BaseTestSetup {


    @Test
    public void updateUserPersonalProfile_Successful() {
        currentUsername = generateUniqueUsername();
        currentEmail = generateUniqueEmail();
        register(currentUsername, currentEmail);
        authenticateAndFetchCookies(senderUsername,"Project.10");
        String body = String.format(UPGRADE_USER_PROFILE_TEMPLATE, currentUserId, updatedName);


        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(body)
                .when()
                .post("/api/users/auth/" + currentUserId + "/personal");

        System.out.println(response.asString());
        isResponse200(response);
        int id = response.jsonPath().getInt("id");
        String firstNameFromResponse = response.jsonPath().getString("firstName");

        assertEquals(id, currentUserId + 1, "ID is not incremented by 1.");
        assertEquals(firstNameFromResponse, updatedName, "First name does not match.");

        System.out.println("The profile is successfully updated.");
    }
}

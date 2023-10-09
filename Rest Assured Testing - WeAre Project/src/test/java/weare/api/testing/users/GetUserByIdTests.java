package weare.api.testing.users;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserPersonal;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class GetUserByIdTests extends BaseUserSetup {
    @Test
    public void getUserById_Successful() {
        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
                .queryParam("principal", currentUsername)
                .when()
                .get("/api/users/auth/" + currentUserId);

        isResponse200(response);

        userPersonal = response.as(UserPersonal.class);

        currentUserPersonalProfile = userPersonal;

        Assert.assertEquals(userPersonal.username, currentUsername, "Usernames don't match!");
        Assert.assertEquals(userPersonal.email, currentEmail, "Emails don't match!");
        Assert.assertEquals(userPersonal.id, currentUserId, "IDs don't match!");
    }
}

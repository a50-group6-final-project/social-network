package weare.api.testing.users;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserPersonal;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;

public class GetUserByIdTests extends BaseTestSetup {
    @Test
    public void getUserById_Successful() {

        Response response = RestAssured.given()
                .baseUri(BASE_URL)
                .contentType("application/json")
//                .queryParam("principal", currentUsername)
                .queryParam("principal", "MrTestuMyKI")
                .when()
//                .get("/api/users/auth/" + currentUserId);
                .get("/api/users/auth/" + 45);

        System.out.println(response.asString());
        isResponse200(response);

        UserPersonal userPersonal = response.as(UserPersonal.class);
        System.out.println(userPersonal.firstName);
    }
}

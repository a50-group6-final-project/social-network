package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static Utils.Endpoints.AUTHENTICATE_ENDPOINT;
import static Utils.Endpoints.BASE_URL;
import static Utils.JSONRequests.UPGRADE_USER_PERSONAL_PROFILE_BODY;

public class UpgradeUserPersonalProfile extends BaseTestSetup {


    @Test
    public void updateUserPersonalProfileTest() {

        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .cookies(cookies)
                .contentType("application/json")
                .body(UPGRADE_USER_PERSONAL_PROFILE_BODY)
                .when()
                .post("/api/users/auth/" + 144 + "/personal");

        System.out.println(response.asString());


        AssertResponse(response);
    }
}

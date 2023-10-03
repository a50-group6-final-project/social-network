package weare.api.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.*;
import static Utils.JSONRequests.GET_USER_BY_USERNAME_BODY;
import static base.BaseTestSetup.AssertResponse;

public class GetUserByUsername {


    @Test
    public void getUserByUsername() {
        RestAssured.baseURI = BASE_URL;


        Response response = RestAssured.given()

                .contentType(APPLICATION_JSON)
                .body(GET_USER_BY_USERNAME_BODY)
                .post(GET_USERS_BY_USERNAME);

        System.out.println(response.asString());

        AssertResponse(response);

    }
}
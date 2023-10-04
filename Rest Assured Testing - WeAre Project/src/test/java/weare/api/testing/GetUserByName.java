package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.GET_USERS_BY_NAME_ENDPOINT;
import static Utils.JSONRequests.GET_USER_BY_NAME_BODY;

public class GetUserByName extends BaseTestSetup {


    @Test
    public void getUserByName_Successful() {
        RestAssured.baseURI = BASE_URL;


        Response response = RestAssured.given()

                .contentType(APPLICATION_JSON)
                .body(GET_USER_BY_NAME_BODY)
                .when()
                .post(GET_USERS_BY_NAME_ENDPOINT);

        System.out.println(response.asString());

        AssertResponse(response);

    }
}
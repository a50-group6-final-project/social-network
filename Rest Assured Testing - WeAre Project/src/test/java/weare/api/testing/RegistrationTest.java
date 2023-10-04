package weare.api.testing;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.USERS_ENDPOINT;
import static Utils.JSONRequests.REGISTRATION_BODY;


public class RegistrationTest extends BaseTestSetup {


    @Test
    public void  registerUser_Successful() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType(APPLICATION_JSON)
                .body(REGISTRATION_BODY)
                .when()
                .post(USERS_ENDPOINT);

        System.out.println(response.asString());

        AssertResponse(response);


        System.out.println("Registered successfully!");


    }
}

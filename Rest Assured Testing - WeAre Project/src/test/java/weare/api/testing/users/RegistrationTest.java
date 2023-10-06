package weare.api.testing.users;

import base.BaseTestSetup;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.USERS_ENDPOINT;
import static Utils.JSONRequests.REGISTRATION_BODY;


public class RegistrationTest extends BaseTestSetup {


    @Test
    public void registerUser_successful() {
       registerAndAuthenticate();

//        currentUsername = generateUniqueUsername();
//        currentEmail = generateUniqueEmail();
//
//        RestAssured.baseURI = BASE_URL;
//        String body = REGISTRATION_BODY(currentUsername, currentEmail);
//
//        Response response = RestAssured.given()
//                .contentType("application/json")
//                .body(body)
//                .when()
//                .post(USERS_ENDPOINT);
//
//        System.out.println(response.asString());
//        isResponse200(response);

    }


}









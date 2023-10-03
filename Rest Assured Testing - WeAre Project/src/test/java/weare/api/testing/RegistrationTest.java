package weare.api.testing;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static Utils.Constants.APPLICATION_JSON;
import static Utils.Endpoints.BASE_URL;
import static Utils.Endpoints.USERS_ENDPOINT;
import static Utils.JSONRequests.REGISTRATION_BODY;
import static base.BaseTestSetup.AssertResponse;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;

public class RegistrationTest {




    @Test
    public void registrarUserSuccessfullyTest() {
        RestAssured.baseURI = BASE_URL;

        Response response = RestAssured.given()
                .contentType(APPLICATION_JSON)
                .body(REGISTRATION_BODY)
                .post(USERS_ENDPOINT);

        System.out.println(response.asString());

        AssertResponse(response);


        System.out.println("Registered successfully!");


    }
}
